package org.exam.service;

import org.exam.bean.entity.TMenu;
import org.exam.bean.entity.TMenuExample;
import org.exam.bean.entity.TMenuRoleExample;
import org.exam.common.UserUtils;
import org.exam.mapper.TMenuMapper;
import org.exam.mapper.TMenuRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.service
 * @date 04/03/2019 22:20
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class MenuService {


    @Autowired
    private TMenuMapper tMenuMapper;
    @Autowired
    private TMenuRoleMapper tMenuRoleMapper;

    public List<TMenu> getMenusByUserId(){
        return tMenuMapper.getMenusByUserId(UserUtils.getCurrentUser().getUserId());
    }


    public List<TMenu> getMenuList(){
        TMenuExample menuExample = new TMenuExample();
        menuExample.createCriteria().andIsDelEqualTo((byte) 0);
        return tMenuMapper.selectByExample(menuExample);
    }

    public List<TMenu> getMenuByParentId(Integer parentId){
      try{
        return tMenuMapper.getMenusByParentId(parentId);
      }catch (Exception e){
          e.printStackTrace();
          return new ArrayList<>();
      }
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean addMenu(TMenu menu){
        if(tMenuMapper.insertSelective(menu)==1){
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean deleteMenu(Integer id){
        tMenuMapper.deleteByPrimaryKey(id);
        return true;
    }

    public Map<String, Object> getMenuTreeByRoleId(Integer parentId, Integer roleId){
        Map<String, Object> returnMap = new HashMap<>(4);
        returnMap.put("menuTrees", tMenuMapper.getMenusByParentId(parentId));

        TMenuRoleExample menuRoleExample = new TMenuRoleExample();
        menuRoleExample.createCriteria().andRoleIdEqualTo(roleId);
        returnMap.put("menuIds", tMenuRoleMapper.selectByExample(menuRoleExample).stream().map(entity -> entity.getMenuId()));
        return returnMap;
    }
}
