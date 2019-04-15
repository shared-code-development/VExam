package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.exam.bean.entity.TDic;
import org.exam.bean.entity.TDicExample;
import org.exam.common.IdGen.UKeyWorker;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TDicMapper;
import org.exam.mapper.TDicMapper;
import org.exam.mapper.TDicMapper;
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
public class DicService {
    @Autowired
    TDicMapper tDicMapper;
    @Autowired
    UKeyWorker dicIdWorker;
    public TDic get(Long dicId){
        return tDicMapper.selectByPrimaryKey(dicId);
    }

    public PageInfo<List<TDic>> list(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        TDicExample userExample = new TDicExample();
        return PageUtils.nullListHandler(tDicMapper.selectByExample(userExample));
    }

    public PageInfo<List<TDic>> treeList(Long parentId) {
        return PageUtils.nullListHandler(tDicMapper.getDicTreeList(parentId));
    }

    public Boolean add(TDic dic) {
        dic.setDicId(dicIdWorker.getId());
        dic.setCreator(1L);
        dic.setUpdater(1L);
        if (1 == tDicMapper.insertSelective(dic)) {
            return true;
        }
        throw new BusinessException(BusinessEnum.DB_ADD_FAILURE);
    }

    public int delete(Long id) {
        try {
            return tDicMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }

    public int delete(Long[] ids) {
        TDicExample userExample = new TDicExample();
        userExample.createCriteria().andDicIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tDicMapper.deleteByExample(userExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_DELETE_FAILURE);
        }
    }

    public Boolean update(TDic menu) {
        TDicExample userExample = new TDicExample();
        if (1 == tDicMapper.updateByExampleSelective(menu, userExample)) {
            return true;
        }
        throw new BusinessException(BusinessEnum.DB_UPDATE_FAILURE);
    }
}
