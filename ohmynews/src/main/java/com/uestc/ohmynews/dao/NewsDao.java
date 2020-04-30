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
    @Insert({"insert into news(news_name,type,label,description,user_id)"})
void addNews(String news_name,String type,String label,String description,int user_id);


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
    //通过新闻id修改新闻类别
    @Update({"update news set type=#{type} where news_id=#{news_id}"})
    void updateNewsTypeByNews_id(String type,int news_id);
    //通过新闻id修改新闻标签
    @Update({"update news set label=#{label} where news_id=#{news_id}"})
    void updateNewsLabelByNews_id(String label,int news_id);


//查

    //实现一对多查询
    @Select("select * from news where user_id=#{user_id}")
    public Set<News> getNewsByUser_id(int user_id);

    //通过新闻名称查询新闻
    @Select({"select * from news where news_name=#{news_name}"})
    List<News> findNewsByNews_name(String news_name);
    //通过新闻标签查询新闻
    @Select({"select * from news where label=#{label}"})
    List<News>findNewsByLabel(String label);
    //通过新闻类别查询新闻
    @Select({"select * from news where type=#{type}"})
    List<News> findNewsByType(String type);
    //查询所有新闻
    @Select({"select * from news"})
    List<News>findAllNews();
    //通过用户id新闻
    @Select({"select * from news where user_id=#{user_id}"})
    List<News> findNewsByUser_id(int user_id);
}
