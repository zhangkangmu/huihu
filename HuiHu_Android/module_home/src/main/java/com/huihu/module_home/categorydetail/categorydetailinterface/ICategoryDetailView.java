package com.huihu.module_home.categorydetail.categorydetailinterface;

import com.huihu.module_home.categorydetail.entity.CategoryInfoBean;
import com.huihu.module_home.categorydetail.entity.PageDatasBean;

import java.util.List;

public interface ICategoryDetailView {
    void p2vShowFirstList(List<PageDatasBean> beans);
    void p2vShowMoreList(List<PageDatasBean> beans);
    void p2vChangeFollowState(CategoryInfoBean bean);
    void p2vChangeFollowState(PageDatasBean bean);
    void p2vGetListComplete();
    void p2vShowInfo(CategoryInfoBean bean);
    void p2vShowNoData();
    void p2vShowNetError();
}
