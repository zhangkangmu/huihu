package com.huihu.module_mine.loginbypassword.entity;

import java.io.Serializable;

/**
 * create by ouyangjianfeng on 2019/3/21
 * description:
 */
public class UserLoginModel implements Serializable {

    /**
     * account : string
     * clientIp : string
     * clientType : string
     * deviceId : string
     * deviceModel : string
     * loginProduct : string
     * password : string
     */

    private String account;
    private String clientIp;
    private String clientType;
    private String deviceId;
    private String deviceModel;
    private String loginProduct;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getLoginProduct() {
        return loginProduct;
    }

    public void setLoginProduct(String loginProduct) {
        this.loginProduct = loginProduct;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
