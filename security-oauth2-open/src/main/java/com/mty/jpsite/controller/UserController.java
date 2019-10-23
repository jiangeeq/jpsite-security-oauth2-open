package com.mty.jpsite.controller;

import com.mty.jpsite.entity.User;
import com.mty.jpsite.mapper.UserMapper;
import com.mty.jpsite.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/10/2315:22
 */
@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleMapper userRoleMapper;


    @PostMapping("/addUser")
    public String addUser(User user){
        user.setId(userMapper.getMaxId() + 1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateDate(new Date());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        userMapper.insert(user);
        userRoleMapper.insert(user.getId(), 1);
        return "addUser";
    }
}
