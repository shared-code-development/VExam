package org.exam.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import org.exam.bean.dto.RespBean;
import org.exam.bean.entity.TUser;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.enums.BusinessEnum;
import org.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping(value = "/init")
    public RespBean init(){
        Map<String, Object> initMap = new HashMap<>(5);
        initMap.put("nation", new ArrayList<>());

        return RespBean.ok(BusinessEnum.SERVER_SUCCESS, initMap);
    }

    @GetMapping(value = "/list")
    public RespBean list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String keywords,
            String beginDateScope){

        return RespBean.ok(BusinessEnum.SERVER_SUCCESS, userService.getUserList());
    }

    @PutMapping
    public RespBean put(TUser user){
        return RespBean.ok(userService.updateUser(user));
    }
    @PostMapping
    public RespBean post(TUser user){
        return RespBean.ok(userService.addUser(user));
    }

    @GetMapping(value = "/nextUserId")
    public RespBean nextUserId(){
        return RespBean.ok(BusinessEnum.SERVER_SUCCESS,userIdWorker.getId());
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
