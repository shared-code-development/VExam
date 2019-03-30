package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.RespBean;
import org.exam.bean.entity.TQuestionChoice;
import org.exam.service.ClazzService;
import org.exam.service.QuestionChoiceService;
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
@RequestMapping("/question/choice")
public class TQuestionChoiceController {
    @Autowired
    QuestionChoiceService questionChoiceService;
    @GetMapping(value = "/list")
    public RespBean<PageInfo<List<TQuestionChoice>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return RespBean.ok(questionChoiceService.list(pageNum, pageSize));
    }

    @PutMapping
    public RespBean<Integer> put(TQuestionChoice academy){
        return RespBean.ok(questionChoiceService.update(academy));
    }
    @PostMapping
    public RespBean post(TQuestionChoice academy){
        return RespBean.ok(questionChoiceService.insert(academy));
    }


    @DeleteMapping(value = "/{id}")
    public RespBean delete(@PathVariable("id") Long id){
        return RespBean.ok(questionChoiceService.delete(id));
    }

}
