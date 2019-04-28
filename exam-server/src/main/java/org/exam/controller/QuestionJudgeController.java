package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TQuestionJudge;
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
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<List<TQuestionJudge>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyWords){
        return ResponseBean.ok(questionJudgeService.questionJudgeList(pageNum, pageSize, keyWords));
    }

    @PutMapping
    public ResponseBean<Integer> put(TQuestionJudge questionJudge){
        return ResponseBean.ok(questionJudgeService.update(questionJudge));
    }
    @PostMapping
    public ResponseBean post(TQuestionJudge questionJudge){
        return ResponseBean.ok(questionJudgeService.insert(questionJudge));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseBean delete(@PathVariable("id") Long id){
        return ResponseBean.ok(questionJudgeService.delete(id));
    }
    @DeleteMapping
    public ResponseBean deleteMany(@RequestParam("ids") Long[] ids){
        return ResponseBean.ok(questionJudgeService.delete(ids));
    }
}