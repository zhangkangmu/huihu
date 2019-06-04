package com.huihu.module_home.question.entity;

import java.util.List;

/**
 * create by ouyangjianfeng on 2019/4/17
 * description:
 */
public class PostDraftModel {

    /**
     * content : string
     * images : [{"height":0,"imageDesc":"string","imageUrl":"string","width":0}]
     * questionId : 0
     * title : string
     * type : 1
     * uid : 0
     */

    private String content;
    private long questionId;
    private String title;
    private int type;
    private int uid;
    private List<ImagesBean> images;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
         * imageUrl : string
         * width : 0
         */

        private int height;
        private String imageDesc;
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
