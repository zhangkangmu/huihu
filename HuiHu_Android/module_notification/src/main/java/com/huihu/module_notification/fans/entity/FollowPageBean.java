package com.huihu.module_notification.fans.entity;

import com.huihu.uilib.def.NetDataBoolean;

import java.util.List;

/**
 * create by wangjing on 2019/3/29 0029
 * description:
 */
public class FollowPageBean {

    /**
     * pageDatas : [{"authName":"string","followId":0,"followTime":0,"isMutual":0,"nickName":"string","userHeadImg":"string","userId":0}]
     * pageIndex : 0
     * pageSize : 0
     * totalCount : 0
     * totalPage : 0
     */

    private int pageIndex;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<DataBean> pageDatas;

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

    public List<DataBean> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<DataBean> pageDatas) {
        this.pageDatas = pageDatas;
    }

    public static class DataBean {
        /**
         * authName : string
         * followId : 0
         * followTime : 0
         * isMutual : 0
         * incMax : https://gaimg.fx110.com/upload/images/17/2019/03/27/165641680.jpg
         * incMin : https://gaimg.fx110.com/upload/images/huihu/2019/04/09/141040874.jpg
         * nickName : string
         * userHeadImg : string
         * userId : 0
         */

        private String authName;
        private long followId;
        private long followTime;
        private int isMutual;
        private String incMax;
        private String incMin;
        private String nickName;
        private String userHeadImg;
        private long userId;
        private boolean follow;

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

        public boolean isFollow() {
            return follow;
        }

        public void setFollow(boolean follow) {
            this.follow = follow;
        }

        public String getAuthName() {
            return authName;
        }

        public void setAuthName(String authName) {
            this.authName = authName;
        }

        public long getFollowId() {
            return followId;
        }

        public void setFollowId(long followId) {
            this.followId = followId;
        }

        public long getFollowTime() {
            return followTime;
        }

        public void setFollowTime(long followTime) {
            this.followTime = followTime;
        }

        public int getIsMutual() {
            return isMutual;
        }

        public void setIsMutual(@NetDataBoolean int isMutual) {
            this.isMutual = isMutual;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserHeadImg() {
            return userHeadImg;
        }

        public void setUserHeadImg(String userHeadImg) {
            this.userHeadImg = userHeadImg;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

    }
}
