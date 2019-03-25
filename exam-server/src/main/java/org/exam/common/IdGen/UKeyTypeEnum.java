package org.exam.common.IdGen;

/**
 * Created with IntelliJ IDEA.
 * User: changjiang
 * Date: 2019/3/1
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 * 业务标示最大值不能超过63L，目前限定不能超过40L，合理分配业务标示
 * 未知的默认业务标示位1L，机构2L，用户3L，
 */
public enum UKeyTypeEnum {


    //手机号，邮箱等自动生成的加盐代码
    SALT_CODE(4L,"saltCode","加盐代码"),

    USER_COMMON(3L,"usercommon","普通用户"),

    //机构类型ID 10L
    ORG_COMMON(2L,"org","机构类型"),

    //分享计划书编码 5L
    SHARE_TRACK(5L,"share_track","分享编码"),

    //访问微信分享编码 8L
    USER_APPLY(8L,"user_apply","用户申请记录id"),
    //访问微信分享记录主表编码 7L
    STATISTICS_SHARE(6L,"statistics_share","访问微信分享记录主表编码"),

    //访问微信分享明细记录编码 6L
    VISIT_SHARE(7L,"visit_share","访问微信分享明细记录编码");




    private long bussnessflag;
    private String bussnessType;
    private String bussnessDescription;

    UKeyTypeEnum(long bussnessflag,String bussnessType,String bussnessDescription){

        this.bussnessflag = bussnessflag;
        this.bussnessType = bussnessType;
        this.bussnessDescription = bussnessDescription;
    }

    /**
     * 获得对应的业务标示位
     * @param uKeyTypeEnum
     * @return
     */
    public static long getKeyTypeFlag(UKeyTypeEnum uKeyTypeEnum){

        UKeyTypeEnum[] values = UKeyTypeEnum.values();
        for(UKeyTypeEnum resultStatusEnum:values){
            if(resultStatusEnum == uKeyTypeEnum){
                return resultStatusEnum.getBussnessflag();
            }
        }
        //默认的业务标示位为1L
        return 1L;
    }

    public long getBussnessflag() {
        return bussnessflag;
    }

    public void setBussnessflag(long bussnessflag) {
        this.bussnessflag = bussnessflag;
    }

    public String getBussnessType() {
        return bussnessType;
    }

    public void setBussnessType(String bussnessType) {
        this.bussnessType = bussnessType;
    }

    public String getBussnessDescription() {
        return bussnessDescription;
    }

    public void setBussnessDescription(String bussnessDescription) {
        this.bussnessDescription = bussnessDescription;
    }
}
