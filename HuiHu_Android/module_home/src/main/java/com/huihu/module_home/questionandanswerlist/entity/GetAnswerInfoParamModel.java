package com.huihu.module_home.questionandanswerlist.entity;

/**
 * create by ouyangjianfeng on 2019/4/1
 * description:
 */
public class GetAnswerInfoParamModel {
    private int agreeCount;
    private long lastTime;
    private int orderBy;
    private int pageSize;
    private long questionId;
    private int uid;

    public int getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(int agreeCount) {
        this.agreeCount = agreeCount;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(@Order int orderBy) {
        this.orderBy = orderBy;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
