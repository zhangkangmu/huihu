package com.huihu.module_notification.commentdetails.entity;

import java.util.List;

public class CommentHead {

    /**
     * aboutContent : string
     * aboutId : 0
     * aboutState : 0
     * aboutType : 0
     * agreeCount : 0
     * comment : string
     * commentId : 0
     * commentImages : [{"height":0,"imageDesc":"string","imageId":0,"imageUrl":"string","width":0}]
     * commentState : 0
     * commentTime : 0
     * commentUid : 0
     * ideaImages : [{"height":0,"imageDesc":"string","imageId":0,"imageUrl":"string","width":0}]
     * userInfo : {"authBewrite":"string","authName":"string","follow":0,"incMax":"string","incMin":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
     */

    private String aboutContent;
    private long aboutId;
    private int aboutState;
    private int aboutType;
    private int agreeCount;
    private String comment;
    private long commentId;
    private int commentState;
    private long commentTime;
    private long commentUid;
    private int isAgree;
    private int replyNum;
    private long ideaAuthId;
    private UserInfoBean userInfo;
    private List<ImageInfo> commentImages;
    private List<IdeaImagesBean> ideaImages;

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public long getIdeaAuthId() {
        return ideaAuthId;
    }

    public void setIdeaAuthId(long ideaAuthId) {
        this.ideaAuthId = ideaAuthId;
    }

    public String getAboutContent() {
        return aboutContent;
    }

    public void setAboutContent(String aboutContent) {
        this.aboutContent = aboutContent;
    }

    public long getAboutId() {
        return aboutId;
    }

    public void setAboutId(long aboutId) {
        this.aboutId = aboutId;
    }

    public int getAboutState() {
        return aboutState;
    }

    public void setAboutState(int aboutState) {
        this.aboutState = aboutState;
    }

    public int getAboutType() {
        return aboutType;
    }

    public void setAboutType(int aboutType) {
        this.aboutType = aboutType;
    }

    public int getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(int agreeCount) {
        this.agreeCount = agreeCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public int getCommentState() {
        return commentState;
    }

    public void setCommentState(int commentState) {
        this.commentState = commentState;
    }

    public long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(long commentTime) {
        this.commentTime = commentTime;
    }

    public long getCommentUid() {
        return commentUid;
    }

    public void setCommentUid(long commentUid) {
        this.commentUid = commentUid;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public List<ImageInfo> getCommentImages() {
        return commentImages;
    }

    public void setCommentImages(List<ImageInfo> commentImages) {
        this.commentImages = commentImages;
    }

    public List<IdeaImagesBean> getIdeaImages() {
        return ideaImages;
    }

    public void setIdeaImages(List<IdeaImagesBean> ideaImages) {
        this.ideaImages = ideaImages;
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

    public static class IdeaImagesBean {
        /**
         * height : 0
         * imageDesc : string
         * imageId : 0
         * imageUrl : string
         * width : 0
         */

        private int height;
        private String imageDesc;
        private long imageId;
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

        public long getImageId() {
            return imageId;
        }

        public void setImageId(long imageId) {
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
