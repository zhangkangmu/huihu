package com.huihu.module_home.answerdetail.entity;

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
     * questionState : 0
     * state : 0
     * title : string
     * userInfo : {"authBewrite":"string","authName":"string","follow":0,"incMax":"string","incMin":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
     * viewpoint : 0
     */

    private long agreeCount;
    private int answer;
    private long answerCount;
    private int collection;
    private long commentCount;
    private String content;
    private long editTime;
    private long ideaId;
    private long opposeCount;
    private int popular;
    private long questionId;
    private int questionState;
    private int state;
    private String title;
    private UserInfoBean userInfo;
    private int viewpoint;

    public long getAgreeCount() {
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

    public long getAnswerCount() {
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

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
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

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }

    public long getOpposeCount() {
        return opposeCount;
    }

    public void setOpposeCount(long opposeCount) {
        this.opposeCount = opposeCount;
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

    public int getQuestionState() {
        return questionState;
    }

    public void setQuestionState(int questionState) {
        this.questionState = questionState;
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

    public int getViewpoint() {
        return viewpoint;
    }

    public void setViewpoint(int viewpoint) {
        this.viewpoint = viewpoint;
    }

    public static class UserInfoBean {
        /**
         * authBewrite : string
         * authName : string
         * follow : 0
         * incMax : string
         * incMin : string
         * nickName : string
         * uid : 0
         * userHeadImage : string
         * userHeadImg_120 : string
         * userHeadImg_48 : string
         * userHeadImg_80 : string
         */

        private String authBewrite;
        private String authName;
        private int follow;
        private String incMax;
        private String incMin;
        private String nickName;
        private int uid;
        private String userHeadImage;
        private String userHeadImg_120;
        private String userHeadImg_48;
        private String userHeadImg_80;

        public String getAuthBewrite() {
            return authBewrite;
        }

        public void setAuthBewrite(String authBewrite) {
            this.authBewrite = authBewrite;
        }

        public String getAuthName() {
            return authName;
        }

        public void setAuthName(String authName) {
            this.authName = authName;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public String getIncMax() {
            return incMax;
        }

        public void setIncMax(String incMax) {
            this.incMax = incMax;
        }

        public String getIncMin() {
            return incMin;
        }

        public void setIncMin(String incMin) {
            this.incMin = incMin;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUserHeadImage() {
            return userHeadImage;
        }

        public void setUserHeadImage(String userHeadImage) {
            this.userHeadImage = userHeadImage;
        }

        public String getUserHeadImg_120() {
            return userHeadImg_120;
        }

        public void setUserHeadImg_120(String userHeadImg_120) {
            this.userHeadImg_120 = userHeadImg_120;
        }

        public String getUserHeadImg_48() {
            return userHeadImg_48;
        }

        public void setUserHeadImg_48(String userHeadImg_48) {
            this.userHeadImg_48 = userHeadImg_48;
        }

        public String getUserHeadImg_80() {
            return userHeadImg_80;
        }

        public void setUserHeadImg_80(String userHeadImg_80) {
            this.userHeadImg_80 = userHeadImg_80;
        }
    }
}
