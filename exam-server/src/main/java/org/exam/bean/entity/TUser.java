package org.exam.bean.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.exam.enums.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Data
public class TUser implements UserDetails {
    private Integer id;

    private String userId;

    private String name;

    private String phone;

    private String telePhone;

    private String address;

    private Byte isDel;

    private String username;

    private String password;

    private String userface;

    private String remark;

    private Long createTime;

    private Long updateTime;

    private Integer creater;

    private Integer updater;

    private Integer version;

    private List<TRole> roles;
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Optional.ofNullable(roles).orElse(createRoleList())
                .stream()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())))
                ;
        return authorities;
    }

    private List<TRole> createRoleList() {
        List<TRole> roleList = new ArrayList<>();
        TRole role = new TRole();
        role.setId(0);
        role.setName(RoleEnum.ADMIN.getCode());
        role.setNamezh(RoleEnum.ADMIN.getDescription());
        roleList.add(role);
        return roleList;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}