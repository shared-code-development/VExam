package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.exam.bean.dto.ResponseBean;
import org.exam.bean.entity.TDic;
import org.exam.bean.entity.TDicType;
import org.exam.mapper.TDicTypeMapper;
import org.exam.service.DicService;
import org.exam.service.DicTypeService;
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
@Api(tags = "Dic")
@RestController
@RequestMapping("/dic")
public class DicController {
    @Autowired
    private DicTypeService dicTypeService;
    @Autowired
    private DicService dicService;

    @ApiOperation(value = "用户列表", notes = "分页列表")
    @GetMapping(value = "/type/list")
    public ResponseBean<PageInfo<TDicType>> dicTypeList(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseBean.ok(dicTypeService.list(pageNum, pageSize));
    }

    @GetMapping(value = "/type/{dicTypeId}")
    public ResponseBean<TDicType> dicTypeGet(@PathVariable("dicTypeId") Long dicTypeId){
        return ResponseBean.ok(dicTypeService.get(dicTypeId));
    }

    @PutMapping(value = "/type")
    public ResponseBean dicTypePut(TDicType dicType){
        return ResponseBean.ok(dicTypeService.update(dicType));
    }
    @PostMapping(value = "/type")
    public ResponseBean dicTypePost(TDicType dicType){
        return ResponseBean.ok(dicTypeService.add(dicType));
    }

    @DeleteMapping(value = "/type/{dicTypeId}")
    public ResponseBean dicTypeDelete(@PathVariable("dicTypeId") Long dicTypeId){
        return ResponseBean.ok(dicTypeService.delete(dicTypeId));
    }

    @DeleteMapping(value = "/type")
    public ResponseBean dicTypeDelete(@RequestParam Long[] ids){
        return ResponseBean.ok(dicTypeService.delete(ids));
    }

    @ApiOperation(value = "字典列表", notes = "分页列表")
    @GetMapping(value = "/list")
    public ResponseBean<PageInfo<TDic>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize){
        return ResponseBean.ok(dicService.list(pageNum, pageSize));
    }

    @GetMapping(value = "/{dicId}")
    public ResponseBean<TDic> get(@PathVariable("dicId") Long dicId){
        return ResponseBean.ok(dicService.get(dicId));
    }

    @PutMapping
    public ResponseBean<Boolean> put(TDic dic){
        return ResponseBean.ok(dicService.update(dic));
    }
    @PostMapping
    public ResponseBean<Boolean> post(TDic dic){
        return ResponseBean.ok(dicService.add(dic));
    }

    @DeleteMapping(value = "/{dicId}")
    public ResponseBean<Integer> delete(@PathVariable("dicId") Long dicId){
        return ResponseBean.ok(dicService.delete(dicId));
    }

    @DeleteMapping
    public ResponseBean<Integer> delete(@RequestParam Long[] ids){
        return ResponseBean.ok(dicTypeService.delete(ids));
    }
    @ApiOperation(value = "字典树列表", notes = "字典树")
    @GetMapping(value = "/type/tree")
    public ResponseBean getDicTreeList(@RequestParam(required = false) Long parentId){
        return ResponseBean.ok(dicTypeService.treeList(parentId));
    }
}
