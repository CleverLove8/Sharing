package com.example.liwensheng.sharing.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by liWensheng on 2017/3/30.
 */

public class CircleEntity extends BmobObject {
    private int type;
    private UserEntity author;
    private String detail;

    public void setType(int i) {
        type = i;
    }

    public int getType() {
        return type;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }
}
