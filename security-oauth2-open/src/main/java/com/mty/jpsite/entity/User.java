package com.mty.jpsite.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * 用户信息表
 * @author jiangpeng
 */
@Data
public class User implements UserDetails {
    private Integer id;
    private String username;
    private String realname;
    private String password;
    private Date createDate;
    private Date lastLoginTime;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    /** 用户所有权限 */
    private List<GrantedAuthority> authorities = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}
