package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TMenu;
import org.exam.bean.entity.TMenu;
import org.exam.mapper.TMenuMapper;
import org.exam.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "Menu")
@RestController
@RequestMapping("/system/config/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private TMenuMapper tMenuMapper;

    @ApiOperation(value = "用户列表", notes = "分页列表")
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<List<TMenu>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseBean.ok(menuService.list(pageNum, pageSize));
    }

    @ApiOperation(value = "菜单树", notes = "根据当前登录用户，parentId查询菜单树")
    @GetMapping(value = "/tree")
    public ResponseBean<List<TMenu>> tree(@RequestParam(required = false) Long parentId){
        return ResponseBean.ok(menuService.getMenuTreeByUserId(null, parentId));
    }

    @ApiOperation(value = "菜单列表树", notes = "根据parentId查询菜单及其子菜单")
    @GetMapping(value = "/tree/list")
    public ResponseBean<List<TMenu>> treeList(@RequestParam(required = false) Long parentId){
        return ResponseBean.ok(menuService.tree(parentId));
    }

    @GetMapping(value = "/{menuId}")
    public ResponseBean<TMenu> get(@PathVariable("menuId") Long menuId){
        return ResponseBean.ok(tMenuMapper.selectByPrimaryKey(menuId));
    }

    @PutMapping
    public ResponseBean put(TMenu menu){
        return ResponseBean.ok(menuService.update(menu));
    }
    @PostMapping
    public ResponseBean post(TMenu menu){
        return ResponseBean.ok(menuService.add(menu));
    }

    @DeleteMapping(value = "/{menuId}")
    public ResponseBean delete(@PathVariable("menuId") Long menuId){
        return ResponseBean.ok(menuService.delete(menuId));
    }

    @DeleteMapping(value = "/")
    public ResponseBean delete(@RequestParam Long[] ids){
        return ResponseBean.ok(menuService.delete(ids));
    }
}
