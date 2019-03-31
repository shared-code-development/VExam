package org.exam.common.IdGen;

import lombok.Getter;

/**
 * 业务标示最大值不能超过63L，目前限定不能超过40L，合理分配业务标示
 * 未知的默认业务标示位1L，机构2L，用户3L，
 * @author heshiyuan
 */
public enum UKeyTypeEnum {
    //访问微信分享明细记录编码 6L
    USER_ID(1L,"user_id","用户表主键");

    @Getter
    private long bizId;
    @Getter
    private String businessType;
    @Getter
    private String businessDescription;

    UKeyTypeEnum(long bizId, String businessType, String businessDescription){
        this.bizId = bizId;
        this.businessType = businessType;
        this.businessDescription = businessDescription;
    }

    /**
     * 获得对应的业务标示位
     * @param uKeyTypeEnum
     * @return
     */
    public static long getKeyTypeFlag(UKeyTypeEnum uKeyTypeEnum){
        for(UKeyTypeEnum resultStatusEnum : UKeyTypeEnum.values()){
            if(resultStatusEnum == uKeyTypeEnum){
                return resultStatusEnum.getBizId();
            }
        }
        //默认的业务标示位为1L
        return 1L;
    }
}
