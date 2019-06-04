package com.huihu.module_circle.mycircle.entity;

import java.util.List;

public class MyCircleInfo {

    /**
     * pageDatas : [{"circleId":0,"circleName":"string","circleType":0,"height":0,"ideaNum":0,"imageUrl":"string","joinTime":0,"memberNum":0,"memberType":0,"name":"string","width":0}]
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
         * circleId : 0
         * circleName : string
         * circleType : 0
         * height : 0
         * ideaNum : 0
         * imageUrl : string
         * joinTime : 0
         * memberNum : 0
         * memberType : 0
         * name : string
         * width : 0
         */

        private long circleId;
        private String circleName;
        private int circleType;
        private int height;
        private int ideaNum;
        private String imageUrl;
        private long joinTime;
        private int memberNum;
        private int memberType;
        private String name;
        private int width;

        public long getCircleId() {
            return circleId;
        }

        public void setCircleId(long circleId) {
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

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getIdeaNum() {
            return ideaNum;
        }

        public void setIdeaNum(int ideaNum) {
            this.ideaNum = ideaNum;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public long getJoinTime() {
            return joinTime;
        }

        public void setJoinTime(long joinTime) {
            this.joinTime = joinTime;
        }

        public int getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(int memberNum) {
            this.memberNum = memberNum;
        }

        public int getMemberType() {
            return memberType;
        }

        public void setMemberType(int memberType) {
            this.memberType = memberType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
