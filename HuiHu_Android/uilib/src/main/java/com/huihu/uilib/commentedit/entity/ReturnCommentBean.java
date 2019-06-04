package com.huihu.uilib.commentedit.entity;

import java.util.List;

public class ReturnCommentBean {

    private long uid;
    private long ideaId;
    private long commentId;
    private String comment;
    private long agreeCount;
    private long replyNum;
    private long commentTime;
    private int isAgree;
    private long ideaAuthId;
    private List<ImagesBean> images;
    private UserInfoBean userInfo;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(long agreeCount) {
        this.agreeCount = agreeCount;
    }

    public long getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(long replyNum) {
        this.replyNum = replyNum;
    }

    public long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(long commentTime) {
        this.commentTime = commentTime;
    }

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    public long getIdeaAuthId() {
        return ideaAuthId;
    }

    public void setIdeaAuthId(long ideaAuthId) {
        this.ideaAuthId = ideaAuthId;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }
}
