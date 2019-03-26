package org.exam.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.TUser;
import org.exam.bean.entity.TUserExample;
import org.exam.enums.BusinessEnum;
import org.exam.exception.BusinessException;
import org.exam.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class UserService implements UserDetailsService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    TUserMapper tUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[username]:{}", username);
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException(username + " is not exists!");
        }
        TUser user = tUserMapper.loadUserByUsername(username);
        return user;
    }

    public PageInfo<List<TUser>> getUserList(Integer pageNum, Integer pageSize,
                                             String keywords, String beginDateScope){
        PageHelper.startPage(pageNum, pageSize);
        TUserExample userExample = new TUserExample();
        TUserExample.Criteria criteria = userExample.createCriteria();
        if(StringUtils.isNotBlank(keywords)){
            criteria.andNameLike(keywords);
        }

        if(StringUtils.isNotBlank(beginDateScope)){
            criteria.andBirthdayGreaterThanOrEqualTo(0L);
        }
        List<TUser> list = tUserMapper.selectByExample(userExample);
        if(null!=list&&list.size()>0){
            return new PageInfo(list);
        }else{
            return new PageInfo<>(new ArrayList<>());
        }
    }

    public Boolean addUser(TUser user){
        if(1==tUserMapper.insertSelective(user)){
            return true;
        }
        throw new BusinessException(BusinessEnum.USER_ADD_FAILURE);
    }

    public Boolean deleteUser(String ids){
        TUserExample userExample = new TUserExample();
        List<Integer> idList = new ArrayList<>();
        for (String id : ids.split(",")){
            idList.add(Integer.parseInt(id));
        }
        userExample.createCriteria().andIdIn(idList)
                .andIsDelEqualTo((byte)0);
        if(tUserMapper.deleteByExample(userExample)>0){
            return true;
        }
        throw new BusinessException(BusinessEnum.USER_ADD_FAILURE);
    }

    public Boolean updateUser(TUser user){
        TUserExample userExample = new TUserExample();
        if(1==tUserMapper.updateByExampleSelective(user, userExample)){
            return true;
        }
        throw new BusinessException(BusinessEnum.USER_ADD_FAILURE);
    }
}
