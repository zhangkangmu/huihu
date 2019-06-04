package com.huihu.module_circle.membermanagement.entity;

import java.util.List;

public class MemberManagementInfo {

    /**
     * pageDatas : [{"forbid":0,"joinTime":0,"memberType":0,"userInfo":{"authBewrite":"string","authName":"string","follow":0,"incMax":"string","incMin":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}}]
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
         * forbid : 0
         * joinTime : 0
         * memberType : 0
         * userInfo : {"authBewrite":"string","authName":"string","follow":0,"incMax":"string","incMin":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
         */

        private int forbid;
        private long joinTime;
        private int memberType;
        private UserInfoBean userInfo;

        public int getForbid() {
            return forbid;
        }

        public void setForbid(int forbid) {
            this.forbid = forbid;
        }

        public long getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(long joinTime) {
            this.joinTime = joinTime;
        }

        public int getMemberType() {
            return memberType;
        }

        public void setMemberType(int memberType) {
            this.memberType = memberType;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
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
    }
}
