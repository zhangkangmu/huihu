package com.huihu.module_notification.reply.entity;

import com.huihu.module_notification.entity.NetImageBean;
import com.huihu.module_notification.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wangjing on 2019/4/8 0008
 * description:
 */
public class ReplyBean {

    /**
     * agreeCount : 0
     * briefContent : 2423423423423
     * commentCount : 0
     * editTime : 1554693690865
     * follow : null
     * ideaId : 332
     * images : []
     * messageStatus : 1
     * opposeCount : 0
     * question : {"briefContent":"string","editTime":1554371761929,"follow":null,"ideaId":309,"images":[{"height":null,"imageDesc":"string","imageId":508,"imageUrl":"string","width":null}],"title":"的沙发发?","userInfo":{"authBewrite":"知名外汇操盘手","authName":"wh","follow":0,"incMax":null,"incMin":null,"nickName":"度假酒店家的基督","uid":284002,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/34_120.jpg"}}
     * userInfo : {"authBewrite":null,"authName":null,"follow":0,"incMax":null,"incMin":null,"nickName":"FZW912518","uid":284066,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/39_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/39_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/39_120.jpg"}
     */

    private int agreeCount;
    private String briefContent;
    private int commentCount;
    private long editTime;
    private int follow;
    private long ideaId;
    private int messageStatus;
    private int opposeCount;
    private QuestionBean question;
    private UserInfo userInfo;
    private List<NetImageBean> images;

    public int getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(int agreeCount) {
        this.agreeCount = agreeCount;
    }

    public String getBriefContent() {
        return briefContent;
    }

    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public long getEditTime() {
        return editTime;
    }

    public void setEditTime(long editTime) {
        this.editTime = editTime;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public int getOpposeCount() {
        return opposeCount;
    }

    public void setOpposeCount(int opposeCount) {
        this.opposeCount = opposeCount;
    }

    public QuestionBean getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBean question) {
        this.question = question;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<NetImageBean> getImages() {
        return images;
    }

    public void setImages(List<NetImageBean> images) {
        this.images = images;
    }

    private List<String> urls;

    public List<String> getUrls(){
        if (urls == null){
            urls = new ArrayList<>();
            for (NetImageBean bean : images){
                urls.add(bean.getImageUrl());
            }
        }
        return urls;
    }

    public static class QuestionBean {
        /**
         * briefContent : string
         * editTime : 1554371761929
         * follow : null
         * ideaId : 309
         * images : [{"height":null,"imageDesc":"string","imageId":508,"imageUrl":"string","width":null}]
         * title : 的沙发发?
         * userInfo : {"authBewrite":"知名外汇操盘手","authName":"wh","follow":0,"incMax":null,"incMin":null,"nickName":"度假酒店家的基督","uid":284002,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/34_120.jpg"}
         */

        private String briefContent;
        private long editTime;
        private int follow;
        private int ideaId;
        private String title;
        private UserInfo userInfo;
        private List<NetImageBean> images;

        public String getBriefContent() {
            return briefContent;
        }

        public void setBriefContent(String briefContent) {
            this.briefContent = briefContent;
        }

        public long getEditTime() {
            return editTime;
        }

        public void setEditTime(long editTime) {
            this.editTime = editTime;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getIdeaId() {
            return ideaId;
        }

        public void setIdeaId(int ideaId) {
            this.ideaId = ideaId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public List<NetImageBean> getImages() {
            return images;
        }

        public void setImages(List<NetImageBean> images) {
            this.images = images;
        }

    }
}
