package com.huihu.module_mine.answered.entity;

import java.util.List;

public class AnsweredInfo {

    /**
     * pageDatas : [{"agreeCount":0,"answer":0,"answerCount":0,"briefContent":"string","commentCount":0,"editTime":0,"followCount":0,"ideaId":0,"ideaType":0,"images":[{"imageDesc":"string","imageId":0,"imageUrl":"string"}],"popular":0,"questionId":0,"state":0,"title":"string","user":{"authBewrite":"string","authName":"string","follow":0,"fxCode":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}}]
     * pageSize : 0
     * timestamp : 0
     */

    private int pageSize;
    private long timestamp;
    private List<PageDatasBean> pageDatas;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<PageDatasBean> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<PageDatasBean> pageDatas) {
        this.pageDatas = pageDatas;
    }

    public static class PageDatasBean {
        /**
         * agreeCount : 0
         * answer : 0
         * answerCount : 0
         * briefContent : string
         * commentCount : 0
         * editTime : 0
         * followCount : 0
         * ideaId : 0
         * ideaType : 0
         * images : [{"imageDesc":"string","imageId":0,"imageUrl":"string"}]
         * popular : 0
         * questionId : 0
         * state : 0
         * title : string
         * user : {"authBewrite":"string","authName":"string","follow":0,"fxCode":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
         */

        private int agreeCount;
        private int answer;
        private int answerCount;
        private String briefContent;
        private int commentCount;
        private long editTime;
        private int followCount;
        private int ideaId;
        private int ideaType;
        private int popular;
        private int questionId;
        private int state;
        private String title;
        private UserBean user;
        private List<ImagesBean> images;

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

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public int getIdeaId() {
            return ideaId;
        }

        public void setIdeaId(int ideaId) {
            this.ideaId = ideaId;
        }

        public int getIdeaType() {
            return ideaType;
        }

        public void setIdeaType(int ideaType) {
            this.ideaType = ideaType;
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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class UserBean {
            /**
             * authBewrite : string
             * authName : string
             * follow : 0
             * fxCode : string
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
            private String fxCode;
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

            public String getFxCode() {
                return fxCode;
            }

            public void setFxCode(String fxCode) {
                this.fxCode = fxCode;
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

        public static class ImagesBean {
            /**
             * imageDesc : string
             * imageId : 0
             * imageUrl : string
             */

            private String imageDesc;
            private int imageId;
            private String imageUrl;

            public String getImageDesc() {
                return imageDesc;
            }

            public void setImageDesc(String imageDesc) {
                this.imageDesc = imageDesc;
            }

            public int getImageId() {
                return imageId;
            }

            public void setImageId(int imageId) {
                this.imageId = imageId;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }
    }
}
