package com.example.liwensheng.sharing.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by liWensheng on 2017/3/10.
 */

public class CommentEntity extends BmobObject implements Serializable {
    private UserEntity userEntity;//发起评论的用户
    private ThingEntity thingEntity;//对应的事件
    private String comments;//
    private boolean read;//被评论用户是否阅读该条评论，用于消息通知

    public void setUserEntity(UserEntity userEntity) {this.userEntity = userEntity;}
    public UserEntity getUserEntity() {return userEntity;}
    public ThingEntity getThingEntity() {return thingEntity;}
    public void setThingEntity(ThingEntity t) { thingEntity = t;}
    public String getComments() {return comments;}
    public void setComments(String comments) {this.comments = comments;}
    public void setRead(boolean read) {this.read = read;}
    public boolean getRead() {return read;}
}
