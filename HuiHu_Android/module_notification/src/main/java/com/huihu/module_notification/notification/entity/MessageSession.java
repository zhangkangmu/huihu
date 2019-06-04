package com.huihu.module_notification.notification.entity;

import com.huihu.module_notification.notification.entity.session.BaseSession;

/**
 * create by wangjing on 2019/3/20 0020
 * description:
 */
public class MessageSession {


    /**
     * aboutId : 1
     * messageStatus : 0
     * msg : {"ideaId":1,"ideaTitle":"t测试1","msg":null,"nickName":"PV2745759","userId":20165391,"userImage":"https://fxchatimage.fx110.com/headdefault/37.jpg"}
     * noticeId : 1
     * receiveUid : 283980
     * sendTimestamp : 1553080260818
     * sessionId : 097ed443-d2e5-47ed-972c-c9713d937d31
     * title : 回答了问题
     */

    private String aboutId;
    private int messageStatus;
    private String msg;
    private int noticeId;
    private int receiveUid;
    private long sendTimestamp;
    private String sessionId;
    private String title;
    private BaseSession info;

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

    public BaseSession getInfo() {
        if (info == null){
            info = BaseSession.getSessionByType(noticeId, msg);
        }
        return info;
    }

    public void setInfo(BaseSession info) {
        this.info = info;
    }
}
