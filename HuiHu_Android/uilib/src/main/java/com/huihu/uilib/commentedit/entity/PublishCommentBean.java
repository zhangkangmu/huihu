package com.huihu.uilib.commentedit.entity;

import java.util.List;

/**
 * Created by jiangwensong on 2019/4/3.
 * description：
 */


public class PublishCommentBean {
    /**
     * atCommentId : 0
     * atUid : 0
     * comment : string
     * commentGrade : 0
     * commentId : 0
     * commentIp : string
     * commentType : 0
     * ideaId : 0
     * images : [{"height":0,"imageDesc":"string","imageUrl":"string","width":0}]
     * uid : 0
     */

    private long atCommentId;
    private long atUid;
    private String comment;
    private int commentGrade;
    private long commentId;
    private String commentIp;
    private int commentType;
    private long ideaId;
    private long uid;
    private List<ImagesBean> images;

    public long getAtCommentId() {
        return atCommentId;
    }

    public void setAtCommentId(long atCommentId) {
        this.atCommentId = atCommentId;
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

    public int getCommentGrade() {
        return commentGrade;
    }

    public void setCommentGrade(int commentGrade) {
        this.commentGrade = commentGrade;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentIp() {
        return commentIp;
    }

    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp;
    }

    public int getCommentType() {
        return commentType;
    }

    public void setCommentType(int commentType) {
        this.commentType = commentType;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

}
