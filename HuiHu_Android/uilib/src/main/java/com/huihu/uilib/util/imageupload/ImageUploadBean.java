package com.huihu.uilib.util.imageupload;

import com.huihu.commonlib.utils.Constant;

/**
 * create by wangjing on 2019/3/27 0027
 * description:
 */
class ImageUploadBean {

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
    private int fileType = Constant.IMAGE_FILE_TYPE;
    private int siteType = Constant.HUIHU_SITE_TYPE;
    private String url;
    private boolean verifyImage;
    private int watermarkAlpha;
    private int watermarkCoverRatio;
    private int watermarkPosition;
    private int watermarkRotateAngle;
    private int watermarkSize;
    private int watermarkSizePercentage;
    private int watermarkType = 0;

    public ImageUploadBean() {
    }

    public ImageUploadBean(String base64) {
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

    public int getWatermarkAlpha() {
        return watermarkAlpha;
    }

    public void setWatermarkAlpha(int watermarkAlpha) {
        this.watermarkAlpha = watermarkAlpha;
    }

    public int getWatermarkCoverRatio() {
        return watermarkCoverRatio;
    }

    public void setWatermarkCoverRatio(int watermarkCoverRatio) {
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
