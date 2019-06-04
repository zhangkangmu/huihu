package com.huihu.module_mine.attentionquestion.entity;

import java.util.List;

public class AttentionQuestionInfo {

    /**
     * pageDatas : [{"briefContent":"string","createTime":0,"followId":0,"ideaId":0,"images":[{"height":0,"imageDesc":"string","imageId":0,"imageUrl":"string","width":0}],"title":"string"}]
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
         * briefContent : string
         * createTime : 0
         * followId : 0
         * ideaId : 0
         * images : [{"height":0,"imageDesc":"string","imageId":0,"imageUrl":"string","width":0}]
         * title : string
         */

        private String briefContent;
        private long createTime;
        private int followId;
        private long ideaId;
        private String title;
        private List<ImagesBean> images;

        public String getBriefContent() {
            return briefContent;
        }

        public void setBriefContent(String briefContent) {
            this.briefContent = briefContent;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getFollowId() {
            return followId;
        }

        public void setFollowId(int followId) {
            this.followId = followId;
        }

        public long getIdeaId() {
            return ideaId;
        }

        public void setIdeaId(long ideaId) {
            this.ideaId = ideaId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean {
            /**
             * height : 0
             * imageDesc : string
             * imageId : 0
             * imageUrl : string
             * width : 0
             */

            private int height;
            private String imageDesc;
            private int imageId;
            private String imageUrl;
            private int width;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

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

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }
    }
}
