package com.huihu.uilib.entity;

/**
 * create by wangjing on 2019/4/16 0016
 * description:
 */
public class NoticeCustomMessage {

    private String aboutId;
    private int messageStatus;
    private String msg;
    private int noticeId;
    private int receiveUid;
    private long sendTimestamp;
    private String sessionId;
    private String title;

    public String getAboutId() {
        return aboutId;
    }

    public void setAboutId(String aboutId) {
        this.aboutId = aboutId;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public int getReceiveUid() {
        return receiveUid;
    }

    public void setReceiveUid(int receiveUid) {
        this.receiveUid = receiveUid;
    }

    public long getSendTimestamp() {
        return sendTimestamp;
    }

    public void setSendTimestamp(long sendTimestamp) {
        this.sendTimestamp = sendTimestamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
