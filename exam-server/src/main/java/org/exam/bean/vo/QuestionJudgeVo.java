package org.exam.bean.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.bean.vo
 * @date 11/05/2019 13:43
 */
@Data
public class QuestionJudgeVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long judgeId;

    private String judgeName;

    private Boolean answer;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long courseId;
}
