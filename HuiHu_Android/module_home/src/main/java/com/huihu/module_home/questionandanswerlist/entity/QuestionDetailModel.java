package com.huihu.module_home.questionandanswerlist.entity;

import java.util.List;

/**
 * create by ouyangjianfeng on 2019/3/30
 * description:
 */
public class QuestionDetailModel {

    /**
     * answer : 0
     * answerCount : 0
     * briefContent : 手动添加数据让我很难受
     * categoryMongos : [{"category":"理论交流","categoryId":2},{"category":"行情讨论","categoryId":1},{"category":"交易心得","categoryId":3}]
     * content : <p>手动添加数据让我很难受</p>
     * editTime : 1553740374038
     * follow : 0
     * followCount : 0
     * ideaId : 148
     * images : [{"imageDesc":"string","imageId":221,"imageUrl":"string"}]
     * popular : 1
     * state : 1
     * title : 这是欧阳在做提问的时候添加的测试数据?
     */

    private Integer answer;
    private int answerCount;
    private String briefContent;
    private String content;
    private long editTime;
    private int follow;
    private int followCount;
    private int ideaId;
    private int popular;
    private int state;
    private String title;
    private List<CategoryMongosBean> categoryMongos;
    private List<ImagesBean> images;
    /**
     * userInfo : {"fxCode":"","nickName":"欧阳阳","uid":283977,"userAuth":null,"userHeadImage":"https://gaimg.fx110.com/upload/images/17/2019/03/29/201842304.jpg","userHeadImg_120":"https://gaimg.fx110.com/upload/images/17/2019/03/29/201842304.jpg","userHeadImg_48":"https://gaimg.fx110.com/upload/images/17/2019/03/29/201842304.jpg","userHeadImg_80":"https://gaimg.fx110.com/upload/images/17/2019/03/29/201842304.jpg"}
     */

    private UserInfoBean userInfo;

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public String getBriefContent() {
        return briefContent;
    }

    public void setBriefContent(String briefContent) {
        this.briefContent = briefContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getEditTime() {
        return editTime;
    }

    public void setEditTime(long editTime) {
        this.editTime = editTime;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(int ideaId) {
        this.ideaId = ideaId;
    }

    public int getPopular() {
        return popular;
    }

    public void setPopular(int popular) {
        this.popular = popular;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CategoryMongosBean> getCategoryMongos() {
        return categoryMongos;
    }

    public void setCategoryMongos(List<CategoryMongosBean> categoryMongos) {
        this.categoryMongos = categoryMongos;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class CategoryMongosBean {
        /**
         * category : 理论交流
         * categoryId : 2
         */

        private String category;
        private int categoryId;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }
    }

    public static class ImagesBean {
        /**
         * imageDesc : string
         * imageId : 221
         * imageUrl : string
         */

        private String imageDesc;
        private int imageId;
        private String imageUrl;

        public String getImageDesc() {
            return imageDesc;
        }

        public void setImageDesc(String imageDesc) {
            this.imageDesc = imageDesc;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

    public static class UserInfoBean {
        /**
         * fxCode :
         * nickName : 欧阳阳
         * uid : 283977
         * userAuth : null
         * userHeadImage : https://gaimg.fx110.com/upload/images/17/2019/03/29/201842304.jpg
         * userHeadImg_120 : https://gaimg.fx110.com/upload/images/17/2019/03/29/201842304.jpg
         * userHeadImg_48 : https://gaimg.fx110.com/upload/images/17/2019/03/29/201842304.jpg
         * userHeadImg_80 : https://gaimg.fx110.com/upload/images/17/2019/03/29/201842304.jpg
         */

        private String fxCode;
        private String nickName;
        private int uid;
        private int userAuth;
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

        public int getUserAuth() {
            return userAuth;
        }

        public void setUserAuth(int userAuth) {
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
    }
}
