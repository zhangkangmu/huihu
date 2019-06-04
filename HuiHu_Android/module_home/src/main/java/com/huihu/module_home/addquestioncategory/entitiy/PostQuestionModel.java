package com.huihu.module_home.addquestioncategory.entitiy;

import java.io.Serializable;
import java.util.List;

/**
 * create by ouyangjianfeng on 2019/3/28
 * description:
 */
public class PostQuestionModel implements Serializable {

    /**
     * categorys : [1,2,3]
     * content : <p>手动添加数据让我很难受</p>
     * draftId : 0
     * images : [{"imageDesc":"string","imageUrl":"string"}]
     * title : 这是欧阳在做提问的时候添加的测试数据
     * uid : 283977
     */

    private String content;
    private int draftId;
    private String title;
    private int uid;
    private List<Integer> categorys;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public List<Integer> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Integer> categorys) {
        this.categorys = categorys;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        /**
         * imageDesc : string
         * imageUrl : string
         */

        private String imageDesc = "";
        private String imageUrl = "";

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
    }
}
