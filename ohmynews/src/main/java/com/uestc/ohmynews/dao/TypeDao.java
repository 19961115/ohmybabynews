package com.uestc.ohmynews.dao;

import com.uestc.ohmynews.entity.Type;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TypeDao {
//增

    //增加新闻类型
    @Insert({"insert into type(type_name) values(#{type_name})"})
    void addTypeByType_name(String type_name);

//删

    //通过新闻类别删除新闻
    @Delete({"delete from type where type_name=#{type_name}"})
    void deleteType_nameByType_name(String type_name);


//改



//查
    //查询所有新闻类别
    @Select({"select * from type"})
    List<Type> findAllType();
    //通过类别id查询类别
    @Select({"select * from type where type_id=#{type_id}"})
    Type findTypeByType_id(int type_id);

    //实现一个类别查询多条新闻
    @Select({"select * from type where type_id=#{type_id}"})
    @Results({
            @Result(id = true,column="type_id",property="type_id"),
            @Result(column="type_name",property="type_name"),
            @Result(column="type_id",property="news",
                    many=@Many(select="com.uestc.ohmynews.dao.NewsDao.getNewsByType_id",
                            fetchType= FetchType.EAGER)
            )
    })
    Type getNewsByType_id(int type_id);
}
