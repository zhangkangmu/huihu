package com.huihu.module_notification.like.likeinterface;

import com.huihu.module_notification.like.entity.LikeMePageBean;

public interface ILikePresenter {
    void v2pGetFirstData();
    void v2pGetMoreData(LikeMePageBean.PageDatasBean bean);
    void m2pGetDataSuccess(LikeMePageBean bean, boolean isMore);
    void m2pNetFail();
    void m2pGetDataComplete();
    void m2pGetDataFail();
    void v2pLookOtherPeople(LikeMePageBean.PageDatasBean bean);
}
