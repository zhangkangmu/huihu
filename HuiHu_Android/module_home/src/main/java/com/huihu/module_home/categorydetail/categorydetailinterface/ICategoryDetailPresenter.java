package com.huihu.module_home.categorydetail.categorydetailinterface;

import com.huihu.module_home.categorydetail.entity.CategoryInfoBean;
import com.huihu.module_home.categorydetail.entity.CategoryPageBean;
import com.huihu.module_home.categorydetail.entity.PageDatasBean;

public interface ICategoryDetailPresenter {
    void v2pGetFirstList(int categoryId, int order);
    void v2pGetMoreList(PageDatasBean bean, int categoryId, int order);
    void v2pGetInfo(int categoryId);
    void v2pPutGiveFollows(PageDatasBean bean);
    void v2pPutGiveFollows(CategoryInfoBean bean);
    void v2pPutFollowSuccess(PageDatasBean bean);
    void v2pPutFollowSuccess(CategoryInfoBean bean);
    void m2pGetListSuccess(CategoryPageBean bean, boolean isMore);
    void m2pGetInfoSuccess(CategoryInfoBean bean);
    void m2pGetListComplete();
    void m2pNetFail();
    void m2pGetListDataFail();
}
