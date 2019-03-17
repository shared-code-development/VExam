package org.exam.service;

import lombok.extern.slf4j.Slf4j;
import org.exam.bean.entity.TRole;
import org.exam.bean.entity.TRoleExample;
import org.exam.mapper.TRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.service
 * @date 15/03/2019 22:56
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Slf4j
@Service
public class RoleService {
    @Autowired
    private TRoleMapper tRoleMapper;
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRole(Integer id){
        tRoleMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean addRole(TRole role){
        tRoleMapper.insertSelective(role);
        return true;
    }

    public List<TRole> roles(){
        return tRoleMapper.selectByExample(new TRoleExample());
    }
}
