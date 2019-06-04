package com.huihu.module_mine.classificationattention.entity;

import java.util.List;

public class ClassificationAttentionInfo {


    /**
     * pageDatas : [{"category":"string","categoryId":0,"followCount":0,"followId":0,"picture":"string"}]
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
         * category : string
         * categoryId : 0
         * followCount : 0
         * followId : 0
         * picture : string
         */

        private String category;
        private int categoryId;
        private int followCount;
        private int followId;
        private String picture;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public int getFollowId() {
            return followId;
        }

        public void setFollowId(int followId) {
            this.followId = followId;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
