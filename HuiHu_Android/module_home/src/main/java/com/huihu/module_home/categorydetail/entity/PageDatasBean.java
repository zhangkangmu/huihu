package com.huihu.module_home.categorydetail.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wangjing on 2019/4/1 0001
 * description:
 */
public class PageDatasBean {
    /**
     * agreeCount : 0
     * briefContent : string
     * commentCount : 0
     * editTime : 0
     * ideaId : 0
     * images : [{"imageDesc":"string","imageId":0,"imageUrl":"string"}]
     * popular : 0
     * questionId : 0
     * title : string
     * userInfo : {"authBewrite":"string","authName":"string","follow":0,"fxCode":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
     */

    private long agreeCount;
    private String briefContent;
    private long commentCount;
    private long editTime;
    private long ideaId;
    private int popular;
    private long questionId;
    private String title;
    private UserInfoBean userInfo;
    private List<ImagesBean> images;

    public long getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(long agreeCount) {
        this.agreeCount = agreeCount;
    }

    public String getBriefContent() {
        return briefContent;
    }

    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public long getEditTime() {
        return editTime;
    }

    public void setEditTime(long editTime) {
        this.editTime = editTime;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(int popular) {
        this.popular = popular;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
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

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }


    private List<String> urls;

    public List<String> getUrls(){
        if (urls == null){
            urls = new ArrayList<>();
            for (ImagesBean bean : images){
                urls.add(bean.getImageUrl());
            }
        }
        return urls;
    }
}
