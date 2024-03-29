package com.huihu.module_mine.attentioncircle.entity;

import java.util.List;

public class CircleAttentionInfo {

    /**
     * pageDatas : [{"circleId":0,"circleName":"string","circleType":0,"circleUid":0,"followId":0,"imageUrl":"string","introduce":"string","memberNum":0,"nickName":"string"}]
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
         * circleId : 0
         * circleName : string
         * circleType : 0
         * circleUid : 0
         * followId : 0
         * imageUrl : string
         * introduce : string
         * memberNum : 0
         * nickName : string
         */

        private int circleId;
        private String circleName;
        private int circleType;
        private int circleUid;
        private int followId;
        private String imageUrl;
        private String introduce;
        private int memberNum;
        private String nickName;

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

        public int getCircleType() {
            return circleType;
        }

        public void setCircleType(int circleType) {
            this.circleType = circleType;
        }

        public int getCircleUid() {
            return circleUid;
        }

        public void setCircleUid(int circleUid) {
            this.circleUid = circleUid;
        }

        public int getFollowId() {
            return followId;
        }

        public void setFollowId(int followId) {
            this.followId = followId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public int getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(int memberNum) {
            this.memberNum = memberNum;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }
}
