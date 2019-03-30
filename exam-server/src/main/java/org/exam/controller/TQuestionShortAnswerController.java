package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.RespBean;
import org.exam.bean.entity.TQuestionShortAnswer;
import org.exam.service.QuestionJudgeService;
import org.exam.service.QuestionShortAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * exam-server/org.exam.controller
 *
 * @author heshiyuan
 */
@Api(tags = "班级相关")
@RestController
@RequestMapping("/question/short-answer")
public class TQuestionShortAnswerController {
    @Autowired
    QuestionShortAnswerService questionShortAnswerService;
    @GetMapping(value = "/list")
    public RespBean<PageInfo<List<TQuestionShortAnswer>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return RespBean.ok(questionShortAnswerService.list(pageNum, pageSize));
    }

    @PutMapping
    public RespBean<Integer> put(TQuestionShortAnswer academy){
        return RespBean.ok(questionShortAnswerService.update(academy));
    }
    @PostMapping
    public RespBean post(TQuestionShortAnswer academy){
        return RespBean.ok(questionShortAnswerService.insert(academy));
    }


    @DeleteMapping(value = "/{id}")
    public RespBean delete(@PathVariable("id") Long id){
        return RespBean.ok(questionShortAnswerService.delete(id));
    }

}
