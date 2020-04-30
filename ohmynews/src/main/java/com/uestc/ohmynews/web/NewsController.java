package com.uestc.ohmynews.web;

import com.uestc.ohmynews.dao.NewsDao;
import com.uestc.ohmynews.dao.UserDao;
import com.uestc.ohmynews.service.UserService;
import com.uestc.ohmynews.util.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class NewsController {
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

    //实现新闻发布功能
    @GetMapping("/news/input")
    public ModelAndView newsInput(ModelAndView mv){
        mv.setViewName("/news/newsInput");
        return mv;
    }
    @PostMapping("/news/input")
    public ModelAndView newsInput(@PathVariable String news_name,@PathVariable String label,
                                  @PathVariable String type,@PathVariable String description,ModelAndView mv){
        newsDao.addNews(news_name,type,label,description,getUser.getUser().getUser_id());
        mv.addObject("message","新闻发布成功！");
        mv.setViewName("/index");
        return  mv;
    }

    //实现新闻列表功能
    @GetMapping("/news/showAllNews")
    public ModelAndView newsShow(ModelAndView mv){
        mv.addObject("newsList",newsDao.findAllNews());
        mv.setViewName("/news/newsShow");
        return mv;
    }

    //实现查询当前用户发布新闻的功能
    @GetMapping("/news/showNewsByUser_id")
    public ModelAndView showNewsByUser_id(ModelAndView mv){
        mv.addObject("newsList",newsDao.findNewsByUser_id(getUser.getUser().getUser_id()));
        mv.setViewName("/news/nowUserNews");
        return mv;
    }
    //实现通过新闻类别查询新闻
    @GetMapping("/news/findNewsByType")
    public ModelAndView showNewsByType(ModelAndView mv,@PathVariable String type){
        mv.setViewName("/news/findNewsByType");
        mv.addObject("newsList",newsDao.findNewsByType(type));
        return mv;
    }
    //实现通过新闻标签查询新闻
    @GetMapping("/news/findNewsByLabel")
    public ModelAndView showNewsByLable(ModelAndView mv,@PathVariable String label){
        mv.setViewName("/news/findNewsByLabel");
        mv.addObject("newsList",newsDao.findNewsByLabel(label));
        return mv;
    }
    //通过新闻名称查询新闻
    @GetMapping("/news/findNewsByNews_name")
    public ModelAndView showNewsByNews_name(ModelAndView mv,@PathVariable String news_name){
        mv.setViewName("/news/findNewsByNews_name");
        mv.addObject("newsList",newsDao.findNewsByNews_name(news_name));
        return mv;
    }



}
