package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.RespBean;
import org.exam.bean.entity.TQuestionJudge;
import org.exam.service.QuestionChoiceService;
import org.exam.service.QuestionJudgeService;
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
@RequestMapping("/question/judge")
public class TQuestionJudgeController {
    @Autowired
    QuestionJudgeService questionJudgeService;
    @GetMapping(value = "/list")
    public RespBean<PageInfo<List<TQuestionJudge>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return RespBean.ok(questionJudgeService.list(pageNum, pageSize));
    }

    @PutMapping
    public RespBean<Integer> put(TQuestionJudge academy){
        return RespBean.ok(questionJudgeService.update(academy));
    }
    @PostMapping
    public RespBean post(TQuestionJudge academy){
        return RespBean.ok(questionJudgeService.insert(academy));
    }


    @DeleteMapping(value = "/{id}")
    public RespBean delete(@PathVariable("id") Long id){
        return RespBean.ok(questionJudgeService.delete(id));
    }

}
