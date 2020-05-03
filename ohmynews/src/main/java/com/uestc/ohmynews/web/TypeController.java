package com.uestc.ohmynews.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.uestc.ohmynews.dao.TypeDao;
import com.uestc.ohmynews.entity.News;
import com.uestc.ohmynews.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class TypeController {
    @Autowired(required = false)
    TypeDao typeDao;

    //增加新闻类型
    @GetMapping("/news/addNews_type")
    public ModelAndView addNews_type(@PathVariable String type_name,ModelAndView mv){
        typeDao.addTypeByType_name(type_name);
        mv.setViewName("/type/addType");
        return mv;
    }
    //通过类别名称删除新闻类别
    @GetMapping("/news/type/deleteType_nameByType_name")
    public ModelAndView deleteType_nameByType_name(@PathVariable String type_name,ModelAndView mv){
        typeDao.deleteType_nameByType_name(type_name);
        mv.setViewName("/news/deleteType_nameByType_name");
        return mv;
    }
    //通过新闻类别查询新闻
    @GetMapping("/news/findNewsByNews_type/{pageNum}/{pageSize}")
    public ModelAndView findNewsByNews_type(ModelAndView mv, @PathVariable int type_id,
                                            @PathVariable int pageNum,@PathVariable int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<News>newsList= (List<News>) typeDao.findTypeByType_id(type_id);
        PageInfo<News>pageInfo=new PageInfo<>(newsList);
        mv.addObject("newsList",pageInfo);
        mv.setViewName("/");
        return mv;
    }
    //查询所有新闻类别
    @GetMapping("/news/findAllType/{pageNum}/{pageSize}")
    public ModelAndView findAllType(ModelAndView mv,@PathVariable int pageNum,@PathVariable int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Type>typeList=typeDao.findAllType();
        PageInfo<Type>pageInfo=new PageInfo<>(typeList);
        mv.addObject("typeList",pageInfo);
        mv.setViewName("/");
        return mv;
    }

}
