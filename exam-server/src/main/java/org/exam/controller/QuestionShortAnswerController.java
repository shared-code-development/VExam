package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TQuestionShortAnswer;
import org.exam.service.QuestionShortAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * exam-server/org.exam.controller
 *
 * @author heshiyuan
 */
@Api(tags = "学院相关")
@RestController
@RequestMapping("/questionShortAnswer")
public class QuestionShortAnswerController {

    @Autowired
    QuestionShortAnswerService questionShortAnswerService;
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<List<TQuestionShortAnswer>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseBean.ok(questionShortAnswerService.questionShortAnswerList(pageNum, pageSize));
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
}