package com.huihu.module_notification.notification.entity;

import java.util.List;

/**
 * create by wangjing on 2019/4/3 0003
 * description:
 */
public class NoticeSessionBean {

    private List<MessageSession> messageSessionHeader;
    private List<MessageSession> messageSessionList;

    public List<MessageSession> getMessageSessionHeader() {
        return messageSessionHeader;
    }

    public void setMessageSessionHeader(List<MessageSession> messageSessionHeader) {
        this.messageSessionHeader = messageSessionHeader;
    }

    public List<MessageSession> getMessageSessionList() {
        return messageSessionList;
    }

    public void setMessageSessionList(List<MessageSession> messageSessionList) {
        this.messageSessionList = messageSessionList;
    }

}
