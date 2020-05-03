package com.uestc.ohmynews.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import java.util.Set;

public class Type {
    private int type_id;
    private String type_name;
    private Set<News> newsset;
    private int news_id;
    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Set<News> getNewsset() {
        return newsset;
    }

    public void setNewsset(Set<News> newsset) {
        this.newsset = newsset;
    }

    public int getNews_id() {
        return news_id;
    }

    public void setNews_id(int news_id) {
        this.news_id = news_id;
    }
}
