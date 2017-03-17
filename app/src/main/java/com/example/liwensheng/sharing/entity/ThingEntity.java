package com.example.liwensheng.sharing.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by liWensheng on 2017/3/6.
 */

public class ThingEntity extends BmobObject  implements Serializable {
    private UserEntity author;
    private String name;
    private String content;
    private String location;
    private boolean send; // true for qiujie, false for jiechu
    private int type;//1.电子产品 2.图书 3.衣饰 4.生活用品
    private String time;
    private BmobFile img;
    private boolean over;

    public void setAuthor(UserEntity userEntity) {this.author = userEntity;}
    public UserEntity getAuthor() {return author;}
    public void setName(String name) {this.name = name;}
    public String getName() {return name;}
    public void setContent(String content) {this.content = content;}
    public String getContent() {return content;}
    public void setLocation(String location) {this.location = location;}
    public String getLocation() {return location;}
    public void setSend(boolean send) {this.send = send;}
    public boolean getSend() {return send;}
    public void setType(int i) {type = i;}
    public int getType() {return type;}
    public void setTime(String ti) {time = ti;}
    public String getTime() {return time;}
    public void setImg(BmobFile img) {this.img = img;}
    public BmobFile getImg() {return img;}
    public boolean getOver() {return over;}
    public void setOver(boolean flag) {over = flag;}
}
