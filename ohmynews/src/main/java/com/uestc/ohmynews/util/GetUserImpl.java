package com.uestc.ohmynews.util;

import com.uestc.ohmynews.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
@Service
@Component
public class GetUserImpl implements GetUser{
    @Autowired
    HttpServletRequest request;

    @Override
    public User getUser() {
        return (User)request.getSession().getAttribute("user");
    }
}
