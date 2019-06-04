package com.huihu.module_notification.commentdetails.entity;

/**
 * Created by jiangwensong on 2019/3/30.
 * description：
 */
public class ImageInfo {

    int height;
    int width;
    private String imageDesc;
    private int imageId;
    private String imageUrl;

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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
