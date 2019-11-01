package com.mty.jpsite.controller;

import com.mty.jpsite.entity.OauthClientDetails;
import com.mty.jpsite.entity.User;
import com.mty.jpsite.mapper.UserMapper;
import com.mty.jpsite.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mty.jpsite.mapper.OauthClientDetailsMapper;
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
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @PostMapping("/addUser")
    public String addUser(User user) {
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

    @GetMapping("/registClientId")
    public String registClientId() {
        OauthClientDetails clientDetails = new OauthClientDetails();
        clientDetails.setClientId("client_2");
        clientDetails.setClientSecret(passwordEncoder.encode("123456"));
        clientDetails.setScope("all");
        clientDetails.setAuthorizedGrantTypes("password,client_credentials,refresh_token,authorization_code");
        clientDetails.setWebServerRedirectUri("http://localhost:8080/code");
        clientDetails.setAuthorities("ROLE_ADMIN, ROLE_USER");
        clientDetails.setAccessTokenValidity(7200);
        clientDetails.setRefreshTokenValidity(7200);
        oauthClientDetailsMapper.insert(clientDetails);
        return "registClientId";
    }
}
