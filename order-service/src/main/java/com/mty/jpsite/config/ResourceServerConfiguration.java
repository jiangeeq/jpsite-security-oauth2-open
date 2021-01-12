package com.mty.jpsite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author jiangpeng
 * 资源服务器处理逻辑配置
 * @date 2019/10/1416:48
 */
@Configuration
// 开启资源服务器配置
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 对 api/order 等自定义请求进行拦截
        http.authorizeRequests().antMatchers("/api/order/**").access("#oauth2.hasScope('all')")
                // 拦截对 /api/product/的 get 请求
                .antMatchers(HttpMethod.GET,"/api/product/**").authenticated()
                // 拦截对 /api/trade/的 post 请求
                .antMatchers(HttpMethod.POST,"/api/trade/**").authenticated();
    }
}
