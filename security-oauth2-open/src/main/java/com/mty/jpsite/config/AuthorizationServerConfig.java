package com.mty.jpsite.config;

import com.mty.jpsite.service.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

/**
 * @author jiangpeng
 * @Description 配置授权中心信息
 * @date 2019/10/1217:06
 */
@Configuration
// 开启认证授权中心
@EnableAuthorizationServer
@Slf4j
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ClientDetailsService clientDetailsService;

    /**
     * 添加商户信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
     * 设置token类型
     *
     * @param endpoints
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        // token 保存库
        endpoints.tokenStore(tokenStore)
                // 允许的令牌端点请求方法
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // 用于密码授予的AuthenticationManager。刷新token需要
                .authenticationManager(authenticationManager)
                // 有权限访问的用户
                .userDetailsService(userDetailsService);

        // 配置tokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 令牌存储的持久性策略。
        tokenServices.setTokenStore(endpoints.getTokenStore());
        // 是否支持刷新令牌。
        tokenServices.setSupportRefreshToken(false);
        // 客户端详细信息服务
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        // 访问令牌增强器，它将在新令牌被保存到令牌存储之前应用到新令牌。
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        // 访问令牌的默认有效性(以秒为单位)
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30));
        endpoints.tokenServices(tokenServices);
    }

    /**
     * 授权服务器安全配置
     * @param oauthServer 授权服务器安全配置server类
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        // 允许所有token 访问
        oauthServer.tokenKeyAccess("permitAll()")
                // 允许check_token访问
                .checkTokenAccess("permitAll()")
                // 允许表单认证
                .allowFormAuthenticationForClients();
    }

    /**
     * 处理一个 {@link Authentication} 请求在bean
     * @return AuthenticationManager
     */
    @Bean
    AuthenticationManager authenticationManager() {
        return authentication -> daoAuthenticationProvider().authenticate(authentication);
    }

    /**
     * Dao身份验证提供者
     * @return DaoAuthenticationProvider
     */
    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * j加密算法类
     * @return PasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        // 加密方式
        return new BCryptPasswordEncoder();
    }

    /**
     * token持久化策略
     * @return JdbcTokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    /**
     * 客户端信息持久化策略
     * @return JdbcClientDetailsService
     */
    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }
}
