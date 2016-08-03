package com.sunday.goodhobby.goodhobby.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/22.
 */
public class Hobby implements Serializable{
    private String name;  //习惯名
    private  String createDate;   //创建日期
    private String description;   //习惯描述
    private int persistantDays;   //已经坚持天数
    private int grade;            //习惯当前等级
    public Hobby() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getPersistantDays() {
        return persistantDays;
    }

    public void setPersistantDays(int persistantDays) {
        this.persistantDays = persistantDays;
    }
}
