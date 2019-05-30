package org.exam.bean.dto;

import lombok.Data;
import org.exam.bean.entity.TUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.bean.dto
 * @date 27/05/2019 09:22
 */
@Data
public class UserDetailDto extends TUser implements UserDetails {
    private java.util.Collection authorities;

    private java.lang.String password;

    java.lang.String username;

    boolean isAccountNonExpired = true;

    boolean isAccountNonLocked = true;

    boolean isCredentialsNonExpired = true;

    boolean isEnabled = true;
}
