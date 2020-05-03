package com.uestc.ohmynews.interceptor;

import com.uestc.ohmynews.util.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired(required = false)
    private GetUser getUser;
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (getUser.getUser() == null) {
            response.sendRedirect("/admin/login");
            return false;
        }
        return true;
    }
}
