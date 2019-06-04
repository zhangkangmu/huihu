package com.huihu.module_home.commentdialog.entity;

import com.huihu.uilib.commentedit.entity.UserInfoBean;

public class AnswerInfo {

    /**
     * agreeCount : 0
     * answer : 0
     * answerCount : 0
     * collection : 0
     * commentCount : 0
     * content : string
     * editTime : 0
     * ideaId : 0
     * opposeCount : 0
     * popular : 0
     * questionId : 0
     * state : 0
     * title : string
     * userInfo : {"authBewrite":"string","authName":"string","follow":0,"fxCode":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
     */

    private int agreeCount;
    private int answer;
    private int answerCount;
    private int collection;
    private int commentCount;
    private String content;
    private long editTime;
    private int ideaId;
    private int opposeCount;
    private int popular;
    private int questionId;
    private int state;
    private String title;
    private UserInfoBean userInfo;

    public int getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(int agreeCount) {
        this.agreeCount = agreeCount;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
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

    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public int getOpposeCount() {
        return opposeCount;
    }

    public void setOpposeCount(int opposeCount) {
        this.opposeCount = opposeCount;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(int popular) {
        this.popular = popular;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
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


}
