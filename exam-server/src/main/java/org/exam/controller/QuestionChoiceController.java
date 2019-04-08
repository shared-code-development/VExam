package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TQuestionChoice;
import org.exam.service.QuestionChoiceService;
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
@RequestMapping("/questionChoice")
public class QuestionChoiceController {

    @Autowired
    QuestionChoiceService questionChoiceService;
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<List<TQuestionChoice>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseBean.ok(questionChoiceService.questionChoiceList(pageNum, pageSize));
    }

    @PutMapping
    public ResponseBean<Integer> put(TQuestionChoice questionChoice){
        return ResponseBean.ok(questionChoiceService.update(questionChoice));
    }
    @PostMapping
    public ResponseBean post(TQuestionChoice questionChoice){
        return ResponseBean.ok(questionChoiceService.insert(questionChoice));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseBean delete(@PathVariable("id") Long id){
        return ResponseBean.ok(questionChoiceService.delete(id));
    }
}