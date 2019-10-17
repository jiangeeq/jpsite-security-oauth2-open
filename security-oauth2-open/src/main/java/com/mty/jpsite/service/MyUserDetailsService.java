package com.mty.jpsite.service;

import com.mty.jpsite.entity.Permission;
import com.mty.jpsite.entity.User;
import com.mty.jpsite.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jiangpeng
 * @Description UserDetailsService实现动态查询数据库验证账号
 * @date 2019/10/1216:12
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.根据数据库查询，用户是否登陆
        User user = userMapper.findByUsername(username);
        // 2.查询该用户信息权限
        if (user != null) {
            // 设置用户权限
            List<Permission> permissions = userMapper.findPermissionByUsername(username);
            log.info("用户信息权限：{}，权限：{}", username, permissions.toString());

            if (!CollectionUtils.isEmpty(permissions)) {
                List<GrantedAuthority> simpleGrantedAuthorities =
                        permissions.stream().map(permission -> new SimpleGrantedAuthority(permission.getPermTag())).collect(Collectors.toList());
                user.setAuthorities(simpleGrantedAuthorities);
            }
        }
        return user;
    }
}
