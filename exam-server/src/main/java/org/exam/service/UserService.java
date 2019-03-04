package org.exam.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.entity.TUser;
import org.exam.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
public class UserService implements UserDetailsService {
    @Autowired
    TUserMapper tUserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[username]:{}", username);
        if(StringUtils.isBlank(username)){
            throw new UsernameNotFoundException(username + "is not exists!");
        }
        TUser user = new TUser();
        user.setUsername(username);
//        List<TUser> userList = tUserMapper.selectBy(user);
        List<TUser> userList = tUserMapper.selectAll();
        if(null!=userList&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }
}
