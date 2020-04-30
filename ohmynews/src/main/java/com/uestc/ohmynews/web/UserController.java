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
    public String postLogin(HttpServletRequest request ,
                                  @RequestParam("email") String email, @RequestParam("password") String password, ModelAndView mv){
        if(userDao.findUserByEmailAndPassword(email,MD5Util.encrypt(password))!=null){
            User user=userDao.findUserByEmailAndPassword(email,MD5Util.encrypt(password));
            //首先获取session
            HttpSession session = request.getSession();
            //往session中存入你想要的东西
            session.setAttribute("user",user);//存储session
            mv.addObject("message","登录成功！");
        }
        else{
            mv.addObject("message","邮箱或者密码错误!");
        }
        return "index";
    }


    //实现注册
    @GetMapping("/admin/register")
    public ModelAndView requestRegister(){
        return new ModelAndView("/admin/register");
    }

    @PostMapping("/admin/register/{user}")
    public ModelAndView postRegister(@PathVariable User user, ModelAndView mv){
        if(userDao.findUSerByEmail(user.getEmail())!=null){
            mv.addObject("message","邮箱已经被注册，请直接登录");
        }
        else{
            userDao.addUser(user);
            //将数据存储后再加密
          userDao.updatePasswordByEmail(MD5Util.encrypt(user.getPassword()),user.getEmail());
            mv.addObject("message","注册成功！");
        }
        return mv;
    }



    //实现用户名称修改
    @PostMapping("/admin/update/username/{user_name}/{email}")
    public ModelAndView updateUser_name(@PathVariable String user_name,@PathVariable String email, ModelAndView mv){
        if(userDao.findUserByUserName(user_name)!=null){
            mv.addObject("message","用户名已经被使用！");
        }
        else {
        userDao.updateUserNameByEmail(user_name,email);
            mv.addObject("message","用户名修改成功！");
        }
        return mv;
    }

    //实现用户密码修改
    @PostMapping("/admin/update/pa/{password}/{email}")
    public  ModelAndView updatePassword(@PathVariable String password,@PathVariable String email,ModelAndView mv){
        userDao.updatePasswordByEmail(MD5Util.encrypt(password),email);
        mv.addObject("message","用户密码修改成功！");
        return mv;
    }

}