package com.uestc.ohmynews.web;

import com.uestc.ohmynews.dao.NewsDao;
import com.uestc.ohmynews.dao.UserDao;
import com.uestc.ohmynews.entity.News;
import com.uestc.ohmynews.entity.User;
import com.uestc.ohmynews.service.UserService;
import com.uestc.ohmynews.util.GetUser;
import com.uestc.ohmynews.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Set;

@RestController
@CrossOrigin
public class UserController {
    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    UserDao userDao;
    @Autowired(required = false)
    NewsDao newsDao;
    @Autowired(required = false)
    GetUser getUser;
    @Autowired
    HttpServletRequest request;

    //实现登录
    @GetMapping("/admin/login")
    public ModelAndView getLogin(){
        return new ModelAndView("index");
    }
    @PostMapping("/admin/login/")
    public String postLogin(HttpServletRequest request ,@PathVariable String user_name,@PathVariable String password, ModelAndView mv){
        if(userDao.findUserByUser_nameAndPassword(user_name,MD5Util.encrypt(password))!=null){
            User user1=userDao.findUserByUser_nameAndPassword(user_name,MD5Util.encrypt(password));
            //首先获取session
            HttpSession session = request.getSession();
            //往session中存入你想要的东西
            session.setAttribute("user",user1);//存储session
            mv.addObject("message","登录成功！");
        }
        else{
            mv.addObject("message","用户名或密码错误!");
        }
        return "/";
    }


    //实现注册
    @GetMapping("/admin/register")
    public ModelAndView requestRegister(){
        return new ModelAndView("/index");
    }

    @PostMapping(value = "/admin/register",produces = "application/json;charset=UTF-8")
    public ModelAndView postRegister(@RequestParam String user_name,
                                     @RequestParam String password,
                                     ModelAndView mv){
        if(userDao.findUserByUserName(user_name)!=null){
            mv.addObject("message","用户名已被注册！");
        }
        else{
           userDao.addUser(user_name,MD5Util.encrypt(password),0);

            //将数据存储后再加密
            mv.addObject("message","注册成功！");
        }
        mv.setViewName("index");
        return mv;
    }

    //实现用户名称修改
    @PostMapping("/admin/update/{user_name}")
    public ModelAndView updateUser_name(@PathVariable String user_name, ModelAndView mv){
        if(userDao.findUserByUserName(user_name)!=null){
            mv.addObject("message","用户名已经被使用！");
        }
        else {
        userDao.updateUserNameByUser_id(user_name,getUser.getUser().getUser_id());
            mv.addObject("message","用户名修改成功！");
        }
        return mv;
    }

    //实现用户密码修改
    @PostMapping("/admin/update/pa/{password}")
    public  ModelAndView updatePassword(@PathVariable String password,ModelAndView mv){
        userDao.updatePasswordByUser_id(MD5Util.encrypt(password),getUser.getUser().getUser_id());
        mv.addObject("message","用户密码修改成功！");
        return mv;
    }

    //实现用户注销
    @GetMapping("/admin/deleteUserByUser_id")
    public ModelAndView deleteUserByUser_id(ModelAndView mv){
        userDao.deleteUserByUser_id(getUser.getUser().getUser_id());
        mv.addObject("message","用户已注销！");
        return mv;
    }


}