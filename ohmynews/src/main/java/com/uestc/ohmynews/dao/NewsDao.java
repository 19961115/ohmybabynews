package com.uestc.ohmynews.dao;

import com.uestc.ohmynews.entity.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Mapper
@Component
public interface NewsDao {
//增
    //实现新闻的增加
    @Insert({"insert into news(news_name,image,description,user_id)"})
void addNews(String news_name,String image,String description,int user_id);


//删
    //通过新闻id删除新闻
    @Delete({"delete from news where news_id=#{news_id}"})
void deleteNewsByNews_id(int news_id);
    //通过新闻名称删除新闻
    @Delete({"delete from news where news_name=#{news_name}"})
void deleteNewsByNews_name(String news_name);



//改
    //通过新闻id修改新闻名称
    @Update({"update news set news_name=#{news_name} where news_id=#{news_id}"})
void updateNewsNameByNews_id(String news_name,int news_id);
    //通过新闻id修改新闻图片
    @Update({"update news set image=#{image} where news_id=#{news_id}"})
    void updateNewsImageByNews_id(String image,int news_id);



//查

    //实现与用户的一对多查询
    @Select("select * from news where user_id=#{user_id}")
     Set<News> getNewsByUser_id(int user_id);
    //实现与类别的一对多查询
    @Select({"select * form news where type_id=#{type_id}"})
    List<News> getNewsByType_id(int type_id);

    //通过新闻名称查询新闻
    @Select({"select * from news where news_name=#{news_name}"})
    List<News> findNewsByNews_name(String news_name);
    //查询所有新闻
    @Select({"select * from news"})
    List<News>findAllNews();
    //通过用户id新闻
    @Select({"select * from news where user_id=#{user_id}"})
    List<News> findNewsByUser_id(int user_id);

}
