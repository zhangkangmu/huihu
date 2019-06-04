package com.huihu.module_notification.like.entity;

import com.huihu.module_notification.entity.NetImageBean;
import com.huihu.module_notification.entity.UserInfo;

import java.util.List;

/**
 * create by wangjing on 2019/3/29 0029
 * description:
 */
public class LikeMePageBean {

    /**
     * pageDatas : [{"content":"string","contentId":0,"contentImages":[{"imageDesc":"string","imageId":0,"imageUrl":"string"}],"contentUid":0,"contentUserInfo":{"authBewrite":"string","authName":"string","follow":0,"fxCode":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"},"createTime":0,"type":0,"viewpoint":0,"viewpointUid":0,"viewpointUserInfo":{"authBewrite":"string","authName":"string","follow":0,"fxCode":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"},"viewpointsId":0}]
     * pageIndex : 0
     * pageSize : 0
     * totalCount : 0
     * totalPage : 0
     */

    private int pageIndex;
    private int pageSize;
    private int totalCount;
    private int totalPage;
    private List<PageDatasBean> pageDatas;

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

    public List<PageDatasBean> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<PageDatasBean> pageDatas) {
        this.pageDatas = pageDatas;
    }

    public static class PageDatasBean {
        /**
         * content : string
         * contentId : 0
         * contentImages : [{"imageDesc":"string","imageId":0,"imageUrl":"string"}]
         * contentUid : 0
         * contentUserInfo : {"authBewrite":"string","authName":"string","follow":0,"fxCode":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
         * createTime : 0
         * type : 0
         * viewpoint : 0
         * viewpointUid : 0
         * viewpointUserInfo : {"authBewrite":"string","authName":"string","follow":0,"fxCode":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}
         * viewpointsId : 0
         */

        private String content;
        private int contentId;
        private int contentUid;
        private UserInfo contentUserInfo;
        private long createTime;
        private int type;
        private int viewpoint;
        private int viewpointUid;
        private UserInfo viewpointUserInfo;
        private int viewpointsId;
        private List<NetImageBean> contentImages;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getContentId() {
            return contentId;
        }

        public void setContentId(int contentId) {
            this.contentId = contentId;
        }

        public int getContentUid() {
            return contentUid;
        }

        public void setContentUid(int contentUid) {
            this.contentUid = contentUid;
        }

        public UserInfo getContentUserInfo() {
            return contentUserInfo;
        }

        public void setContentUserInfo(UserInfo contentUserInfo) {
            this.contentUserInfo = contentUserInfo;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getViewpoint() {
            return viewpoint;
        }

        public void setViewpoint(int viewpoint) {
            this.viewpoint = viewpoint;
        }

        public int getViewpointUid() {
            return viewpointUid;
        }

        public void setViewpointUid(int viewpointUid) {
            this.viewpointUid = viewpointUid;
        }

        public UserInfo getViewpointUserInfo() {
            return viewpointUserInfo;
        }

        public void setViewpointUserInfo(UserInfo viewpointUserInfo) {
            this.viewpointUserInfo = viewpointUserInfo;
        }

        public int getViewpointsId() {
            return viewpointsId;
        }

        public void setViewpointsId(int viewpointsId) {
            this.viewpointsId = viewpointsId;
        }

        public List<NetImageBean> getContentImages() {
            return contentImages;
        }

        public void setContentImages(List<NetImageBean> contentImages) {
            this.contentImages = contentImages;
        }


    }
}
