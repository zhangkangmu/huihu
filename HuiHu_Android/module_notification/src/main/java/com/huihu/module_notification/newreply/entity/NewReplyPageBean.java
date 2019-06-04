package com.huihu.module_notification.newreply.entity;

import com.huihu.module_notification.entity.NetImageBean;
import com.huihu.module_notification.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wangjing on 2019/4/10 0010
 * description:
 */
public class NewReplyPageBean {

    /**
     * questionId : 107
     * recentAnswer : [{"agreeCount":0,"briefContent":"Q","commentCount":0,"editTime":1554362312724,"ideaId":296,"images":[],"userInfo":{"authBewrite":null,"authName":null,"follow":0,"incMax":null,"incMin":null,"nickName":"WBI953477","uid":284061,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/12_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/12_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/12_120.jpg"}},{"agreeCount":0,"briefContent":"121212","commentCount":0,"editTime":1554693447646,"ideaId":329,"images":[],"userInfo":{"authBewrite":null,"authName":null,"follow":0,"incMax":null,"incMin":null,"nickName":"FZW912518","uid":284066,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/39_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/39_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/39_120.jpg"}}]
     * title : U7535dfgsdfds?
     */

    private long questionId;
    private String title;
    private List<RecentAnswerBean> recentAnswer;

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

    public List<RecentAnswerBean> getRecentAnswer() {
        return recentAnswer;
    }

    public void setRecentAnswer(List<RecentAnswerBean> recentAnswer) {
        this.recentAnswer = recentAnswer;
    }

    public static class RecentAnswerBean {
        /**
         * agreeCount : 0
         * briefContent : Q
         * commentCount : 0
         * editTime : 1554362312724
         * ideaId : 296
         * images : []
         * userInfo : {"authBewrite":null,"authName":null,"follow":0,"incMax":null,"incMin":null,"nickName":"WBI953477","uid":284061,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/12_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/12_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/12_120.jpg"}
         */

        private int agreeCount;
        private String briefContent;
        private int commentCount;
        private long editTime;
        private long ideaId;
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

        public long getIdeaId() {
            return ideaId;
        }

        public void setIdeaId(long ideaId) {
            this.ideaId = ideaId;
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

    }
}
