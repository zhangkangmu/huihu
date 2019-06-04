package com.huihu.module_mine.loginbyphone.entity;

/**
 * create by ouyangjianfeng on 2019/3/25
 * description:
 */
public class LoginReturnModel {

    /**
     * forbid : 0
     * fxCode : 882075
     * nickName : 欧阳阳
     * phoneNumber : 187****5864
     * uid : 283977
     * userHeadImg : https://fxchatimage.fx110.com/headdefault/3.jpg
     */

    private int forbid;
    private String fxCode;
    private String nickName;
    private String phoneNumber;
    private int uid;
    private String userHeadImg;

    public int getForbid() {
        return forbid;
    }

    public void setForbid(int forbid) {
        this.forbid = forbid;
    }

    public String getFxCode() {
        return fxCode;
    }

    public void setFxCode(String fxCode) {
        this.fxCode = fxCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }
}
