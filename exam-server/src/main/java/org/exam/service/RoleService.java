package org.exam.service;

import lombok.extern.slf4j.Slf4j;
import org.exam.bean.entity.*;
import org.exam.mapper.TMenuRoleMapper;
import org.exam.mapper.TRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

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
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Slf4j
@Service
public class RoleService {
    @Autowired
    private TRoleMapper tRoleMapper;
    @Autowired
    private TMenuRoleMapper tMenuRoleMapper;
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
    @Transactional(rollbackFor = Exception.class)
    public boolean updateMenusOfRole(Integer roleId, Integer[] menuIds){
        try{
            List<Integer> menuIdList = Arrays.asList(menuIds);
            TMenuRoleExample menuRoleExample = new TMenuRoleExample();
            menuRoleExample.createCriteria()
                    .andRoleIdEqualTo(roleId)
            ;
            tMenuRoleMapper.deleteByExample(menuRoleExample);
            /*menuRoleExample.clear();
            menuRoleExample.createCriteria().andMenuIdIn(Arrays.asList(menuIds)).andRoleIdEqualTo(roleId);

            Map<Integer, Integer> menuMap = tMenuRoleMapper.selectByExample(menuRoleExample)
                    .stream().collect(Collectors.toMap(TMenuRole::getMenuId,TMenuRole::getMenuId));
            ListIterator<Integer> listIterator = menuIdList.listIterator();
            List<Integer> newMenuList = new ArrayList<>();
            while (listIterator.hasNext()){
                if(null==menuMap.get(listIterator.next())){
                    newMenuList.add(listIterator.previous());
                }
            }*/
            if(null!=menuIdList&&menuIdList.size()>0){
                tMenuRoleMapper.batchInsert(roleId, menuIdList);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return true;
    }
}
