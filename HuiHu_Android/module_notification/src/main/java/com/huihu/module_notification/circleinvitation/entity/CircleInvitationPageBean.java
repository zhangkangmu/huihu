package com.huihu.module_notification.circleinvitation.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * create by wangjing on 2019/4/8 0008
 * description:
 */
public class CircleInvitationPageBean {


    /**
     * pageDatas : [{"aboutId":"4","messageBody":"{\"childType\":1,\"circleId\":105,\"circleName\":\"外汇交易圈\",\"msg\":null,\"userId\":284003,\"userImage\":\"https://fxchatimage.fx110.com/headdefault/38.jpg\",\"userName\":\"DIV848732\"}","messageId":"e130d5b5-9fdf-4774-84bf-bd07a13e399b","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554257796964,"sendUid":1},{"aboutId":"","messageBody":"null","messageId":"1636d8a8-f1cf-42f0-a437-44e8f192cc75","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554189766850,"sendUid":1},{"aboutId":"","messageBody":"null","messageId":"a72ab462-2174-4195-9e9c-8b75c1eeb6a2","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554189712949,"sendUid":1},{"aboutId":"","messageBody":"{\"msg\":null}","messageId":"cf8919c6-3740-47da-9a87-43d1c3d55068","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554189628518,"sendUid":1},{"aboutId":"4","messageBody":"{\"msg\":null}","messageId":"649657eb-8d8a-45a9-8078-b661343f11c3","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554177511940,"sendUid":1},{"aboutId":"4","messageBody":"{\"msg\":null}","messageId":"e75ba9f5-8647-4139-a459-35e457fce9b2","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554177444694,"sendUid":1},{"aboutId":"4","messageBody":"{\"msg\":null}","messageId":"83f7bd60-d160-4be9-84fa-cf0653345958","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554177030420,"sendUid":1},{"aboutId":"4","messageBody":"{\"msg\":null}","messageId":"455d0013-ebf2-4cab-bf46-53bf3ece0352","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554176874381,"sendUid":1},{"aboutId":"4","messageBody":"{\"msg\":null}","messageId":"849b205a-ee14-462e-bebf-d105d6a74b6a","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554176674816,"sendUid":1},{"aboutId":"4","messageBody":"{\"msg\":null}","messageId":"0eae090c-7a60-43ab-83bf-ceeebc40b993","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554176022480,"sendUid":1},{"aboutId":"4","messageBody":"{\"childType\":1,\"ideaId\":105,\"ideaTitle\":\"邀请回答\",\"msg\":null,\"nickName\":\"XZ2570472\",\"userId\":283980,\"userImage\":\"https://fxchatimage.fx110.com/headdefault/44.jpg\"}","messageId":"cf4184dd-51dc-478f-94ad-a2adb43fdb64","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554175773913,"sendUid":1},{"aboutId":"4","messageBody":"{\"childType\":4,\"circleId\":4,\"circleName\":\"拒绝\",\"msg\":null,\"userId\":284002,\"userImage\":\"https://fxchatimage.fx110.com/headdefault/34.jpg\",\"userName\":\"灌灌灌\"}","messageId":"c8127136-2dc8-4221-85da-0f27489268f1","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554175228955,"sendUid":1},{"aboutId":"4","messageBody":"{\"childType\":3,\"circleId\":4,\"circleName\":\"拒绝\",\"msg\":null,\"userId\":284002,\"userImage\":\"https://fxchatimage.fx110.com/headdefault/34.jpg\",\"userName\":\"灌灌灌\"}","messageId":"44672fc1-e8d4-47d6-9edc-1cd9a5fe9e30","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554175191345,"sendUid":1},{"aboutId":"4","messageBody":"{\"childType\":1,\"circleId\":4,\"circleName\":\"欢迎加入\",\"msg\":null,\"userId\":284002,\"userImage\":\"https://fxchatimage.fx110.com/headdefault/34.jpg\",\"userName\":\"灌灌灌\"}","messageId":"d7de9702-ad56-48ec-8f7d-0b2cbc576cd7","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554175049080,"sendUid":1},{"aboutId":"4","messageBody":"{\"childType\":2,\"circleId\":4,\"circleName\":\"第一个圈子\",\"msg\":null,\"userId\":284002,\"userImage\":\"https://fxchatimage.fx110.com/headdefault/34.jpg\",\"userName\":\"灌灌灌\"}","messageId":"2764a67e-206c-4b00-a748-8803f03632bb","messageStatus":0,"noticeId":5,"receiveUid":284002,"sendTimestamp":1554174226510,"sendUid":1}]
     * pageIndex : 1
     * pageSize : 20
     * totalCount : 15
     * totalPage : 1
     */

    private int pageIndex;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<CircleInvitationData> pageDatas;

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

    public List<CircleInvitationData> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<CircleInvitationData> pageDatas) {
        this.pageDatas = pageDatas;
    }

    public static class CircleInvitationData {
        /**
         * aboutId : 4
         * messageBody : {"childType":1,"circleId":105,"circleName":"外汇交易圈","msg":null,"userId":284003,"userImage":"https://fxchatimage.fx110.com/headdefault/38.jpg","userName":"DIV848732"}
         * messageId : e130d5b5-9fdf-4774-84bf-bd07a13e399b
         * messageStatus : 0
         * noticeId : 5
         * receiveUid : 284002
         * sendTimestamp : 1554257796964
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
        private CircleInvitationInfo info;

        public CircleInvitationInfo getInfo() {
            if (info == null) info = new Gson().fromJson(messageBody, CircleInvitationInfo.class);
            return info;
        }

        public void setInfo(CircleInvitationInfo info) {
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

    public static class CircleInvitationInfo{

        /**
         * childType : 1
         * circleId : 105
         * circleName : 外汇交易圈
         * msg : null
         * userId : 284003
         * userImage : https://fxchatimage.fx110.com/headdefault/38.jpg
         * userName : DIV848732
         */

        private int childType;
        private int circleId;
        private String circleName;
        private String msg;
        private int userId;
        private String userImage;
        private String userName;
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

        public int getCircleId() {
            return circleId;
        }

        public void setCircleId(int circleId) {
            this.circleId = circleId;
        }

        public String getCircleName() {
            return circleName;
        }

        public void setCircleName(String circleName) {
            this.circleName = circleName;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
