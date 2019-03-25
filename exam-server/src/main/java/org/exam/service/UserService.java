package org.exam.service;

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

    public List<TUser> getUserList(){
        TUserExample userExample = new TUserExample();
        return tUserMapper.selectByExample(userExample);
    }

    public Boolean addUser(TUser user){
        if(1==tUserMapper.insertSelective(user)){
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
