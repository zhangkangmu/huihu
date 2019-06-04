package com.huihu.module_home.categorydetail.entity;

import java.util.List;

/**
 * create by wangjing on 2019/4/1 0001
 * description:
 */
public class CategoryPageBean {

    /**
     * pageDatas : [{"agreeCount":0,"briefContent":"string","commentCount":0,"editTime":0,"ideaId":0,"images":[{"imageDesc":"string","imageId":0,"imageUrl":"string"}],"popular":0,"questionId":0,"title":"string","userInfo":{"authBewrite":"string","authName":"string","follow":0,"fxCode":"string","nickName":"string","uid":0,"userHeadImage":"string","userHeadImg_120":"string","userHeadImg_48":"string","userHeadImg_80":"string"}}]
     * pageSize : 0
     * timestamp : 0
     */

    private int pageSize;
    private long timestamp;
    private List<PageDatasBean> pageDatas;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public List<PageDatasBean> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<PageDatasBean> pageDatas) {
        this.pageDatas = pageDatas;
    }

}
