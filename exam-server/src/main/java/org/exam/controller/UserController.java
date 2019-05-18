package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TUser;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.enums.BusinessEnum;
import org.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
@Slf4j
@Api(tags = "User")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UKeyWorker userIdWorker;

    @ApiOperation(value = "获取用户id", notes = "获取下一个用户id")
    @GetMapping(value = "/nextUserId")
    public ResponseBean<Long> nextUserId(){
        long userId = userIdWorker.getId();
        log.info("{}", userId);
        return ResponseBean.ok(userId);
    }
    @Autowired
    UserService userService;

    @ApiResponses({
        @ApiResponse(code=10000,message="请求成功")
    })
    @ApiOperation(value = "用户列表", notes = "分页列表")
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<TUser>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String keywords){
        return ResponseBean.ok(userService.list(pageNum, pageSize, keywords));
    }

    @ApiOperation(value = "查询当前登陆用户信息", notes = "根据header中的token识别用户信息")
    @GetMapping
    public ResponseBean<TUser> get(){
        return ResponseBean.ok(userService.get(null));
    }
    @PutMapping
    public ResponseBean put(TUser user){
        return ResponseBean.ok(userService.update(user));
    }
    @PostMapping
    public ResponseBean post(TUser user){
        return ResponseBean.ok(userService.add(user));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseBean delete(@PathVariable("id") Long id){
        return ResponseBean.ok(userService.delete(id));
    }

    @DeleteMapping(value = "/")
    public ResponseBean delete(@RequestParam Long[] ids){
        return ResponseBean.ok(userService.delete(ids));
    }
}
