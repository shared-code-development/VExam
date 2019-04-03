package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TCourse;
import org.exam.service.CourseService;
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
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<List<TCourse>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseBean.ok(courseService.courseList(pageNum, pageSize));
    }

    @PutMapping
    public ResponseBean<Integer> put(TCourse course){
        return ResponseBean.ok(courseService.update(course));
    }
    @PostMapping
    public ResponseBean post(TCourse course){
        return ResponseBean.ok(courseService.insert(course));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseBean delete(@PathVariable("id") Long id){
        return ResponseBean.ok(courseService.delete(id));
    }
}