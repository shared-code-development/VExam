package org.exam.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.exam.bean.dto.RespBean;
import org.exam.bean.entity.TNation;
import org.exam.bean.entity.TUser;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.enums.BusinessEnum;
import org.exam.service.NationService;
import org.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@Api(tags = "用户相关")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UKeyWorker userIdWorker;
    @Autowired
    NationService nationService;

    @GetMapping(value = "/nation/list")
    public RespBean<List<TNation>> nationList(){
        return RespBean.ok(BusinessEnum.SERVER_SUCCESS, nationService.nationList());
    }

    @GetMapping(value = "/list")
    public RespBean<PageInfo<List<TUser>>> list(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String keywords,
            @RequestParam(required = false) String beginDateScope){
        return RespBean.ok(userService.getUserList(pageNum, pageSize, keywords, beginDateScope));
    }

    @PutMapping
    public RespBean put(TUser user){
        return RespBean.ok(userService.updateUser(user));
    }
    @PostMapping
    public RespBean post(TUser user){
        return RespBean.ok(userService.addUser(user));
    }


    @DeleteMapping(value = "/{ids}")
    public RespBean delete(@PathVariable("ids") String ids){
        return RespBean.ok(userService.deleteUser(ids));
    }


    @GetMapping(value = "/nextUserId")
    public RespBean<Long> nextUserId(){
        return RespBean.ok(userIdWorker.getId());
    }


    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportEmp() {
//        return PoiUtils.exportEmp2Excel(empService.getAllEmployees());
        return null;
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public RespBean importEmp(MultipartFile file) {
        /*List<Employee> emps = PoiUtils.importEmp2List(file,
                empService.getAllNations(), empService.getAllPolitics(),
                departmentService.getAllDeps(), positionService.getAllPos(),
                jobLevelService.getAllJobLevels());
        if (empService.addEmps(emps) == emps.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");*/
        return null;
    }
}
