package com.huihu.module_home.categorydetail.entity;

/**
 * create by wangjing on 2019/4/1 0001
 * description:
 */
public class UserInfoBean {
    /**
     * authBewrite : string
     * authName : string
     * follow : 0
     * fxCode : string
     * nickName : string
     * uid : 0
     * userHeadImage : string
     * userHeadImg_120 : string
     * userHeadImg_48 : string
     * userHeadImg_80 : string
     */

    private String authBewrite;
    private String authName;
    private int follow;
    private String fxCode;
    private String nickName;
    private long uid;
    private String userHeadImage;
    private String userHeadImg_120;
    private String userHeadImg_48;
    private String userHeadImg_80;

    public String getAuthBewrite() {
        return authBewrite;
    }

    public void setAuthBewrite(String authBewrite) {
        this.authBewrite = authBewrite;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
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

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserHeadImage() {
        return userHeadImage;
    }

    public void setUserHeadImage(String userHeadImage) {
        this.userHeadImage = userHeadImage;
    }

    public String getUserHeadImg_120() {
        return userHeadImg_120;
    }

    public void setUserHeadImg_120(String userHeadImg_120) {
        this.userHeadImg_120 = userHeadImg_120;
    }

    public String getUserHeadImg_48() {
        return userHeadImg_48;
    }

    public void setUserHeadImg_48(String userHeadImg_48) {
        this.userHeadImg_48 = userHeadImg_48;
    }

    public String getUserHeadImg_80() {
        return userHeadImg_80;
    }

    public void setUserHeadImg_80(String userHeadImg_80) {
        this.userHeadImg_80 = userHeadImg_80;
    }
}
