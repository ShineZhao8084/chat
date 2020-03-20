package com.dj.chat.core.security.handler;

import com.alibaba.fastjson.JSON;
import com.dj.chat.main.bean.AjaxResponseBody;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        AjaxResponseBody responseBody = new AjaxResponseBody();

        if (e instanceof BadCredentialsException) {
            responseBody.setMsg("账户名或密码错误!");
        } else if (e instanceof DisabledException) {
            responseBody.setMsg("账号未启用！请联系管理员");
        } else if (e instanceof LockedException) {
            responseBody.setMsg("账号已锁定！请联系管理员解锁");
        } else {
            responseBody.setMsg("登陆失败！");
        }

        responseBody.setStatus("400");

        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
