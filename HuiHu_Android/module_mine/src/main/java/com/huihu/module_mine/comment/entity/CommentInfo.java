package com.huihu.module_mine.comment.entity;

import java.util.List;

public class CommentInfo {

    /**
     * pageDatas : [{"aboutContent":"第一条一级评论哈哈哈哈哈哈哈三跪九叩韩国进口","aboutId":20,"aboutImages":[{"imageDesc":"图片一","imageId":225,"imageUrl":"https://gaimg.fx110.com/upload/images/17/2019/03/28/145541110.jpg"}],"aboutState":0,"aboutType":3,"aboutUid":284023,"aboutUserInfo":{"authBewrite":null,"authName":null,"follow":0,"fxCode":"322014","nickName":"MKR844925","uid":284023,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/30_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/30_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/30_120.jpg"},"agreeCount":0,"comment":"二级级评论哈哈哈哈哈哈哈三跪九叩韩国进口","commentId":34,"commentImages":[{"imageDesc":"图片一","imageId":241,"imageUrl":"https://gaimg.fx110.com/upload/images/17/2019/03/28/145541110.jpg"}],"commentState":0,"commentTime":1553756984273,"commentUid":284002,"findCommentId":20,"ideaId":114,"ideasShowComment":1,"ideasState":1,"userInfo":{"authBewrite":"知名外汇操盘手","authName":"wh","follow":0,"fxCode":"814534","nickName":"灌灌灌灌","uid":284002,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/34_120.jpg"}},{"aboutContent":"第一条一级评论哈哈哈哈哈哈哈三跪九叩韩国进口","aboutId":18,"aboutImages":[{"imageDesc":"图片一","imageId":223,"imageUrl":"https://gaimg.fx110.com/upload/images/17/2019/03/28/145541110.jpg"}],"aboutState":0,"aboutType":3,"aboutUid":284025,"aboutUserInfo":{"authBewrite":null,"authName":null,"follow":0,"fxCode":"556265","nickName":"非官方大哥12","uid":284025,"userHeadImage":null,"userHeadImg_120":"https://gaimg.fx110.com/upload/images/17/2019/03/28/134519244.jpg","userHeadImg_48":"https://gaimg.fx110.com/upload/images/17/2019/03/28/134519244.jpg","userHeadImg_80":"https://gaimg.fx110.com/upload/images/17/2019/03/28/134519244.jpg"},"agreeCount":0,"comment":"二级级评论哈哈哈哈哈哈哈三跪九叩韩国进口","commentId":28,"commentImages":[{"imageDesc":"图片一","imageId":235,"imageUrl":"https://gaimg.fx110.com/upload/images/17/2019/03/28/145541110.jpg"}],"commentState":0,"commentTime":1553756790382,"commentUid":284002,"findCommentId":18,"ideaId":125,"ideasShowComment":1,"ideasState":1,"userInfo":{"authBewrite":"知名外汇操盘手","authName":"wh","follow":0,"fxCode":"814534","nickName":"灌灌灌灌","uid":284002,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/34_120.jpg"}},{"aboutContent":"第一条一级评论哈哈哈哈哈哈哈三跪九叩韩国进口","aboutId":22,"aboutImages":[{"imageDesc":"图片一","imageId":227,"imageUrl":"https://gaimg.fx110.com/upload/images/17/2019/03/28/145541110.jpg"}],"aboutState":0,"aboutType":3,"aboutUid":284025,"aboutUserInfo":{"authBewrite":null,"authName":null,"follow":0,"fxCode":"556265","nickName":"非官方大哥12","uid":284025,"userHeadImage":null,"userHeadImg_120":"https://gaimg.fx110.com/upload/images/17/2019/03/28/134519244.jpg","userHeadImg_48":"https://gaimg.fx110.com/upload/images/17/2019/03/28/134519244.jpg","userHeadImg_80":"https://gaimg.fx110.com/upload/images/17/2019/03/28/134519244.jpg"},"agreeCount":0,"comment":"二级级评论哈哈哈哈哈哈哈三跪九叩韩国进口","commentId":27,"commentImages":[{"imageDesc":"图片一","imageId":234,"imageUrl":"https://gaimg.fx110.com/upload/images/17/2019/03/28/145541110.jpg"}],"commentState":0,"commentTime":1553756770991,"commentUid":284002,"findCommentId":22,"ideaId":125,"ideasShowComment":1,"ideasState":1,"userInfo":{"authBewrite":"知名外汇操盘手","authName":"wh","follow":0,"fxCode":"814534","nickName":"灌灌灌灌","uid":284002,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/34_120.jpg"}}]
     * pageIndex : 1
     * pageSize : 20
     * totalCount : 3
     * totalPage : 1
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
         * aboutContent : 第一条一级评论哈哈哈哈哈哈哈三跪九叩韩国进口
         * aboutId : 20
         * aboutImages : [{"imageDesc":"图片一","imageId":225,"imageUrl":"https://gaimg.fx110.com/upload/images/17/2019/03/28/145541110.jpg"}]
         * aboutState : 0
         * aboutType : 3
         * aboutUid : 284023
         * aboutUserInfo : {"authBewrite":null,"authName":null,"follow":0,"fxCode":"322014","nickName":"MKR844925","uid":284023,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/30_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/30_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/30_120.jpg"}
         * agreeCount : 0
         * comment : 二级级评论哈哈哈哈哈哈哈三跪九叩韩国进口
         * commentId : 34
         * commentImages : [{"imageDesc":"图片一","imageId":241,"imageUrl":"https://gaimg.fx110.com/upload/images/17/2019/03/28/145541110.jpg"}]
         * commentState : 0
         * commentTime : 1553756984273
         * commentUid : 284002
         * findCommentId : 20
         * ideaId : 114
         * ideasShowComment : 1
         * ideasState : 1
         * userInfo : {"authBewrite":"知名外汇操盘手","authName":"wh","follow":0,"fxCode":"814534","nickName":"灌灌灌灌","uid":284002,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/34_120.jpg"}
         */

        private String aboutContent;
        private int aboutId;
        private int aboutState;
        private int aboutType;
        private int aboutUid;
        private AboutUserInfoBean aboutUserInfo;
        private int agreeCount;
        private String comment;
        private int commentId;
        private int commentState;
        private long commentTime;
        private int commentUid;
        private int findCommentId;
        private int ideaId;
        private int ideasShowComment;
        private int ideasState;
        private UserInfoBean userInfo;
        private List<AboutImagesBean> aboutImages;
        private List<CommentImagesBean> commentImages;

        public String getAboutContent() {
            return aboutContent;
        }

        public void setAboutContent(String aboutContent) {
            this.aboutContent = aboutContent;
        }

        public int getAboutId() {
            return aboutId;
        }

        public void setAboutId(int aboutId) {
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

        public int getAboutUid() {
            return aboutUid;
        }

        public void setAboutUid(int aboutUid) {
            this.aboutUid = aboutUid;
        }

        public AboutUserInfoBean getAboutUserInfo() {
            return aboutUserInfo;
        }

        public void setAboutUserInfo(AboutUserInfoBean aboutUserInfo) {
            this.aboutUserInfo = aboutUserInfo;
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

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
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

        public int getCommentUid() {
            return commentUid;
        }

        public void setCommentUid(int commentUid) {
            this.commentUid = commentUid;
        }

        public int getFindCommentId() {
            return findCommentId;
        }

        public void setFindCommentId(int findCommentId) {
            this.findCommentId = findCommentId;
        }

        public int getIdeaId() {
            return ideaId;
        }

        public void setIdeaId(int ideaId) {
            this.ideaId = ideaId;
        }

        public int getIdeasShowComment() {
            return ideasShowComment;
        }

        public void setIdeasShowComment(int ideasShowComment) {
            this.ideasShowComment = ideasShowComment;
        }

        public int getIdeasState() {
            return ideasState;
        }

        public void setIdeasState(int ideasState) {
            this.ideasState = ideasState;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public List<AboutImagesBean> getAboutImages() {
            return aboutImages;
        }

        public void setAboutImages(List<AboutImagesBean> aboutImages) {
            this.aboutImages = aboutImages;
        }

        public List<CommentImagesBean> getCommentImages() {
            return commentImages;
        }

        public void setCommentImages(List<CommentImagesBean> commentImages) {
            this.commentImages = commentImages;
        }

        public static class AboutUserInfoBean {
            /**
             * authBewrite : null
             * authName : null
             * follow : 0
             * fxCode : 322014
             * nickName : MKR844925
             * uid : 284023
             * userHeadImage : null
             * userHeadImg_120 : https://fxchatimage.fx110.com/headdefault/30_120.jpg
             * userHeadImg_48 : https://fxchatimage.fx110.com/headdefault/30_120.jpg
             * userHeadImg_80 : https://fxchatimage.fx110.com/headdefault/30_120.jpg
             */

            private Object authBewrite;
            private Object authName;
            private int follow;
            private String fxCode;
            private String nickName;
            private int uid;
            private Object userHeadImage;
            private String userHeadImg_120;
            private String userHeadImg_48;
            private String userHeadImg_80;

            public Object getAuthBewrite() {
                return authBewrite;
            }

            public void setAuthBewrite(Object authBewrite) {
                this.authBewrite = authBewrite;
            }

            public Object getAuthName() {
                return authName;
            }

            public void setAuthName(Object authName) {
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

            public Object getUserHeadImage() {
                return userHeadImage;
            }

            public void setUserHeadImage(Object userHeadImage) {
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

        public static class UserInfoBean {
            /**
             * authBewrite : 知名外汇操盘手
             * authName : wh
             * follow : 0
             * fxCode : 814534
             * nickName : 灌灌灌灌
             * uid : 284002
             * userHeadImage : null
             * userHeadImg_120 : https://fxchatimage.fx110.com/headdefault/34_120.jpg
             * userHeadImg_48 : https://fxchatimage.fx110.com/headdefault/34_120.jpg
             * userHeadImg_80 : https://fxchatimage.fx110.com/headdefault/34_120.jpg
             */

            private String authBewrite;
            private String authName;
            private int follow;
            private String fxCode;
            private String nickName;
            private int uid;
            private Object userHeadImage;
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

            public Object getUserHeadImage() {
                return userHeadImage;
            }

            public void setUserHeadImage(Object userHeadImage) {
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

        public static class AboutImagesBean {
            /**
             * imageDesc : 图片一
             * imageId : 225
             * imageUrl : https://gaimg.fx110.com/upload/images/17/2019/03/28/145541110.jpg
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

        public static class CommentImagesBean {
            /**
             * imageDesc : 图片一
             * imageId : 241
             * imageUrl : https://gaimg.fx110.com/upload/images/17/2019/03/28/145541110.jpg
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
