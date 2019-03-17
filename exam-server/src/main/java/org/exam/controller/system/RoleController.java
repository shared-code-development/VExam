package org.exam.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.exam.bean.dto.RespBean;
import org.exam.bean.entity.TMenu;
import org.exam.bean.entity.TRole;
import org.exam.enums.BusinessEnum;
import org.exam.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.controller.system
 * @date 15/03/2019 22:55
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Api(tags = "微考试系统-角色配置接口")
@RestController
@RequestMapping("/system/config")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "配置菜单", notes = "根据当前登陆用户获取当前用户的菜单列表")
    @ApiResponses({
            @ApiResponse(code=100,message="参数校验失败"),
            @ApiResponse(code=200,message="请求成功"),
            @ApiResponse(code=400,message="无权访问"),
            @ApiResponse(code=500,message="服务器错误"),
            @ApiResponse(code=600,message="业务异常"),
            @ApiResponse(code=700,message="其它异常")
    })
    @GetMapping("/roles")
    public RespBean getList() {
        return RespBean.ok(BusinessEnum.SERVER_SUCCESS, roleService.roles());
    }

    @PostMapping("/role")
    public RespBean insert(TRole role) {
        return RespBean.ok(BusinessEnum.SERVER_SUCCESS, roleService.addRole(role));
    }

    @DeleteMapping("/role/{id}")
    public RespBean delete(@PathVariable Integer id) {
        return RespBean.ok(BusinessEnum.SERVER_SUCCESS, roleService.deleteRole(id));
    }
}
