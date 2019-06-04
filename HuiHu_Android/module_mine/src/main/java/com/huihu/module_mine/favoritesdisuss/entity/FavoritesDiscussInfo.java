package com.huihu.module_mine.favoritesdisuss.entity;

import java.util.List;

public class FavoritesDiscussInfo {

    /**
     * pageDatas : [{"briefContent":"string","circleId":0,"circleName":"string","editTime":0,"ideaId":0,"images":[{"height":0,"imageDesc":"string","imageId":0,"imageUrl":"string","width":0}],"title":"string"}]
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
         * briefContent : string
         * circleId : 0
         * circleName : string
         * editTime : 0
         * ideaId : 0
         * images : [{"height":0,"imageDesc":"string","imageId":0,"imageUrl":"string","width":0}]
         * title : string
         */

        private String briefContent;
        private int circleId;
        private String circleName;
        private long editTime;
        private long ideaId;
        private String title;
        private List<ImagesBean> images;

        public String getBriefContent() {
            return briefContent;
        }

        public void setBriefContent(String briefContent) {
            this.briefContent = briefContent;
        }

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

        public long getEditTime() {
            return editTime;
        }

        public void setEditTime(long editTime) {
            this.editTime = editTime;
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
