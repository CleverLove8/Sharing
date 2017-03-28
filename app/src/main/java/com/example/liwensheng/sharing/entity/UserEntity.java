package com.example.liwensheng.sharing.entity;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by liWensheng on 2017/3/4.
 */

public class UserEntity extends BmobUser implements Serializable {
    private String User_name;
    private String User_phoneNum;
    private String User_qq;
    private int User_credit;
    private String User_Position;
    private String sex;
    private String birth;
    private BmobFile User_icon;

    public void setUser_name(String name) {
        setUsername(name);
        User_name = name;
    }
    public String getUser_name() {return User_name;}
    public void setUser_phoneNum(String phoneNum){
        setMobilePhoneNumber(phoneNum);
        setMobilePhoneNumberVerified(true);
        User_phoneNum = phoneNum;
    }

    public String getUser_phoneNum() {return BmobUser.getCurrentUser().getMobilePhoneNumber();}
    public void setUser_qq(String qq) {this.User_qq = qq;}
    public String getUser_qq() {return  User_qq;}
    public void setUser_credit(int credit){this.User_credit = credit;}
    public int getUser_credit() {return User_credit;}
    public void setUser_Position(String position) {this.User_Position = position;}
    public String getUser_Position() {return User_Position;}
    public void setUser_icon(BmobFile icon) {this.User_icon = icon;}
    public BmobFile getUser_icon() {return User_icon;}
    public String getSex() {return sex;}
    public void  setSex(String str) {sex = str;}
    public String getBirth() {return birth;}
    public void setBirth(String birth) {this.birth = birth;}
}
