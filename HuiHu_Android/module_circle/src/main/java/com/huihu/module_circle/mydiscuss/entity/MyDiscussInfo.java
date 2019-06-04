package com.huihu.module_circle.mydiscuss.entity;

import java.util.List;

public class MyDiscussInfo {

    /**
     * pageDatas : [{"agreeCount":0,"briefContent":"string","circleId":0,"circleName":"string","commentCount":0,"createTime":0,"discussTime":0,"hold":0,"ideaId":0,"images":[{"height":0,"imageDesc":"string","imageId":0,"imageUrl":"string","width":0}],"popular":0,"state":0,"title":"string","userInfo":{"authBewrite":"string","authName":"string","follow":0,"incMax":"string","incMin":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}}]
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
         * briefContent : string
         * circleId : 0
         * circleName : string
         * commentCount : 0
         * createTime : 0
         * discussTime : 0
         * hold : 0
         * ideaId : 0
         * images : [{"height":0,"imageDesc":"string","imageId":0,"imageUrl":"string","width":0}]
         * popular : 0
         * state : 0
         * title : string
         * userInfo : {"authBewrite":"string","authName":"string","follow":0,"incMax":"string","incMin":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
         */

        private int agreeCount;
        private String briefContent;
        private int circleId;
        private String circleName;
        private int commentCount;
        private long createTime;
        private long discussTime;
        private int hold;
        private long ideaId;
        private int popular;
        private int state;
        private String title;
        private UserInfoBean userInfo;
        private List<ImagesBean> images;

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

        public int getCircleId() {
            return circleId;
        }

        public void setCircleId(int circleId) {
            this.circleId = circleId;
        }

        public String getCircleName() {
            return circleName;
        }

        public void setCircleName(String circleName) {
            this.circleName = circleName;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getDiscussTime() {
            return discussTime;
        }

        public void setDiscussTime(long discussTime) {
            this.discussTime = discussTime;
        }

        public int getHold() {
            return hold;
        }

        public void setHold(int hold) {
            this.hold = hold;
        }

        public long getIdeaId() {
            return ideaId;
        }

        public void setIdeaId(long ideaId) {
            this.ideaId = ideaId;
        }

        public int getPopular() {
            return popular;
        }

        public void setPopular(int popular) {
            this.popular = popular;
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

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
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
            private long uid;
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

            public long getUid() {
                return uid;
            }

            public void setUid(long uid) {
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
             * height : 0
             * imageDesc : string
             * imageId : 0
             * imageUrl : string
             * width : 0
             */

            private int height;
            private String imageDesc;
            private int imageId;
            private String imageUrl;
            private int width;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

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

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }
    }
}
