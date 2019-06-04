package com.huihu.module_circle.newcircleintroduction.entity;

import java.util.List;

public class CircleListInfo {

    /**
     * author : {"nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
     * circleId : 0
     * circleName : string
     * circleType : 0
     * createTime : 0
     * height : 0
     * imageUrl : string
     * introduce : string
     * memberNum : 0
     * memberType : 0
     * members : [{"fxCode":"string","nickName":"string","uid":0,"userAuth":{"bu":0,"id":0,"kb":0,"niu":0},"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}]
     * width : 0
     */

    private AuthorBean author;
    private int circleId;
    private String circleName;
    private int circleType;
    private long createTime;
    private int height;
    private String imageUrl;
    private String introduce;
    private int memberNum;
    private int memberType;
    private int width;
    private List<MembersBean> members;

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
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

    public int getCircleType() {
        return circleType;
    }

    public void setCircleType(int circleType) {
        this.circleType = circleType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }

    public int getMemberType() {
        return memberType;
    }

    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public List<MembersBean> getMembers() {
        return members;
    }

    public void setMembers(List<MembersBean> members) {
        this.members = members;
    }

    public static class AuthorBean {
        /**
         * nickName : string
         * uid : 0
         * userHeadImage : string
         * userHeadImg_120 : string
         * userHeadImg_48 : string
         * userHeadImg_80 : string
         */

        private String nickName;
        private int uid;
        private String userHeadImage;
        private String userHeadImg_120;
        private String userHeadImg_48;
        private String userHeadImg_80;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUserHeadImage() {
            return userHeadImage;
        }

        public void setUserHeadImage(String userHeadImage) {
            this.userHeadImage = userHeadImage;
        }

        public String getUserHeadImg_120() {
            return userHeadImg_120;
        }

        public void setUserHeadImg_120(String userHeadImg_120) {
            this.userHeadImg_120 = userHeadImg_120;
        }

        public String getUserHeadImg_48() {
            return userHeadImg_48;
        }

        public void setUserHeadImg_48(String userHeadImg_48) {
            this.userHeadImg_48 = userHeadImg_48;
        }

        public String getUserHeadImg_80() {
            return userHeadImg_80;
        }

        public void setUserHeadImg_80(String userHeadImg_80) {
            this.userHeadImg_80 = userHeadImg_80;
        }
    }

    public static class MembersBean {
        /**
         * fxCode : string
         * nickName : string
         * uid : 0
         * userAuth : {"bu":0,"id":0,"kb":0,"niu":0}
         * userHeadImage : string
         * userHeadImg_120 : string
         * userHeadImg_48 : string
         * userHeadImg_80 : string
         */

        private String fxCode;
        private String nickName;
        private int uid;
        private UserAuthBean userAuth;
        private String userHeadImage;
        private String userHeadImg_120;
        private String userHeadImg_48;
        private String userHeadImg_80;

        public String getFxCode() {
            return fxCode;
        }

        public void setFxCode(String fxCode) {
            this.fxCode = fxCode;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public UserAuthBean getUserAuth() {
            return userAuth;
        }

        public void setUserAuth(UserAuthBean userAuth) {
            this.userAuth = userAuth;
        }

        public String getUserHeadImage() {
            return userHeadImage;
        }

        public void setUserHeadImage(String userHeadImage) {
            this.userHeadImage = userHeadImage;
        }

        public String getUserHeadImg_120() {
            return userHeadImg_120;
        }

        public void setUserHeadImg_120(String userHeadImg_120) {
            this.userHeadImg_120 = userHeadImg_120;
        }

        public String getUserHeadImg_48() {
            return userHeadImg_48;
        }

        public void setUserHeadImg_48(String userHeadImg_48) {
            this.userHeadImg_48 = userHeadImg_48;
        }

        public String getUserHeadImg_80() {
            return userHeadImg_80;
        }

        public void setUserHeadImg_80(String userHeadImg_80) {
            this.userHeadImg_80 = userHeadImg_80;
        }

        public static class UserAuthBean {
            /**
             * bu : 0
             * id : 0
             * kb : 0
             * niu : 0
             */

            private int bu;
            private int id;
            private int kb;
            private int niu;

            public int getBu() {
                return bu;
            }

            public void setBu(int bu) {
                this.bu = bu;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getKb() {
                return kb;
            }

            public void setKb(int kb) {
                this.kb = kb;
            }

            public int getNiu() {
                return niu;
            }

            public void setNiu(int niu) {
                this.niu = niu;
            }
        }
    }
}
