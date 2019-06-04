package com.huihu.module_mine.followsandfollowed.entity;

import java.util.List;

public class FollowsBean {


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
         * authName : string
         * followId : 0
         * followTime : 0
         * isMutual : 0
         * nickName : string
         * userHeadImg : string
         * userId : 0
         */

        private String authName;
        private int followId;
        private long followTime;
        private int isMutual;
        private String nickName;
        private String userHeadImg;
        private long userId;

        public String getAuthName() {
            return authName;
        }

        public void setAuthName(String authName) {
            this.authName = authName;
        }

        public int getFollowId() {
            return followId;
        }

        public void setFollowId(int followId) {
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

        public void setIsMutual(int isMutual) {
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
