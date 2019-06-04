package com.huihu.module_home.questiondraft.model;

import java.util.List;

/**
 * create by ouyangjianfeng on 2019/4/17
 * description:
 */
public class GetDraftModel {


    /**
     * pageDatas : [{"content":"你看看那个看看","draftId":156,"editTime":1555489595365,"images":[{"height":0,"imageDesc":"","imageId":801,"imageUrl":"https://gaimg.fx110.com/upload/images/huihu/2019/04/17/162620636.jpg","width":0},{"height":0,"imageDesc":"","imageId":802,"imageUrl":"https://gaimg.fx110.com/upload/images/huihu/2019/04/17/162620668.jpg","width":0},{"height":0,"imageDesc":"","imageId":803,"imageUrl":"https://gaimg.fx110.com/upload/images/huihu/2019/04/17/162620934.jpg","width":0},{"height":0,"imageDesc":"","imageId":804,"imageUrl":"https://gaimg.fx110.com/upload/images/huihu/2019/04/17/162620855.jpg","width":0}],"isClose":0,"title":"这是有图片的草稿"},{"content":"这是草稿","draftId":155,"editTime":1555489552615,"images":[],"isClose":0,"title":"go LOGO"},{"content":"","draftId":154,"editTime":1555489374584,"images":[],"isClose":0,"title":"艰难困苦"}]
     * pageSize : 20
     * timestamp : 1555489374584
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
         * content : 你看看那个看看
         * draftId : 156
         * editTime : 1555489595365
         * images : [{"height":0,"imageDesc":"","imageId":801,"imageUrl":"https://gaimg.fx110.com/upload/images/huihu/2019/04/17/162620636.jpg","width":0},{"height":0,"imageDesc":"","imageId":802,"imageUrl":"https://gaimg.fx110.com/upload/images/huihu/2019/04/17/162620668.jpg","width":0},{"height":0,"imageDesc":"","imageId":803,"imageUrl":"https://gaimg.fx110.com/upload/images/huihu/2019/04/17/162620934.jpg","width":0},{"height":0,"imageDesc":"","imageId":804,"imageUrl":"https://gaimg.fx110.com/upload/images/huihu/2019/04/17/162620855.jpg","width":0}]
         * isClose : 0
         * title : 这是有图片的草稿
         */

        private String content;
        private long draftId;
        private long editTime;
        private int isClose;
        private String title;
        private List<ImagesBean> images;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getDraftId() {
            return draftId;
        }

        public void setDraftId(long draftId) {
            this.draftId = draftId;
        }

        public long getEditTime() {
            return editTime;
        }

        public void setEditTime(long editTime) {
            this.editTime = editTime;
        }

        public int getIsClose() {
            return isClose;
        }

        public void setIsClose(int isClose) {
            this.isClose = isClose;
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
             * imageDesc :
             * imageId : 801
             * imageUrl : https://gaimg.fx110.com/upload/images/huihu/2019/04/17/162620636.jpg
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
