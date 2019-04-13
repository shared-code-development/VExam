//package org.exam.bean.dto;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
///**
// * @author heshiyuan
// * @description <p></p>
// * @path exam-server/org.exam.bean.dto
// * @date 30/03/2019 22:57
// * @github http://github.com/shiyuan2he
// * @email shiyuan4work@126.com
// * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
// * @price ¥5    微信：hewei1109
// */
//public class UserDetailInfo implements UserDetails {
//    private Long userId;
//    @JsonIgnore
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//    @JsonIgnore
//    @Override
//    public String getPassword() {
//        return null;
//    }
//    @JsonIgnore
//    @Override
//    public String getUsername() {
//        return null;
//    }
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//    @JsonIgnore
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//    @JsonIgnore
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
