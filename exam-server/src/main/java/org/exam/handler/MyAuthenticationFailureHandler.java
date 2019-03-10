package org.exam.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.exam.bean.dto.RespBean;
import org.exam.enums.BusinessEnum;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path exam-server/org.exam.handler
 * @date 09/03/2019 16:30
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        RespBean respBean = null;
        if (e instanceof BadCredentialsException ||
                e instanceof UsernameNotFoundException) {
            respBean = RespBean.error(BusinessEnum.USERNAME_PASSWORD_ERROR);
        } else if (e instanceof LockedException) {
            respBean = RespBean.error(BusinessEnum.ACCOUNT_LOCKED_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            respBean = RespBean.error(BusinessEnum.PASSWORD_EXPIRE_ERROR);
        } else if (e instanceof AccountExpiredException) {
            respBean = RespBean.error(BusinessEnum.ACCOUNT_EXPIRE_ERROR);
        } else if (e instanceof DisabledException) {
            respBean = RespBean.error(BusinessEnum.ACCOUNT_DISABLE_ERROR);
        } else {
            respBean = RespBean.error(BusinessEnum.LOGIN_ERROR);
        }
        httpServletResponse.setStatus(401);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = httpServletResponse.getWriter();
        out.write(om.writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
