package com.huihu.module_mine.attitude.entity;

import java.util.List;

public class AttitudeInfo {

    /**
     * pageDatas : [{"aboutContent":"string","aboutContentId":0,"aboutContentImages":[{"imageDesc":"string","imageId":0,"imageUrl":"string"}],"agreeCount":0,"circleId":0,"circleName":"string","commentCount":0,"commentsId":0,"content":"string","contentId":0,"contentImages":[{"imageDesc":"string","imageId":0,"imageUrl":"string"}],"contentUid":0,"createTime":0,"follow":0,"followCount":0,"opposeCount":0,"questionId":0,"title":"string","type":0,"userInfo":{"authBewrite":"string","authName":"string","follow":0,"fxCode":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"},"viewpoint":0,"viewpointUid":0,"viewpointsId":0}]
     * pageIndex : 0
     * pageSize : 0
     * totalCount : 0
     * totalPage : 0
     */

    private int pageIndex;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<PageDatasBean> pageDatas;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<PageDatasBean> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<PageDatasBean> pageDatas) {
        this.pageDatas = pageDatas;
    }

    public static class PageDatasBean {
        /**
         * aboutContent : string
         * aboutContentId : 0
         * aboutContentImages : [{"imageDesc":"string","imageId":0,"imageUrl":"string"}]
         * agreeCount : 0
         * circleId : 0
         * circleName : string
         * commentCount : 0
         * commentsId : 0
         * content : string
         * contentId : 0
         * contentImages : [{"imageDesc":"string","imageId":0,"imageUrl":"string"}]
         * contentUid : 0
         * createTime : 0
         * follow : 0
         * followCount : 0
         * opposeCount : 0
         * questionId : 0
         * title : string
         * type : 0
         * userInfo : {"authBewrite":"string","authName":"string","follow":0,"fxCode":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
         * viewpoint : 0
         * viewpointUid : 0
         * viewpointsId : 0
         */

        private String aboutContent;
        private int aboutContentId;
        private int agreeCount;
        private int circleId;
        private String circleName;
        private int commentCount;
        private int commentsId;
        private String content;
        private int contentId;
        private int contentUid;
        private long createTime;
        private int follow;
        private int followCount;
        private int opposeCount;
        private int questionId;
        private String title;
        private int type;
        private UserInfoBean userInfo;
        private int viewpoint;
        private int viewpointUid;
        private int viewpointsId;
        private List<AboutContentImagesBean> aboutContentImages;
        private List<ContentImagesBean> contentImages;

        public String getAboutContent() {
            return aboutContent;
        }

        public void setAboutContent(String aboutContent) {
            this.aboutContent = aboutContent;
        }

        public int getAboutContentId() {
            return aboutContentId;
        }

        public void setAboutContentId(int aboutContentId) {
            this.aboutContentId = aboutContentId;
        }

        public int getAgreeCount() {
            return agreeCount;
        }

        public void setAgreeCount(int agreeCount) {
            this.agreeCount = agreeCount;
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

        public int getCommentsId() {
            return commentsId;
        }

        public void setCommentsId(int commentsId) {
            this.commentsId = commentsId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getContentId() {
            return contentId;
        }

        public void setContentId(int contentId) {
            this.contentId = contentId;
        }

        public int getContentUid() {
            return contentUid;
        }

        public void setContentUid(int contentUid) {
            this.contentUid = contentUid;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getFollow() {
            return follow;
        }

        public void setFollow(int follow) {
            this.follow = follow;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public int getOpposeCount() {
            return opposeCount;
        }

        public void setOpposeCount(int opposeCount) {
            this.opposeCount = opposeCount;
        }

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getViewpointUid() {
            return viewpointUid;
        }

        public void setViewpointUid(int viewpointUid) {
            this.viewpointUid = viewpointUid;
        }

        public int getViewpointsId() {
            return viewpointsId;
        }

        public void setViewpointsId(int viewpointsId) {
            this.viewpointsId = viewpointsId;
        }

        public List<AboutContentImagesBean> getAboutContentImages() {
            return aboutContentImages;
        }

        public void setAboutContentImages(List<AboutContentImagesBean> aboutContentImages) {
            this.aboutContentImages = aboutContentImages;
        }

        public List<ContentImagesBean> getContentImages() {
            return contentImages;
        }

        public void setContentImages(List<ContentImagesBean> contentImages) {
            this.contentImages = contentImages;
        }

        public static class UserInfoBean {
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

        public static class AboutContentImagesBean {
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

        public static class ContentImagesBean {
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
