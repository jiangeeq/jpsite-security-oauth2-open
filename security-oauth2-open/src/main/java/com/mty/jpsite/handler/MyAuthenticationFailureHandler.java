package com.mty.jpsite.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiangpeng
 * @Description
 * @date 2019/10/1215:18
 */
@Component
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException auth)
            throws IOException {
        log.info("用户认证失败");
        res.getWriter().println("user not exist");
    }

}
