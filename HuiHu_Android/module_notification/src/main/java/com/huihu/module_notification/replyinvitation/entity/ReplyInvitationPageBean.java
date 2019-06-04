package com.huihu.module_notification.replyinvitation.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * create by wangjing on 2019/3/21 0021
 * description:
 */
public class ReplyInvitationPageBean {


    /**
     * pageDatas : [{"aboutId":"","messageBody":"{\"childType\":1,\"ideaId\":105,\"ideaTitle\":\"问题是XXX\",\"msg\":null,\"nickName\":\"灌灌灌\",\"userId\":284002,\"userImage\":\"https://fxchatimage.fx110.com/headdefault/34.jpg\"}","messageId":"e306907e-dc30-4b3e-9e55-54b5cf9fc5f1","messageStatus":0,"noticeId":4,"receiveUid":284002,"sendTimestamp":1554190565781,"sendUid":1},{"aboutId":"","messageBody":"{\"childType\":1,\"ideaId\":105,\"ideaTitle\":\"问题是XXX\",\"msg\":null,\"nickName\":\"灌灌灌\",\"userId\":284002,\"userImage\":\"https://fxchatimage.fx110.com/headdefault/34.jpg\"}","messageId":"59f3ff4d-ba5e-41fc-a91a-9ab1920a4ae2","messageStatus":0,"noticeId":4,"receiveUid":284002,"sendTimestamp":1554190329203,"sendUid":1},{"aboutId":"","messageBody":"{\"childType\":1,\"ideaId\":105,\"ideaTitle\":\"问题是XXX\",\"msg\":null,\"nickName\":\"灌灌灌\",\"userId\":284002,\"userImage\":\"https://fxchatimage.fx110.com/headdefault/34.jpg\"}","messageId":"084c38bb-0eb2-4dbf-970c-76e8e1bca2dc","messageStatus":0,"noticeId":4,"receiveUid":284002,"sendTimestamp":1554189969203,"sendUid":1}]
     * pageIndex : 1
     * pageSize : 20
     * totalCount : 3
     * totalPage : 1
     */

    private int pageIndex;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<ReplyInvitationBean> pageDatas;

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

    public List<ReplyInvitationBean> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<ReplyInvitationBean> pageDatas) {
        this.pageDatas = pageDatas;
    }

    public static class ReplyInvitationBean {
        /**
         * aboutId :
         * messageBody : {"childType":1,"ideaId":105,"ideaTitle":"问题是XXX","msg":null,"nickName":"灌灌灌","userId":284002,"userImage":"https://fxchatimage.fx110.com/headdefault/34.jpg"}
         * messageId : e306907e-dc30-4b3e-9e55-54b5cf9fc5f1
         * messageStatus : 0
         * noticeId : 4
         * receiveUid : 284002
         * sendTimestamp : 1554190565781
         * sendUid : 1
         */

        private String aboutId;
        private String messageBody;
        private String messageId;
        private int messageStatus;
        private int noticeId;
        private int receiveUid;
        private long sendTimestamp;
        private int sendUid;
        private ReplyInvitationInfo info;

        public ReplyInvitationInfo getInfo() {
            if (info == null) info = new Gson().fromJson(messageBody, ReplyInvitationInfo.class);
            return info;
        }

        public void setInfo(ReplyInvitationInfo info) {
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

        public int getSendUid() {
            return sendUid;
        }

        public void setSendUid(int sendUid) {
            this.sendUid = sendUid;
        }
    }

    public static class ReplyInvitationInfo{

        /**
         * childType : 1
         * ideaId : 105
         * ideaTitle : 问题是XXX
         * msg : null
         * nickName : 灌灌灌
         * userId : 284002
         * userImage : https://fxchatimage.fx110.com/headdefault/34.jpg
         */

        private int childType;
        private int ideaId;
        private String ideaTitle;
        private String msg;
        private String nickName;
        private int userId;
        private String userImage;
        private String incMin;

        public String getIncMin() {
            return incMin;
        }

        public void setIncMin(String incMin) {
            this.incMin = incMin;
        }

        public int getChildType() {
            return childType;
        }

        public void setChildType(int childType) {
            this.childType = childType;
        }

        public int getIdeaId() {
            return ideaId;
        }

        public void setIdeaId(int ideaId) {
            this.ideaId = ideaId;
        }

        public String getIdeaTitle() {
            return ideaTitle;
        }

        public void setIdeaTitle(String ideaTitle) {
            this.ideaTitle = ideaTitle;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }
    }
}
