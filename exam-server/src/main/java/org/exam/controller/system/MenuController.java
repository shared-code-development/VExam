package org.exam.controller.system;

import io.swagger.annotations.*;
import org.exam.bean.dto.RespBean;
import org.exam.bean.entity.TMenu;
import org.exam.enums.BusinessEnum;
import org.exam.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
@Api(tags = "微考试系统-菜单配置接口")
@RestController
@RequestMapping("/system/config")
public class MenuController {
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
    public RespBean<List<TMenu>> menu() {
        return RespBean.ok(menuService.getMenusByUserId());
    }

    @GetMapping("/menu/{parentId}")
    public RespBean<List<TMenu>> menu(@PathVariable Integer parentId) {
        return RespBean.ok(menuService.getMenuByParentId(parentId));
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
    @GetMapping("/menu/list")
    public RespBean<List<TMenu>> getList() {
        return RespBean.ok(BusinessEnum.SERVER_SUCCESS, menuService.getMenuList());
    }

    @PostMapping("/menu")
    public RespBean<Boolean> insert(TMenu menu) {
        return RespBean.ok(BusinessEnum.SERVER_SUCCESS, menuService.addMenu(menu));
    }

    @DeleteMapping("/menu/{id}")
    public RespBean<Boolean> delete(@PathVariable Integer id) {
        return RespBean.ok(menuService.deleteMenu(id));
    }

    @GetMapping("/menu/{parentId}/{roleId}")
    public RespBean<Map<String, Object>> getListByRole(@PathVariable Integer parentId, @PathVariable Integer roleId) {
        return RespBean.ok(BusinessEnum.SERVER_SUCCESS, menuService.getMenuTreeByRoleId(parentId, roleId));
    }
}
