package com.dj.chat.core.security.handler;


import com.alibaba.fastjson.JSON;
import com.dj.chat.main.bean.AjaxResponseBody;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:如果用户已经通过身份验证，试图访问受保护的(该用户没有权限的)资源
 */
@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("403");
        responseBody.setMsg("（禁止）权限不足，服务器拒绝请求！");

        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}