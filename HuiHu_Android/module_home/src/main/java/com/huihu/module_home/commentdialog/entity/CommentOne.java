package com.huihu.module_home.commentdialog.entity;

import com.huihu.uilib.commentedit.entity.ImagesBean;
import com.huihu.uilib.commentedit.entity.UserInfoBean;

import java.util.List;

/**
 * Created by jiangwensong on 2019/3/30.
 * description：
 */
public class CommentOne {

    private int agreeCount;
    private String comment;
    private List<CommentTwo> commentChildsMongodbs;
    private long commentId;
    private long commentTime;
    private long ideaAuthId;
    private long ideaId;
    private List<ImagesBean> images;
    private int isAgree;
    private int replyNum;
    private long uid;
    private UserInfoBean userInfo;

    public int getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(int agreeCount) {
        this.agreeCount = agreeCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<CommentTwo> getCommentChildsMongodbs() {
        return commentChildsMongodbs;
    }

    public void setCommentChildsMongodbs(List<CommentTwo> commentChildsMongodbs) {
        this.commentChildsMongodbs = commentChildsMongodbs;
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

    public long getIdeaAuthId() {
        return ideaAuthId;
    }

    public void setIdeaAuthId(long ideaAuthId) {
        this.ideaAuthId = ideaAuthId;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
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

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
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
