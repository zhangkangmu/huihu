package com.huihu.module_mine.feedback.entity;

/**
 * create by wangjing on 2019/3/27 0027
 * description:
 */
public class PostFeedbackBean {

    /**
     * clientModel : string
     * clientType : 0
     * contact : string
     * content : string
     * equipmentType : 0
     * imgUrls : string
     * userId : 0
     * version : string
     */

    private String clientModel;
    private int clientType;
    private String contact;
    private String content;
    private int equipmentType;
    private String imgUrls;
    private long userId;
    private String version;

    public String getClientModel() {
        return clientModel;
    }

    public void setClientModel(String clientModel) {
        this.clientModel = clientModel;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
