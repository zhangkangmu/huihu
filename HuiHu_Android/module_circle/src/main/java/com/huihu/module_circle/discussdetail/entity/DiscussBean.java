package com.huihu.module_circle.discussdetail.entity;

import com.huihu.uilib.commentedit.entity.ImagesBean;
import com.huihu.uilib.commentedit.entity.UserInfoBean;

import java.util.List;

public class DiscussBean {


    /**
     * agreeCount : 0
     * circleId : 0
     * circleName : string
     * commentCount : 0
     * content : string
     * editTime : 0
     * hold : 0
     * ideaId : 0
     * images : [{"height":0,"imageDesc":"string","imageId":0,"imageUrl":"string","width":0}]
     * opposeCount : 0
     * popular : 0
     * state : 0
     * title : string
     * userInfo : {"authBewrite":"string","authName":"string","follow":0,"incMax":"string","incMin":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
     * viewpoint : 0
     */

    private long agreeCount;
    private long circleId;
    private String circleName;
    private long commentCount;
    private String content;
    private long editTime;
    private int hold;
    private long ideaId;
    private long opposeCount;
    private int popular;
    private int state;
    private String title;
    private UserInfoBean userInfo;
    private int viewpoint;
    private List<ImagesBean> images;

    public long getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(long agreeCount) {
        this.agreeCount = agreeCount;
    }

    public long getCircleId() {
        return circleId;
    }

    public void setCircleId(long circleId) {
        this.circleId = circleId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getEditTime() {
        return editTime;
    }

    public void setEditTime(long editTime) {
        this.editTime = editTime;
    }

    public int getHold() {
        return hold;
    }

    public void setHold(int hold) {
        this.hold = hold;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }

    public long getOpposeCount() {
        return opposeCount;
    }

    public void setOpposeCount(long opposeCount) {
        this.opposeCount = opposeCount;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(int popular) {
        this.popular = popular;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public int getViewpoint() {
        return viewpoint;
    }

    public void setViewpoint(int viewpoint) {
        this.viewpoint = viewpoint;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

}
