package com.huihu.module_home.hotrank.entity;

import java.util.List;

public class HotRankBean {

    /**
     * pageDatas : [{"answerCount":null,"browse":null,"commentCount":0,"holdTime":1553239807788,"ideaId":94,"ideaType":2,"imageUrl":null,"title":"测试标题?"},{"answerCount":0,"browse":null,"commentCount":null,"holdTime":1553239714788,"ideaId":93,"ideaType":1,"imageUrl":null,"title":"测试标题?"},{"answerCount":0,"browse":null,"commentCount":null,"holdTime":1553235435416,"ideaId":72,"ideaType":1,"imageUrl":null,"title":"string?"},{"answerCount":null,"browse":null,"commentCount":0,"holdTime":1552101387000,"ideaId":58,"ideaType":3,"imageUrl":"https://gaimg.fx110.com/upload/images/glodcoinmall/2019/02/22/111157934.jpg","title":"我是第三时间加入热榜"},{"answerCount":null,"browse":null,"commentCount":0,"holdTime":1552101036000,"ideaId":55,"ideaType":2,"imageUrl":"https://gaimg.fx110.com/upload/images/glodcoinmall/2019/02/16/111034738.jpg","title":"我是第二时间加入热榜"},{"answerCount":0,"browse":null,"commentCount":null,"holdTime":1552100522000,"ideaId":54,"ideaType":1,"imageUrl":"https://gaimg.fx110.com/upload/images/glodcoinmall/2019/02/16/111034738.jpg","title":"我是第一时间加入的热榜"}]
     * pageSize : 20
     * timestamp : 1552100522000
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
         * answerCount : null
         * browse : null
         * commentCount : 0
         * holdTime : 1553239807788
         * ideaId : 94
         * ideaType : 2
         * imageUrl : null
         * title : 测试标题?
         */

        private int answerCount;
        private int  browse;
        private int commentCount;
        private long holdTime;
        private int ideaId;
        private int ideaType;
        private String imageUrl;
        private String title;

        public int getAnswerCount() {
            return answerCount;
        }

        public void setAnswerCount(int answerCount) {
            this.answerCount = answerCount;
        }

        public int getBrowse() {
            return browse;
        }

        public void setBrowse(int browse) {
            this.browse = browse;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public long getHoldTime() {
            return holdTime;
        }

        public void setHoldTime(long holdTime) {
            this.holdTime = holdTime;
        }

        public int getIdeaId() {
            return ideaId;
        }

        public void setIdeaId(int ideaId) {
            this.ideaId = ideaId;
        }

        public int getIdeaType() {
            return ideaType;
        }

        public void setIdeaType(int ideaType) {
            this.ideaType = ideaType;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
