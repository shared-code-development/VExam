package org.exam.bean.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.bean.vo
 * @date 13/05/2019 20:23
 */
@Data
@ApiModel(value = "HandleGourpExamPaperVo", description = "组卷实体")
public class HandleGourpExamPaperVo {
    private Long paperId;
    private String questionType;
    private List<Long> questionIds;
}
