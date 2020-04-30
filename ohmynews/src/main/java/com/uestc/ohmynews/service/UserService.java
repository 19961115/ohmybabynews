package com.uestc.ohmynews.service;

import com.uestc.ohmynews.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface UserService {
    //用户确定
    User userCheck(String email,String password);

    //用户邮箱返回
    User userEmailCheck(String email);

}
