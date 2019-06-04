package com.huihu.module_home.addquestioncategory.entitiy;

import java.io.Serializable;

/**
 * create by ouyangjianfeng on 2019/4/19
 * description:
 */
public class PostCategoryByTitleModel implements Serializable {

    /**
     * pageIndex : 0
     * pageSize : 0
     * title : string
     * type : 0
     */

    private int pageIndex;
    private int pageSize;
    private String title;
    private int type;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
