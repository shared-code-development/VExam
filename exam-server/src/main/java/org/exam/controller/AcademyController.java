package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.RespBean;
import org.exam.bean.entity.TAcademy;
import org.exam.service.AcademyService;
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
@Api(tags = "学院相关")
@RestController
@RequestMapping("/academy")
public class AcademyController {

    @Autowired
    AcademyService academyService;
    @GetMapping(value = "/list")
    public RespBean<PageInfo<List<TAcademy>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return RespBean.ok(academyService.academyList(pageNum, pageSize));
    }

    @PutMapping
    public RespBean<Integer> put(TAcademy academy){
        return RespBean.ok(academyService.update(academy));
    }
    @PostMapping
    public RespBean post(TAcademy academy){
        return RespBean.ok(academyService.insert(academy));
    }


    @DeleteMapping(value = "/{id}")
    public RespBean delete(@PathVariable("id") Long id){
        return RespBean.ok(academyService.delete(id));
    }

}
