package com.huihu.module_notification.systemmessage.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * create by wangjing on 2019/4/4 0004
 * description:
 */
public class SystemMessagePageBean {

    /**
     * pageDatas : [{"aboutId":"","messageBody":"{\"childType\":1,\"image\":\"https://gaimg.fx110.com/upload/images/glodcoinmall/2018/12/28/140006385.jpg\",\"msg\":\"【打开提问】其中的小的波段，不必非常在意第二天的行情会怎样走，要学会有利则取，落袋而安，无利则逃，避开危险，防止突发事件\",\"title\":\"外汇课堂 | 炒外汇如何进行短线操作？\",\"url\":\"https://www.baidu.com/\"}","messageId":"5554fb54-de82-4bdf-a171-3369b75f4e7b","messageStatus":0,"noticeId":6,"receiveUid":284002,"sendTimestamp":1554169357895,"sendUid":1}]
     * pageIndex : 1
     * pageSize : 20
     * totalCount : 1
     * totalPage : 1
     */

    private int pageIndex;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<SystemMessageBean> pageDatas;

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

    public List<SystemMessageBean> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<SystemMessageBean> pageDatas) {
        this.pageDatas = pageDatas;
    }

    public static class SystemMessageBean {
        /**
         * aboutId :
         * messageBody : {"childType":1,"image":"https://gaimg.fx110.com/upload/images/glodcoinmall/2018/12/28/140006385.jpg","msg":"【打开提问】其中的小的波段，不必非常在意第二天的行情会怎样走，要学会有利则取，落袋而安，无利则逃，避开危险，防止突发事件","title":"外汇课堂 | 炒外汇如何进行短线操作？","url":"https://www.baidu.com/"}
         * messageId : 5554fb54-de82-4bdf-a171-3369b75f4e7b
         * messageStatus : 0
         * noticeId : 6
         * receiveUid : 284002
         * sendTimestamp : 1554169357895
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
        private SystemMessageInfo info;

        public SystemMessageInfo getInfo() {
            if (info == null) info = new Gson().fromJson(messageBody, SystemMessageInfo.class);
            return info;
        }

        public void setInfo(SystemMessageInfo info) {
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

    public static class SystemMessageInfo{

        /**
         * childType : 1
         * image : https://gaimg.fx110.com/upload/images/glodcoinmall/2018/12/28/140006385.jpg
         * msg : 【打开提问】其中的小的波段，不必非常在意第二天的行情会怎样走，要学会有利则取，落袋而安，无利则逃，避开危险，防止突发事件
         * title : 外汇课堂 | 炒外汇如何进行短线操作？
         * url : https://www.baidu.com/
         */

        private int childType;
        private String image;
        private String msg;
        private String title;
        private String url;

        public int getChildType() {
            return childType;
        }

        public void setChildType(int childType) {
            this.childType = childType;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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
