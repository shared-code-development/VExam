package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.RespBean;
import org.exam.bean.entity.TMajor;
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
@Api(tags = "专业相关")
@RestController
@RequestMapping("/major")
public class MajorController {

    @Autowired
    MajorService majorService;
    @GetMapping(value = "/list")
    public RespBean<PageInfo<List<TMajor>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return RespBean.ok(majorService.majorList(pageNum, pageSize));
    }

    @PutMapping
    public RespBean<Integer> put(TMajor major){
        return RespBean.ok(majorService.update(major));
    }
    @PostMapping
    public RespBean post(TMajor major){
        return RespBean.ok(majorService.insert(major));
    }

    @DeleteMapping(value = "/{id}")
    public RespBean delete(@PathVariable("id") Long id){
        return RespBean.ok(majorService.delete(id));
    }
}
