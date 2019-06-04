package com.huihu.module_circle.discussdetail.entity;

import java.util.List;

/**
 * Created by jiangwensong on 2019/3/30.
 * description：
 */
public class CommentBean {

    private List<CommentOne> pageDatas;
    private int pageIndex;
    private int pageSize;
    private int totalCount;
    private int totalPage;

    public List<CommentOne> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<CommentOne> pageDatas) {
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
