package org.exam.service;

import org.exam.bean.entity.TMenu;
import org.exam.bean.entity.TMenuExample;
import org.exam.common.UserUtils;
import org.exam.mapper.TMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
@Service
public class MenuService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private TMenuMapper tMenuMapper;

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
}
