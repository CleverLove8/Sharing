package com.example.liwensheng.sharing.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by liWensheng on 2017/3/17.
 */

public class Friend extends BmobObject {
    private UserEntity user;
    private UserEntity friendUser;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(UserEntity friendUser) {
        this.friendUser = friendUser;
    }
}
