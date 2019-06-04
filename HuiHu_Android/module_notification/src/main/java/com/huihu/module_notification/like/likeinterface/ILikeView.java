package com.huihu.module_notification.like.likeinterface;

import com.huihu.module_notification.like.entity.LikeMePageBean;

import java.util.List;

public interface ILikeView {
    void p2vShowFirstData(List<LikeMePageBean.PageDatasBean> beans);
    void p2vShowMoreData(List<LikeMePageBean.PageDatasBean> beans);
    void p2vGetDataComplete();
    void p2vShowNoData();
    void p2vShowGetDataFail();
    void p2vStartOtherPeople(long uid);
}
