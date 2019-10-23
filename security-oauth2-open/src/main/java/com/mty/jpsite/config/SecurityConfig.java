package com.mty.jpsite.config;

import com.mty.jpsite.entity.Permission;
import com.mty.jpsite.handler.MyAuthenticationFailureHandler;
import com.mty.jpsite.handler.MyAuthenticationSuccessHandler;
import com.mty.jpsite.mapper.PermissionMapper;
import com.mty.jpsite.service.MyUserDetailsService;
import com.mty.jpsite.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/10/1214:51
 */
@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationFailureHandler failureHandler;
    @Autowired
    private MyAuthenticationSuccessHandler successHandler;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用户认证信息
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 实现动态账号与数据库关联 在该地方改为查询数据库
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return passwordEncoder.encode(rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return passwordEncoder.matches(rawPassword, encodedPassword);
            }
        });
    }

    /**
     * 配置HttpSecurity 拦截资源
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<Permission> permissions = permissionMapper.findAllPermission();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests =
                http.authorizeRequests();
        permissions.forEach(permission -> authorizeRequests.antMatchers(permission.getUrl()).hasAnyAuthority(permission.getPermTag()));

        authorizeRequests.antMatchers("/login").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/addUser").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/**").fullyAuthenticated().and().formLogin().loginPage("/login")
                .successHandler(successHandler).failureHandler(failureHandler)
                .and().csrf().disable();
    }

    /**
     * 授权中心管理器
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
