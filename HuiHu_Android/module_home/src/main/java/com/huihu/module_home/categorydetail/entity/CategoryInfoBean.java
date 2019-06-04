package com.huihu.module_home.categorydetail.entity;

/**
 * create by wangjing on 2019/4/1 0001
 * description:
 */
public class CategoryInfoBean {

    /**
     * answerNum : 0
     * category : string
     * categoryId : 0
     * follow : 0
     * followPeopleNum : 0
     * picture : string
     */

    private long answerNum;
    private String category;
    private int categoryId;
    private int follow;
    private long followPeopleNum;
    private String picture;

    public long getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(long answerNum) {
        this.answerNum = answerNum;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public long getFollowPeopleNum() {
        return followPeopleNum;
    }

    public void setFollowPeopleNum(long followPeopleNum) {
        this.followPeopleNum = followPeopleNum;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
