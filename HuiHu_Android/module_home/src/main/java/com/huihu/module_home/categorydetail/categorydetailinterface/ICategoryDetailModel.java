package com.huihu.module_home.categorydetail.categorydetailinterface;

import com.huihu.module_home.categorydetail.entity.CategoryInfoBean;
import com.huihu.module_home.categorydetail.entity.PageDatasBean;

public interface ICategoryDetailModel {
    void p2mGetAnswerListNet(int categoryId, int order, boolean isMore, PageDatasBean bean);
    void p2mGetCategoryInfoNet(int categoryId);
    void p2mPutGiveFollowsNet(PageDatasBean bean);
    void p2mPutGiveFollowsNet(CategoryInfoBean bean);
}
