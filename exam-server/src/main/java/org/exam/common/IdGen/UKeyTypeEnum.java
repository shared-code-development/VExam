package org.exam.common.IdGen;

import lombok.Getter;

/**
 * 业务标示最大值不能超过63L，目前限定不能超过40L，合理分配业务标示
 * 未知的默认业务标示位1L，机构2L，用户3L，
 * @author heshiyuan
 */
public enum UKeyTypeEnum {
    /**
     * 用户id
     */
    USER_ID(1L,"user_id","用户表主键"),
    MENU_ID(2L,"menu_id","菜单表主键"),
    DIC_TYPE_ID(3L,"menu_id","菜单表主键"),
    DIC_ID(4L,"menu_id","菜单表主键"),
    ACADEMY_ID(5L,"academy_id","学院表主键"),
    MAJOR_ID(6L,"major_id","专业表主键"),
    CLAZZ_ID(7L,"academy_id","班级表主键"),
    COURSE_ID(8L,"course_id","课程表主键"),
    CHOICE_ID(9L,"choice_id","选择题表主键"),
    JUDGE_ID(10L,"judge_id","判断题表主键"),
    SAMPLE_ANSWER_ID(11L,"sample_answer_id","简答题表主键"),
    EXAM_PAPER_ID(12L,"exam_paper_id","试卷表主键"),
    ;

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
