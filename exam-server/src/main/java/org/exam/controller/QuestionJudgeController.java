package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TQuestionJudge;
import org.exam.bean.entity.TQuestionShortAnswer;
import org.exam.bean.vo.QuestionJudgeVo;
import org.exam.service.ExamPaperService;
import org.exam.service.QuestionJudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * exam-server/org.exam.controller
 *
 * @author heshiyuan
 */
@Api(tags = "QuestionJudge")
@RestController
@RequestMapping("/questionJudge")
public class QuestionJudgeController {

    @Autowired
    QuestionJudgeService questionJudgeService;
    @Autowired
    ExamPaperService examPaperService;
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<TQuestionJudge>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyWords,
            @RequestParam(required = false) Long courseId){
        return ResponseBean.ok(questionJudgeService.questionJudgeList(pageNum, pageSize, keyWords, courseId));
    }

    @PutMapping
    public ResponseBean<Integer> put(QuestionJudgeVo questionJudgeVo){
        return ResponseBean.ok(questionJudgeService.update(questionJudgeVo));
    }
    @PostMapping
    public ResponseBean post(QuestionJudgeVo questionJudgeVo){
        return ResponseBean.ok(questionJudgeService.insert(questionJudgeVo));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseBean delete(@PathVariable("id") Long id){
        return ResponseBean.ok(questionJudgeService.delete(id));
    }
    @DeleteMapping
    public ResponseBean deleteMany(@RequestParam("ids") Long[] ids){
        return ResponseBean.ok(questionJudgeService.delete(ids));
    }
    @GetMapping(value = "/paper")
    public ResponseBean<List<TQuestionJudge>> getShortAnswerListOfPaper(@RequestParam Long paperId){
        return ResponseBean.ok(examPaperService.getQuestionJudgeOfPaper(paperId));
    }
}