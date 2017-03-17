package com.example.liwensheng.sharing.model;

import com.example.liwensheng.sharing.entity.Friend;
import com.example.liwensheng.sharing.entity.UserEntity;

import cn.bmob.newim.listener.BmobListener1;
import cn.bmob.v3.exception.BmobException;


/**
 * @author :smile
 * @project:QueryUserListener
 * @date :2016-02-01-16:23
 */
public abstract class QueryUserListener extends BmobListener1<UserEntity> {

    public abstract void done(UserEntity s, BmobException e);

    @Override
    protected void postDone(UserEntity o, BmobException e) {
        done(o, e);
    }
}
