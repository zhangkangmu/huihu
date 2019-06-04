package com.huihu.module_home.questionandanswerlist.entity;

import java.util.List;

/**
 * create by ouyangjianfeng on 2019/4/1
 * description:
 */
public class AnswerModel {

    /**
     * pageDatas : [{"agreeCount":0,"briefContent":"AAAAAAAAA","commentCount":0,"editTime":1553931782585,"follow":0,"ideaId":184,"images":[{"imageDesc":"这里是图片描述啦","imageId":405,"imageUrl":"https://gaimg.fx110.com/upload/images/glodcoinmall/2018/12/28/140006385.jpg"}],"opposeCount":0,"userInfo":{"authBewrite":"知名外汇操盘手","authName":"wh","follow":0,"fxCode":"454458","nickName":"LQW893201","uid":284005,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/39_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/39_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/39_120.jpg"}},{"agreeCount":0,"briefContent":"AAAAAAAAA","commentCount":0,"editTime":1553931777585,"follow":0,"ideaId":183,"images":[{"imageDesc":"这里是图片描述啦","imageId":404,"imageUrl":"https://gaimg.fx110.com/upload/images/glodcoinmall/2018/12/28/140006385.jpg"}],"opposeCount":0,"userInfo":{"authBewrite":null,"authName":null,"follow":0,"fxCode":"681547","nickName":"CJJ975578","uid":284004,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/14_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/14_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/14_120.jpg"}},{"agreeCount":0,"briefContent":"AAAAAAAAA","commentCount":0,"editTime":1553931772444,"follow":0,"ideaId":182,"images":[{"imageDesc":"这里是图片描述啦","imageId":403,"imageUrl":"https://gaimg.fx110.com/upload/images/glodcoinmall/2018/12/28/140006385.jpg"}],"opposeCount":0,"userInfo":{"authBewrite":null,"authName":null,"follow":0,"fxCode":"742404","nickName":"DIV848732","uid":284003,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/38_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/38_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/38_120.jpg"}},{"agreeCount":0,"briefContent":"这里是内容啦","commentCount":0,"editTime":1553931571835,"follow":1,"ideaId":181,"images":[{"imageDesc":"这里是图片描述啦","imageId":402,"imageUrl":"https://gaimg.fx110.com/upload/images/glodcoinmall/2018/12/28/140006385.jpg"}],"opposeCount":0,"userInfo":{"authBewrite":"知名外汇操盘手","authName":"wh","follow":1,"fxCode":"814534","nickName":"灌灌灌","uid":284002,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/34_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/34_120.jpg"}}]
     * pageSize : 5
     * timestamp : 1553931571835
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
         * briefContent : AAAAAAAAA
         * commentCount : 0
         * editTime : 1553931782585
         * follow : 0
         * ideaId : 184
         * images : [{"imageDesc":"这里是图片描述啦","imageId":405,"imageUrl":"https://gaimg.fx110.com/upload/images/glodcoinmall/2018/12/28/140006385.jpg"}]
         * opposeCount : 0
         * userInfo : {"authBewrite":"知名外汇操盘手","authName":"wh","follow":0,"fxCode":"454458","nickName":"LQW893201","uid":284005,"userHeadImage":null,"userHeadImg_120":"https://fxchatimage.fx110.com/headdefault/39_120.jpg","userHeadImg_48":"https://fxchatimage.fx110.com/headdefault/39_120.jpg","userHeadImg_80":"https://fxchatimage.fx110.com/headdefault/39_120.jpg"}
         */

        private int agreeCount;
        private String briefContent;
        private int commentCount;
        private long editTime;
        private int follow;
        private int ideaId;
        private int opposeCount;
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
             * authBewrite : 知名外汇操盘手
             * authName : wh
             * follow : 0
             * fxCode : 454458
             * nickName : LQW893201
             * uid : 284005
             * userHeadImage : null
             * userHeadImg_120 : https://fxchatimage.fx110.com/headdefault/39_120.jpg
             * userHeadImg_48 : https://fxchatimage.fx110.com/headdefault/39_120.jpg
             * userHeadImg_80 : https://fxchatimage.fx110.com/headdefault/39_120.jpg
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
             * imageDesc : 这里是图片描述啦
             * imageId : 405
             * imageUrl : https://gaimg.fx110.com/upload/images/glodcoinmall/2018/12/28/140006385.jpg
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
