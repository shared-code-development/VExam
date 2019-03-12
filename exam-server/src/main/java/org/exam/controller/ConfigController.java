package org.exam.controller;

import io.swagger.annotations.*;
import org.exam.bean.entity.TMenu;
import org.exam.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.controller
 * @date 10/03/2019 17:42
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Api(tags = "微考试系统-配置接口")
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "配置菜单", notes = "根据当前登陆用户获取当前用户的菜单列表")
    @ApiResponses({
            @ApiResponse(code=100,message="参数校验失败"),
            @ApiResponse(code=200,message="请求成功"),
            @ApiResponse(code=400,message="无权访问"),
            @ApiResponse(code=500,message="服务器错误"),
            @ApiResponse(code=600,message="业务异常"),
            @ApiResponse(code=700,message="其它异常")
    })
    @RequestMapping("/menu")
    public List<TMenu> menu() {
        return menuService.getMenusByUserId();
    }

    @ApiOperation(value = "配置菜单", notes = "根据当前登陆用户获取当前用户的菜单列表")
    @ApiResponses({
            @ApiResponse(code=100,message="参数校验失败"),
            @ApiResponse(code=200,message="请求成功"),
            @ApiResponse(code=400,message="无权访问"),
            @ApiResponse(code=500,message="服务器错误"),
            @ApiResponse(code=600,message="业务异常"),
            @ApiResponse(code=700,message="其它异常")
    })
    @RequestMapping("/menu/list")
    public List<TMenu> menuList() {
        return menuService.getMenusByUserId();
    }
}
