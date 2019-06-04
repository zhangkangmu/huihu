package com.huihu.module_mine.verificationcode.entity;

import java.io.Serializable;

/**
 * create by ouyangjianfeng on 2019/3/21
 * description:
 */
public class UserSmsLoginModel implements Serializable {

    /**
     * clientType : 0
     * countryCode : string
     * deviceId : string
     * deviceModel : string
     * equipmentType : 0
     * loginIP : string
     * phoneNumber : string
     * vcode : string
     */

    private int clientType;
    private String countryCode;
    private String deviceId;
    private String deviceModel;
    private int equipmentType;
    private String loginIP;
    private String phoneNumber;
    private String vcode;

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }
}
