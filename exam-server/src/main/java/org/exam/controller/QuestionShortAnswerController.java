package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TQuestionChoice;
import org.exam.bean.entity.TQuestionShortAnswer;
import org.exam.service.ExamPaperService;
import org.exam.service.QuestionShortAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * exam-server/org.exam.controller
 *
 * @author heshiyuan
 */
@Api(tags = "QuestionShortAnswer")
@RestController
@RequestMapping("/questionShortAnswer")
public class QuestionShortAnswerController {

    @Autowired
    QuestionShortAnswerService questionShortAnswerService;
    @Autowired
    ExamPaperService examPaperService;
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<TQuestionShortAnswer>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyWords,
            @RequestParam(required = false) Long courseId
            ){
        return ResponseBean.ok(questionShortAnswerService.questionShortAnswerList(pageNum, pageSize, keyWords, courseId));
    }

    @PutMapping
    public ResponseBean<Integer> put(TQuestionShortAnswer questionShortAnswer){
        return ResponseBean.ok(questionShortAnswerService.update(questionShortAnswer));
    }
    @PostMapping
    public ResponseBean post(TQuestionShortAnswer questionShortAnswer){
        return ResponseBean.ok(questionShortAnswerService.insert(questionShortAnswer));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseBean delete(@PathVariable("id") Long id){
        return ResponseBean.ok(questionShortAnswerService.delete(id));
    }
    @DeleteMapping
    public ResponseBean deleteMany(@RequestParam("ids") Long[] ids){
        return ResponseBean.ok(questionShortAnswerService.delete(ids));
    }
    @GetMapping(value = "/paper")
    public ResponseBean<List<TQuestionShortAnswer>> getShortAnswerListOfPaper(@RequestParam Long paperId){
        return ResponseBean.ok(examPaperService.getQuestionSampleAnswerOfPaper(paperId));
    }
}