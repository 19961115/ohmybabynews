package com.uestc.ohmynews.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class Comment {
    private int comment_id;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date comment_create_time;
    //与用户多对一
    private int user_id;
    //与新闻实现多对一
    private int news_id;
    //实现评论功能
    private int p_id;
    private int reply_user_id;
    public int getP_id() {
        return p_id;
    }
    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getReply_user_id() {
        return reply_user_id;
    }

    public void setReply_user_id(int reply_user_id) {
        this.reply_user_id = reply_user_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public Date getComment_create_time() {
        return comment_create_time;
    }

    public void setComment_create_time(Date comment_create_time) {
        this.comment_create_time = comment_create_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
