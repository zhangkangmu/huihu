package com.huihu.module_notification.commentdetails.entity;

import java.util.List;

/**
 * Created by jiangwensong on 2019/4/2.
 * description：
 */

public class CommentChild {

    List<CommentTwo> pageDatas;
    int pageIndex;
    int pageSize;
    int totalCount;
    int totalPage;

    public List<CommentTwo> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<CommentTwo> pageDatas) {
        this.pageDatas = pageDatas;
    }

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
}
