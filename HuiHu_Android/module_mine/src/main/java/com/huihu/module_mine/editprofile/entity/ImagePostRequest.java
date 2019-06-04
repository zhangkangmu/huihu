package com.huihu.module_mine.editprofile.entity;

/**
 * Created by $USER_NAME on 2019/3/26.
 * description：
 */
public class ImagePostRequest {
    /**
     * base64 : string
     * cutRectangle : string
     * directory : string
     * extension : string
     * fileType : 0
     * siteType : 0
     * url : string
     * verifyImage : true
     * watermarkAlpha : 0
     * watermarkCoverRatio : 0
     * watermarkPosition : 0
     * watermarkRotateAngle : 0
     * watermarkSize : 0
     * watermarkSizePercentage : 0
     * watermarkType : 0
     */


    private String base64;
    private String cutRectangle;
    private String directory;
    private String extension;
    //文件类型(图片 = 1,音频 = 2,短视频 = 3,文件=4)
    private int fileType;
    //站点类型(汇聊 = 1,主站 = 2,BBS论坛 = 3,外汇媒体 = 5,BrokersShow = 6,拜仑=7,交易商评测站 = 8,监管查询站 = 9,外汇慈善 = 10,外汇书=11,外汇百科=12,牛人榜=13)
    private int siteType;
    private String url;
    private boolean verifyImage;
    private double watermarkAlpha;
    private double watermarkCoverRatio;
    //水印图片位置 = ['1’, '2’, '3’, '4’, '5’, '6’, '7’, ‘8’]
    private int watermarkPosition;
    private int watermarkRotateAngle;
    private int watermarkSize;
    private int watermarkSizePercentage;
    //水印类型(无 = 0,默认 = 1,主站=2,BBS论坛 = 3,汇信=4)
    private int watermarkType;

    public ImagePostRequest(String base64){
        this.base64 = base64;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getCutRectangle() {
        return cutRectangle;
    }

    public void setCutRectangle(String cutRectangle) {
        this.cutRectangle = cutRectangle;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getSiteType() {
        return siteType;
    }

    public void setSiteType(int siteType) {
        this.siteType = siteType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVerifyImage() {
        return verifyImage;
    }

    public void setVerifyImage(boolean verifyImage) {
        this.verifyImage = verifyImage;
    }

    public double getWatermarkAlpha() {
        return watermarkAlpha;
    }

    public void setWatermarkAlpha(double watermarkAlpha) {
        this.watermarkAlpha = watermarkAlpha;
    }

    public double getWatermarkCoverRatio() {
        return watermarkCoverRatio;
    }

    public void setWatermarkCoverRatio(double watermarkCoverRatio) {
        this.watermarkCoverRatio = watermarkCoverRatio;
    }

    public int getWatermarkPosition() {
        return watermarkPosition;
    }

    public void setWatermarkPosition(int watermarkPosition) {
        this.watermarkPosition = watermarkPosition;
    }

    public int getWatermarkRotateAngle() {
        return watermarkRotateAngle;
    }

    public void setWatermarkRotateAngle(int watermarkRotateAngle) {
        this.watermarkRotateAngle = watermarkRotateAngle;
    }

    public int getWatermarkSize() {
        return watermarkSize;
    }

    public void setWatermarkSize(int watermarkSize) {
        this.watermarkSize = watermarkSize;
    }

    public int getWatermarkSizePercentage() {
        return watermarkSizePercentage;
    }

    public void setWatermarkSizePercentage(int watermarkSizePercentage) {
        this.watermarkSizePercentage = watermarkSizePercentage;
    }

    public int getWatermarkType() {
        return watermarkType;
    }

    public void setWatermarkType(int watermarkType) {
        this.watermarkType = watermarkType;
    }
}
