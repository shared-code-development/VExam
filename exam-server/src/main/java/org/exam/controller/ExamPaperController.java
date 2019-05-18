package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TExamPaper;
import org.exam.bean.vo.HandleGourpExamPaperVo;
import org.exam.service.ExamPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * exam-server/org.exam.controller
 *
 * @author heshiyuan
 */
@Api(tags = "ExamPaper")
@RestController
@RequestMapping("/examPaper")
public class ExamPaperController {

    @Autowired
    ExamPaperService examPaperService;
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<TExamPaper>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String keyWords
            ){
        return ResponseBean.ok(examPaperService.list(pageNum, pageSize, keyWords));
    }

    @PutMapping
    public ResponseBean<Integer> put(TExamPaper examPaper){
        return ResponseBean.ok(examPaperService.update(examPaper));
    }
    @PostMapping
    public ResponseBean post(TExamPaper examPaper){
        return ResponseBean.ok(examPaperService.insert(examPaper));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseBean delete(@PathVariable("id") Long id){
        return ResponseBean.ok(examPaperService.delete(id));
    }
    @DeleteMapping
    public ResponseBean deleteMany(@RequestParam("ids") Long[] ids){
        return ResponseBean.ok(examPaperService.delete(ids));
    }

    @PostMapping(value = "/handleGroupExamPaper")
    public ResponseBean<Integer> handleGroupExamPaper(
            @Valid @RequestBody HandleGourpExamPaperVo handleGourpExamPaperVo){
        return ResponseBean.ok(examPaperService.groupExamPaper(handleGourpExamPaperVo));
    }
}