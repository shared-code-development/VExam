package org.exam.common.IdGen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
/**
 * timestamp + bizId + workId + sequence
 * @author heshiyuan
 */
@Slf4j
public class UKeyWorker{
    private final static long twepoch = 1404878190828L;
    /**
     * 机器标识位数（每种业务支持多少worker-thread）
     */
	private final static long workerIdBits = 5L;
    /**
     * 业务标识位数（预留）
     */
	private final static long bizIdBits = 6L;

    /**
     * 机器标识最大值
     */
	private final static long maxWorkerId = -1L ^ -1L << UKeyWorker.workerIdBits;
    /**
     * 业务标识最大值
     */
	private final static long maxBizId = -1L ^ -1L << UKeyWorker.bizIdBits;

    /**
     * 毫秒内自增位数
     */
	private final static long sequenceBits = 12L;

    /**
     * 机器标识偏左移
     */
	private final static long workerIdShift = UKeyWorker.sequenceBits;
    /**
     * 业务标识偏左移
     */
	private final static long bizIdShift = UKeyWorker.sequenceBits + UKeyWorker.workerIdBits;
    /**
     * 时间毫秒偏左移
     */
	private final static long timestampShift = UKeyWorker.sequenceBits
			+ UKeyWorker.workerIdBits + UKeyWorker.bizIdBits;

    /**
     * 序列号Mask
     */
	private final static long sequenceMask = -1L ^ -1L << UKeyWorker.sequenceBits;
    /**
     * 机器标识Mask
     */
	private final static long workerIdMask = UKeyWorker.maxWorkerId << UKeyWorker.workerIdShift;
    /**
     * 业务标识Mask
     */
	private final static long bizIdMask = UKeyWorker.maxBizId << UKeyWorker.bizIdShift;
    /**
     * 时间戳Mask
     */
	private final static long timestampMask = Long.MAX_VALUE
			^ (UKeyWorker.sequenceMask | UKeyWorker.workerIdMask | UKeyWorker.bizIdMask);

    /**
     * 最后的时间戳
     */
	private long lastTimestamp = -1L;

	private long workerId = 1L;

	private long bizId = 2L;

	private long sequence = 0L;

	
	public UKeyWorker(long bizId) {
		super();
		if (workerId > UKeyWorker.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format(
					"worker Id can't be greater than %d or less than 0",
					UKeyWorker.maxWorkerId));
		}
		this.workerId = workerId;
		
		if (bizId > UKeyWorker.maxBizId || workerId < 0) {
			throw new IllegalArgumentException(String.format(
					"biz Id can't be greater than %d or less than 0",
					UKeyWorker.maxBizId));
		}
		this.bizId = bizId;
		
		if (log.isDebugEnabled()) {
			log.debug("{msg:UKeyWorker,workId:" + this.workerId + ",bizId:" + this.bizId + "}");
		}
		log.info("{msg:UKeyWorker,workId:" + this.workerId + ",bizId:" + this.bizId + "}");
	}


	public UKeyWorker(long workerId, long bizId) {
		super();
		if (workerId > UKeyWorker.maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format(
					"worker Id can't be greater than %d or less than 0",
					UKeyWorker.maxWorkerId));
		}
		this.workerId = workerId;

		if (bizId > UKeyWorker.maxBizId || workerId < 0) {
			throw new IllegalArgumentException(String.format(
					"biz Id can't be greater than %d or less than 0",
					UKeyWorker.maxBizId));
		}
		this.bizId = bizId;

		if (log.isDebugEnabled()) {
			log.debug("{msg:UKeyWorker,workId:" + this.workerId + ",bizId:" + this.bizId + "}");
		}
		log.info("{msg:UKeyWorker,workId:" + this.workerId + ",bizId:" + this.bizId + "}");
	}
	
	public long getId() {
		long id = nextId();
		return id;
	}

	public synchronized long nextId() {
		long timestamp = this.timeGen();
		// 时间发生错误
		if (timestamp < this.lastTimestamp) {
			throw new RuntimeException(
					String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
									this.lastTimestamp - timestamp));
		}

		//毫秒内
		if (this.lastTimestamp == timestamp) {
			this.sequence = (this.sequence + 1) & UKeyWorker.sequenceMask;
			if (this.sequence == 0) {
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}
		this.lastTimestamp = timestamp;

		//计算
		long nextId = ((timestamp - UKeyWorker.twepoch << UKeyWorker.timestampShift)) |
			(this.bizId << UKeyWorker.bizIdShift) | 
			(this.workerId << UKeyWorker.workerIdShift) | 
			(this.sequence);

		return nextId;
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}
	
	public static long getSequence(final long id){
		return (id & UKeyWorker.sequenceMask);
	}
	
	public static long getWorkerId(final long id){
		return (id & UKeyWorker.workerIdMask) >> UKeyWorker.workerIdShift;
	}
	
	public static long getBizId(final long id){
		return (id & UKeyWorker.bizIdMask) >> UKeyWorker.bizIdShift ;
	}
	
	public static long getTimestamp(final long id) {
		return (id & UKeyWorker.timestampMask) >> UKeyWorker.timestampShift;
	}
	
	

	public static long getTwepoch() {
		return twepoch;
	}

	public static long getTimestampshift() {
		return timestampShift;
	}
}
