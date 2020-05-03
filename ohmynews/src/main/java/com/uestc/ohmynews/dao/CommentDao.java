package com.uestc.ohmynews.dao;

import com.uestc.ohmynews.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentDao {
//增
    //增加评论
    @Insert({"insert into comment(news_id,content,p_id,reply_user_id,user_id) " +
            "values(#{news_id},#{content},#{p_id},#{reply_user_id},#{user_id})"})
    void addComment(int news_id,String content,int p_id,int reply_user_id,int user_id);

//删
    //管理员进行评论删除
    @Delete({"delete from comment where comment_id=#{comment_id}"})
    void deleteCommentByComment_id(int comment_id);
    //用户自己删除新闻评论
    @Delete({"delete from comment where comment_id=#{comment_id} and user_id=#{user_id}"})
    void deleteCommentByComment_idAndUser_id(int comment_id,int user_id);

//查
    //通过用户id和新闻id查询评论
    @Select({"select * from comment where news_id=#{news_id} and user_id=#{user_id}"})
    Comment findCommentByNews_idAndUser_id(int news_id,int user_id);

    //通过评论回复id查询评论
    @Select({"select * from comment where reply_user_id=#{reply_user_id}"})
    List<Comment> findReplyCommentByReply_user_id(int reply_user_id);

    //通过新闻父id查询评论
    @Select({"select * from comment where p_id=#{p_id}"})
    List<Comment> findCommentdByP_id(int p_id);

}
