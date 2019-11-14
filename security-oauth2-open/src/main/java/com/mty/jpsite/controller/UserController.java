package com.mty.jpsite.controller;

import com.mty.jpsite.entity.OauthClientDetails;
import com.mty.jpsite.entity.User;
import com.mty.jpsite.entity.UserClientSecret;
import com.mty.jpsite.mapper.UserClientSecretMapper;
import com.mty.jpsite.mapper.UserMapper;
import com.mty.jpsite.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mty.jpsite.mapper.OauthClientDetailsMapper;

import java.util.Date;
import java.util.Random;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/10/2315:22
 */
@Controller
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;
    @Autowired
    private UserClientSecretMapper userClientSecretMapper;

    /**
     * 首页
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 自定义登陆页面
     *
     * @return String
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 注册新用户
     *
     * @return String
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * 注册用户
     *
     * @param user user
     * @return String
     */
    @PostMapping("/addUser")
    @ResponseBody
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

        return "注册用户成功";
    }

    /**
     * 申请获取客户ID和客户密钥
     *
     * @return String
     */
    @GetMapping("/registerClientId")
    @ResponseBody
    public String registerClientId() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal == null) {
            throw new NullPointerException("用户未登录");
        }

        Random random = new Random();
        OauthClientDetails clientDetails = new OauthClientDetails();
        clientDetails.setClientId("client_" + random.nextInt(999));
        clientDetails.setClientSecret(passwordEncoder.encode("123456"));
        clientDetails.setScope("all");
        clientDetails.setAuthorizedGrantTypes("password,client_credentials,refresh_token,authorization_code");
        clientDetails.setWebServerRedirectUri("http://localhost:8080/code");
        clientDetails.setAuthorities("ROLE_ADMIN, ROLE_USER");
        clientDetails.setAccessTokenValidity(7200);
        clientDetails.setRefreshTokenValidity(7200);
        oauthClientDetailsMapper.insert(clientDetails);

        UserClientSecret userClientSecret = new UserClientSecret(principal.getId(), clientDetails.getClientId());
        userClientSecretMapper.save(userClientSecret);
        return "申请客户ID和客户密钥成功";
    }
}
