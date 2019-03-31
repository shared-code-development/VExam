package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.TUser;
import org.exam.bean.entity.TUserExample;
import org.exam.common.PageUtils;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.service
 * @date 04/03/2019 22:15
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
//public class UserService implements UserDetailsService {
public class UserService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TUserMapper tUserMapper;

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    *//*    log.info("[username]:{}", username);
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException(username + " is not exists!");
        }
        TUser user = tUserMapper.loadUserByUsername(username);
        return user;*//*
    return null;
    }*/

    public PageInfo<List<TUser>> list(Integer pageNum, Integer pageSize, String keywords) {
        PageHelper.startPage(pageNum, pageSize);
        TUserExample userExample = new TUserExample();
        TUserExample.Criteria criteria = userExample.createCriteria();
        if (StringUtils.isNotBlank(keywords)) {
            criteria.andNameLike(keywords);
        }
        return PageUtils.nullListHandler(tUserMapper.selectByExample(userExample));
    }

    public Boolean add(TUser user) {
        if (1 == tUserMapper.insertSelective(user)) {
            return true;
        }
        throw new BusinessException(BusinessEnum.DB_ADD_FAILURE);
    }

    public int delete(Long id) {
        try {
            return tUserMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_ADD_FAILURE);
        }
    }

    public int delete(Long[] ids) {
        TUserExample userExample = new TUserExample();
        userExample.createCriteria().andUserIdIn(Arrays.asList(ids))
                .andIsDelEqualTo(Boolean.TRUE);
        try {
            return tUserMapper.deleteByExample(userExample);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessEnum.DB_ADD_FAILURE);
        }
    }

    public Boolean update(TUser user) {
        TUserExample userExample = new TUserExample();
        if (1 == tUserMapper.updateByExampleSelective(user, userExample)) {
            return true;
        }
        throw new BusinessException(BusinessEnum.DB_ADD_FAILURE);
    }
}
