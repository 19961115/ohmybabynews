package com.uestc.ohmynews.util;

import com.uestc.ohmynews.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface GetUser {
    public User getUser();
}
