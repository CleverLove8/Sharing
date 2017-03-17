package com.example.liwensheng.sharing.entity;

import cn.bmob.v3.BmobObject;

/**
 * Created by liWensheng on 2017/3/15.
 */

public class CollectEntity extends BmobObject {
    private UserEntity userEntity;
    private ThingEntity thingEntity;

    public void setUserEntity(UserEntity userEntity) {this.userEntity = userEntity;}
    public UserEntity getUserEntity() {return userEntity;}
    public void setThingEntity(ThingEntity thingEntity) {this.thingEntity = thingEntity;}
    public ThingEntity getThingEntity() {return thingEntity;}
}
