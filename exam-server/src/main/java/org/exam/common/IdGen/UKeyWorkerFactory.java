package org.exam.common.IdGen;

/**
 * @author heshiyuan
 */
public class UKeyWorkerFactory {

    /**
     * 生成各种业务标示的主键
     * @param keyTypeEnum
     * @return
     */
    public static UKeyWorker createUKeyWorker(UKeyTypeEnum keyTypeEnum){
        return new UKeyWorker(UKeyTypeEnum.getKeyTypeFlag(keyTypeEnum));
    }
}
