package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.dto.DicTreeNode;
import org.exam.bean.entity.TDic;
import org.exam.bean.entity.TDicType;
import org.exam.bean.entity.TDicTypeExample;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TDicMapper;
import org.exam.mapper.TDicTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author heshiyuan
 * @description <p>字典服务</p>
 * @path exam-server/org.exam.service
 * @date 04/03/2019 22:20
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class DicTypeService {
    @Autowired
    TDicTypeMapper tDicTypeMapper;
    @Autowired
    UKeyWorker dicTypeIdWorker;
    public TDicType get(Long dicTypeId){
        return tDicTypeMapper.selectByPrimaryKey(dicTypeId);
    }

    public PageInfo<TDicType> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TDicTypeExample userExample = new TDicTypeExample();
        return PageUtils.nullListHandler(tDicTypeMapper.selectByExample(userExample));
    }

    public List<DicTreeNode> treeList(Long parentId) {
        return tDicTypeMapper.getDicTypeTreeNode(parentId);
    }

    public Boolean add(TDicType dicType) {
        dicType.setDicTypeId(dicTypeIdWorker.getId());
        dicType.setCreator(1L);
        dicType.setUpdater(1L);
        if (1 == tDicTypeMapper.insertSelective(dicType)) {
            return true;
        }
        throw new BusinessException(BusinessEnum.DB_ADD_FAILURE);
    }

    public int delete(Long id) {
        try {
            return tDicTypeMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }

    public int delete(Long[] ids) {
        TDicTypeExample userExample = new TDicTypeExample();
        userExample.createCriteria().andDicTypeIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tDicTypeMapper.deleteByExample(userExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }

    public Boolean update(TDicType menu) {
        TDicTypeExample userExample = new TDicTypeExample();
        if (1 == tDicTypeMapper.updateByExampleSelective(menu, userExample)) {
            return true;
        }
        throw new BusinessException(BusinessEnum.DB_UPDATE_FAILURE);
    }
}
