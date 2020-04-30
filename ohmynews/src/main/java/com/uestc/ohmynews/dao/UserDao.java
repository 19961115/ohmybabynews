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
    @Insert({"insert into user(user_name,password,role,email)"})
    void addUser(User user);

//删
    //通过邮箱删除用户
    @Delete({"delete from user where email=#{email}"})
    void  deleteUserByEmail(String email);
    //通过id删除用户
    @Delete({"delete from user where user_id=#{user_id}"})
    void deleteUserById(int user_id);

//改

    //通过邮箱修改用户名
    @Update({"update user set user_name=#{user_name} where email=#{email}"})
    void updateUserNameByEmail(String user_name,String email);


    //通过邮箱修改密码
    @Update({"update user set password=#{password} where email=#{email}"})
    void updatePasswordByEmail(String password,String email);

//查

    //查询所有用户
    @Select({"select * from user"})
    List<User> findAllUser();

    //通过邮箱查找用户
    @Select({"select * from user where email=#{email}"})
    User findUSerByEmail(String email);

    //通过邮箱和密码查询
    @Select({"select * from user where email=#{email} and password=#{password}"})
    User findUserByEmailAndPassword(String email, String password);

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
    User getUserByUser_id(int user_id);


}