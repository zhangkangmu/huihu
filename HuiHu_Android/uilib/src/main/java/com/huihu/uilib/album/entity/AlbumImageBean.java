package com.huihu.uilib.album.entity;

import android.graphics.Bitmap;

/**
 * create by wangjing on 2019/3/12 0012
 * description:
 */
public class AlbumImageBean {

    private int imageId;
    private Bitmap bitmap;
    private String path;
    private String url;

    public AlbumImageBean(){

    }

    public AlbumImageBean(int imageId, String path) {
        this.imageId = imageId;
        this.path = path;
    }

    public AlbumImageBean(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
