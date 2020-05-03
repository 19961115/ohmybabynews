package com.uestc.ohmynews.dao;

import com.uestc.ohmynews.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {
//增
//添加用户信息
    @Insert({"insert into user(user_name,password,role) values(#{user_name},#{password},#{role})"})
    void addUser(String user_name,String password,int role);

//删
    //通过邮箱删除用户
    @Delete({"delete from user where email=#{email}"})
    void  deleteUserByEmail(String email);
    //通过id删除用户
    @Delete({"delete from user where user_id=#{user_id}"})
    void deleteUserByUser_id(int user_id);

//改

    //通过邮箱修改用户名
    @Update({"update user set user_name=#{user_name} where user_id=#{user_id}"})
    void updateUserNameByUser_id(String user_name,int user_id);


    //通过邮箱修改密码
    @Update({"update user set password=#{password} where user_id=#{user_id}"})
    void updatePasswordByUser_id(String password,int user_id);

//查

    //查询所有用户
    @Select({"select * from user"})
    List<User> findAllUser();

    //通过邮箱查找用户
    @Select({"select * from user where email=#{email}"})
    User findUSerByEmail(String email);

    //通过用户名和密码查询
    @Select({"select * from user where user_name=#{user_name} and password=#{password}"})
    User findUserByUser_nameAndPassword(String user_name, String password);

    //通过用户id查找用户
    @Select({"select * from user where user_id=#{user_id}"})
    User findUserByid(int user_id);

    //通过用户名查找用户
    @Select({"select * from user where user_name=#{user_name}"})
    User findUserByUserName(String user_name);




    //实现一个用户id查找多个新闻
    @Select({"select * from user where user_id = #{user_id}"})
    @Results({
            @Result(id = true,column="user_id",property="user_id"),
            @Result(column="user_name",property="user_name"),
            @Result(column="user_id",property="news",
                    many=@Many(select="com.uestc.ohmynews.dao.NewsDao.getNewsByUser_id",
                            fetchType= FetchType.EAGER)
            )
    })
    User getNewsByUser_id(int user_id);


}