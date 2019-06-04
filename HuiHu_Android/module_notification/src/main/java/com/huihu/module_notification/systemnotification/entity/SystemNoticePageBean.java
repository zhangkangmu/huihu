package com.huihu.module_notification.systemnotification.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * create by wangjing on 2019/4/3 0003
 * description:
 */
public class SystemNoticePageBean {


    /**
     * pageDatas : [{"aboutId":"","messageBody":"{\"childType\":1,\"msg\":\"参与新人调查 赢海南三亚5日游，更多好礼尽在汇乎,HI,这位新人，在茫茫人海中，你被抽到成为汇乎的第一批体验用户，请尽快填写我们的资料，你将获得对应的海南三亚五日游的机会，机会不多，不容错过\",\"title\":\"新人调查通知（标题）\",\"url\":null}","messageId":"676e4512-e51a-4985-825e-450cb8879793","messageStatus":0,"noticeId":7,"receiveUid":284002,"sendTimestamp":1554261655196,"sendUid":1}]
     * pageIndex : 1
     * pageSize : 20
     * totalCount : 1
     * totalPage : 1
     */

    private int pageIndex;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<SystemNoticeBean> pageDatas;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<SystemNoticeBean> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<SystemNoticeBean> pageDatas) {
        this.pageDatas = pageDatas;
    }

    public static class SystemNoticeBean {
        /**
         * aboutId :
         * messageBody : {"childType":1,"msg":"参与新人调查 赢海南三亚5日游，更多好礼尽在汇乎,HI,这位新人，在茫茫人海中，你被抽到成为汇乎的第一批体验用户，请尽快填写我们的资料，你将获得对应的海南三亚五日游的机会，机会不多，不容错过","title":"新人调查通知（标题）","url":null}
         * messageId : 676e4512-e51a-4985-825e-450cb8879793
         * messageStatus : 0
         * noticeId : 7
         * receiveUid : 284002
         * sendTimestamp : 1554261655196
         * sendUid : 1
         */

        private String aboutId;
        private String messageBody;
        private String messageId;
        private int messageStatus;
        private int noticeId;
        private long receiveUid;
        private long sendTimestamp;
        private int sendUid;
        private SystemNoticeInfo info;

        public SystemNoticeInfo getInfo() {
            if (info == null) info = new Gson().fromJson(messageBody, SystemNoticeInfo.class);
            return info;
        }

        public void setInfo(SystemNoticeInfo info) {
            this.info = info;
        }

        public String getAboutId() {
            return aboutId;
        }

        public void setAboutId(String aboutId) {
            this.aboutId = aboutId;
        }

        public String getMessageBody() {
            return messageBody;
        }

        public void setMessageBody(String messageBody) {
            this.messageBody = messageBody;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public int getMessageStatus() {
            return messageStatus;
        }

        public void setMessageStatus(int messageStatus) {
            this.messageStatus = messageStatus;
        }

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public long getReceiveUid() {
            return receiveUid;
        }

        public void setReceiveUid(long receiveUid) {
            this.receiveUid = receiveUid;
        }

        public long getSendTimestamp() {
            return sendTimestamp;
        }

        public void setSendTimestamp(long sendTimestamp) {
            this.sendTimestamp = sendTimestamp;
        }

        public int getSendUid() {
            return sendUid;
        }

        public void setSendUid(int sendUid) {
            this.sendUid = sendUid;
        }
    }

    public static class SystemNoticeInfo{

        /**
         * childType : 1
         * msg : 参与新人调查 赢海南三亚5日游，更多好礼尽在汇乎,HI,这位新人，在茫茫人海中，你被抽到成为汇乎的第一批体验用户，请尽快填写我们的资料，你将获得对应的海南三亚五日游的机会，机会不多，不容错过
         * title : 新人调查通知（标题）
         * url : null
         */

        private int childType;
        private String msg;
        private String title;
        private String url;

        public int getChildType() {
            return childType;
        }

        public void setChildType(int childType) {
            this.childType = childType;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
