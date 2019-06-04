package com.huihu.module_circle.discussdetail.entity;

import com.huihu.uilib.commentedit.entity.ImagesBean;
import com.huihu.uilib.commentedit.entity.UserInfoBean;

import java.util.List;

/**
 * Created by jiangwensong on 2019/3/30.
 * description：
 */
public class CommentTwo {
    private int agreeCount;
    private String atNickName;
    private long atUid;
    private String comment;
    private long commentChildId;
    private long commentId;
    private long commentTime;
    private int commentType;
    private long ideaAuthId;
    private List<ImagesBean> images;
    private int isAgree;
    private long uid;
    private UserInfoBean userInfo;

    public int getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(int agreeCount) {
        this.agreeCount = agreeCount;
    }

    public String getAtNickName() {
        return atNickName;
    }

    public void setAtNickName(String atNickName) {
        this.atNickName = atNickName;
    }

    public long getAtUid() {
        return atUid;
    }

    public void setAtUid(long atUid) {
        this.atUid = atUid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCommentChildId() {
        return commentChildId;
    }

    public void setCommentChildId(long commentChildId) {
        this.commentChildId = commentChildId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(long commentTime) {
        this.commentTime = commentTime;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
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

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }
}
