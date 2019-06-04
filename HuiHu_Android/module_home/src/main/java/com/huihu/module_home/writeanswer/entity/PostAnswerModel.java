package com.huihu.module_home.writeanswer.entity;

import java.io.Serializable;
import java.util.List;

/**
 * create by ouyangjianfeng on 2019/4/2
 * description:
 */
public class PostAnswerModel implements Serializable {

    /**
     * content : string
     * draftId : 0
     * images : [{"height":0,"imageDesc":"string","imageUrl":"string","width":0}]
     * questionId : 0
     * uid : 0
     */

    private String content;
    private int draftId;
    private long questionId;
    private int uid;
    private List<ImagesBean> images;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDraftId() {
        return draftId;
    }

    public void setDraftId(int draftId) {
        this.draftId = draftId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
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
        private String imageDesc = "";
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
