package com.mty.jpsite.config;

import com.mty.jpsite.handler.MyAuthenticationFailureHandler;
import com.mty.jpsite.handler.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author jiangpeng
 * @Description web 安全拦截配置
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
    private PasswordEncoder passwordEncoder;

    /**
     * 用户认证信息
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 手动添加认证用户账号密码
        auth.inMemoryAuthentication().withUser("jpsite").password(passwordEncoder.encode("111111"))
                .authorities("showOrder","addOrder","updateOrder","deleteOrder");
    }

    /**
     * 配置HttpSecurity 拦截资源
     *
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests =
                http.authorizeRequests();
        // 拦截的url和需要的权限
        authorizeRequests.antMatchers("/addOrder").hasAnyAuthority("addOrder")
                .antMatchers("/showOrder").hasAnyAuthority("showOrder")
                .antMatchers("/updateOrder").hasAnyAuthority("updateOrder")
                .antMatchers("/deleteOrder").hasAnyAuthority("deleteOrder");

        authorizeRequests.antMatchers("/login").permitAll()
                .antMatchers("/oauth/**").permitAll()
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

    /**
     *  加密算法类
     * @return BCryptPasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
