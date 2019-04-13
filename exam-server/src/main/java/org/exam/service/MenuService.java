package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TMenu;
import org.exam.bean.entity.TMenuExample;
import org.exam.bean.entity.TUserExample;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.PageUtils;
import org.exam.config.IdKeyWorkerConfiguration;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
    UKeyWorker menuIdWorker;

    public PageInfo<List<TMenu>> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TMenuExample userExample = new TMenuExample();
        /*TMenuExample.Criteria criteria = userExample.createCriteria();
        if(Objects.nonNull(parentId)){
            criteria.andParentIdEqualTo(parentId);
            criteria.andMenuIdEqualTo(parentId)
        }*/
        return PageUtils.nullListHandler(tMenuMapper.selectByExample(userExample));
    }
    public List<TMenu> tree(Long parentId) {
        return tMenuMapper.getMenuByParentId(parentId);
    }

    public List<TMenu> getMenuTreeByUserId(Long userId, Long parentId) {
        if(null==parentId){
            parentId = -1L;
        }
        return tMenuMapper.getMenuByUserIdAndParentId(null, parentId);
    }
    public Boolean add(TMenu menu) {
        TMenu parentMenu = tMenuMapper.selectByPrimaryKey(menu.getParentId());
        menu.setMenuId(menuIdWorker.getId());
        menu.setUrl(parentMenu.getUrl());
        menu.setIconCls(parentMenu.getIconCls());
        menu.setIsDel(true);
        if (1 == tMenuMapper.insertSelective(menu)) {
            return true;
        }
        throw new BusinessException(BusinessEnum.DB_ADD_FAILURE);
    }

    public int delete(Long id) {
        try {
            return tMenuMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }

    public int delete(Long[] ids) {
        TMenuExample userExample = new TMenuExample();
        userExample.createCriteria().andMenuIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tMenuMapper.deleteByExample(userExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }

    public Boolean update(TMenu menu) {
        TMenuExample userExample = new TMenuExample();
        if (1 == tMenuMapper.updateByExampleSelective(menu, userExample)) {
            return true;
        }
        throw new BusinessException(BusinessEnum.DB_UPDATE_FAILURE);
    }
}
