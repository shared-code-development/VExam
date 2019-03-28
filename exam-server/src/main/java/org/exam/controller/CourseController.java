package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.RespBean;
import org.exam.bean.entity.TCourse;
import org.exam.service.CourseService;
import org.exam.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.controller
 * @date 18/03/2019 22:47
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Api(tags = "课程相关")
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;
    @GetMapping(value = "/list")
    public RespBean<PageInfo<List<TCourse>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return RespBean.ok(courseService.list(pageNum, pageSize));
    }

    @PutMapping
    public RespBean<Integer> put(TCourse course){
        return RespBean.ok(courseService.update(course));
    }
    @PostMapping
    public RespBean post(TCourse course){
        return RespBean.ok(courseService.insert(course));
    }

    @DeleteMapping(value = "/{id}")
    public RespBean delete(@PathVariable("id") Long id){
        return RespBean.ok(courseService.delete(id));
    }
}
