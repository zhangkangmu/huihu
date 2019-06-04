package com.huihu.commonlib.net;

/**
 * create by wangjing on 2019/3/19 0019
 * description:
 */
public class ReturnModel {

    /**
     * bodyMessage : String
     * code : 0
     * message : String
     * subCode : 0
     */

    private String bodyMessage;
    private int code;
    private String message;
    private String subCode;

    public String getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(String bodyMessage) {
        this.bodyMessage = bodyMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }
}
