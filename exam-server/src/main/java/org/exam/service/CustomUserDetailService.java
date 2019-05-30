package org.exam.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.exam.bean.dto.UserDetailDto;
import org.exam.bean.entity.TUser;
import org.exam.mapper.TUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.service
 * @date 27/05/2019 09:17
 */
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TUserMapper tUserMapper;
     @Override
    public UserDetailDto loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[username]:{}", username);
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException(username + " is not exists!");
        }
        TUser user = tUserMapper.loadUserByUsername(username);
        UserDetailDto userDetailDto = new UserDetailDto();
        BeanUtils.copyProperties(user, userDetailDto);
        userDetailDto.setAuthorities(AuthorityUtils.createAuthorityList("exam"));
        userDetailDto.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDetailDto;
    }
}
