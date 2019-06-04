package com.huihu.module_mine.others.entity;

import java.util.List;

public class UserInfo {


    /**
     * agreeNum : 0
     * fansNum : 0
     * followNum : 0
     * fxCode : string
     * isFollows : 0
     * nickName : string
     * signature : string
     * uid : 0
     * userAuthShowModelList : [{"authBewrite":"string","authCondition":"string","authName":"string","authOrder":0,"authState":0,"incMax":"string","incMin":"string"}]
     * userCenterImg : string
     * userHeadImage : string
     * userHeadImg_120 : string
     * userHeadImg_48 : string
     * userHeadImg_80 : string
     */

    private long agreeNum;
    private long fansNum;
    private long followNum;
    private String fxCode;
    private int isFollows;
    private String nickName;
    private String signature;
    private long uid;
    private String userCenterImg;
    private String userHeadImage;
    private String userHeadImg_120;
    private String userHeadImg_48;
    private String userHeadImg_80;
    private List<UserAuthShowModelListBean> userAuthShowModelList;

    public long getAgreeNum() {
        return agreeNum;
    }

    public void setAgreeNum(long agreeNum) {
        this.agreeNum = agreeNum;
    }

    public long getFansNum() {
        return fansNum;
    }

    public void setFansNum(long fansNum) {
        this.fansNum = fansNum;
    }

    public long getFollowNum() {
        return followNum;
    }

    public void setFollowNum(long followNum) {
        this.followNum = followNum;
    }

    public String getFxCode() {
        return fxCode;
    }

    public void setFxCode(String fxCode) {
        this.fxCode = fxCode;
    }

    public int getIsFollows() {
        return isFollows;
    }

    public void setIsFollows(int isFollows) {
        this.isFollows = isFollows;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserCenterImg() {
        return userCenterImg;
    }

    public void setUserCenterImg(String userCenterImg) {
        this.userCenterImg = userCenterImg;
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

    public List<UserAuthShowModelListBean> getUserAuthShowModelList() {
        return userAuthShowModelList;
    }

    public void setUserAuthShowModelList(List<UserAuthShowModelListBean> userAuthShowModelList) {
        this.userAuthShowModelList = userAuthShowModelList;
    }

    public static class UserAuthShowModelListBean {
        /**
         * authBewrite : string
         * authCondition : string
         * authName : string
         * authOrder : 0
         * authState : 0
         * incMax : string
         * incMin : string
         */

        private String authBewrite;
        private String authCondition;
        private String authName;
        private int authOrder;
        private int authState;
        private String incMax;
        private String incMin;

        public String getAuthBewrite() {
            return authBewrite;
        }

        public void setAuthBewrite(String authBewrite) {
            this.authBewrite = authBewrite;
        }

        public String getAuthCondition() {
            return authCondition;
        }

        public void setAuthCondition(String authCondition) {
            this.authCondition = authCondition;
        }

        public String getAuthName() {
            return authName;
        }

        public void setAuthName(String authName) {
            this.authName = authName;
        }

        public int getAuthOrder() {
            return authOrder;
        }

        public void setAuthOrder(int authOrder) {
            this.authOrder = authOrder;
        }

        public int getAuthState() {
            return authState;
        }

        public void setAuthState(int authState) {
            this.authState = authState;
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
    }
}
