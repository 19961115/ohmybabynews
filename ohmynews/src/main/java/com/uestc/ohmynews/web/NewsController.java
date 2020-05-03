package com.uestc.ohmynews.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uestc.ohmynews.dao.NewsDao;
import com.uestc.ohmynews.dao.UserDao;
import com.uestc.ohmynews.entity.News;
import com.uestc.ohmynews.service.UserService;
import com.uestc.ohmynews.util.GetUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public ModelAndView newsInput(@PathVariable String news_name,@PathVariable String image,
                                @PathVariable String description,ModelAndView mv){
        newsDao.addNews(news_name,image,description,getUser.getUser().getUser_id());
        mv.addObject("message","新闻发布成功！");
        mv.setViewName("/index");
        return  mv;
    }

    //实现新闻列表功能
    @GetMapping("/news/showAllNews/{pageNum}/{pageSize}")
    public ModelAndView newsShow(ModelAndView mv,@PathVariable int pageNum,@PathVariable int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<News>newsList=newsDao.findAllNews();
        PageInfo<News>pageInfo=new PageInfo<>(newsList);
        mv.addObject("newsList",pageInfo);
        mv.setViewName("/news/newsshow");
        return mv;
    }

    //实现查询当前用户发布新闻的功能
    @GetMapping("/news/showNewsByUser_id/{pageNum}/{pageSize}")
    public ModelAndView showNewsByUser_id(ModelAndView mv,@PathVariable int pageNum,@PathVariable int pageSize){
        PageHelper.startPage(pageNum,pageSize);
       List<News>newsList=newsDao.findNewsByUser_id(getUser.getUser().getUser_id());
        PageInfo<News>pageInfo=new PageInfo<>(newsList);
        mv.addObject("newsList",pageInfo);
        mv.setViewName("/news/nowUserNews");
        return mv;
    }

    //通过新闻名称查询新闻
    @GetMapping("/news/findNewsByNews_name/{pageNum}/{pageSize}")
    public ModelAndView showNewsByNews_name(ModelAndView mv,@PathVariable String news_name,
                                            @PathVariable int pageNum,@PathVariable int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<News>newsList=newsDao.findNewsByNews_name(news_name);
        PageInfo<News>pageInfo=new PageInfo<>(newsList);
        mv.addObject("newsList",pageInfo);
        mv.setViewName("/news/findNewsByNews_name");
        return mv;
    }

    //实现新闻删除功能
       //通过新闻名称删除新闻
    @GetMapping("/news/deleteNewsByNews_name")
    public ModelAndView deleteNewsByNews_name(ModelAndView mv,@PathVariable String news_name){
        newsDao.deleteNewsByNews_name(news_name);
        mv.setViewName("/");
        mv.addObject("message","新闻删除成功！");
        return mv;
    }
        //通过新闻id删除新闻
    @GetMapping("/news/deleteNewsByNews_id/{news_id}")
    public ModelAndView deleteNewsByNews_id(ModelAndView mv,@PathVariable int news_id){
        newsDao.deleteNewsByNews_id(news_id);
        mv.setViewName("/");
        mv.addObject("message","新闻删除成功！");
        return  mv;
    }
}
