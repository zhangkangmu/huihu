package com.huihu.module_notification.fans.fansinterface;

import com.huihu.module_notification.fans.entity.FollowPageBean;

public interface IFansPresenter {
    void v2pGetFirstData(int type);
    void v2pGetMoreData(int type, FollowPageBean.DataBean bean);
    void v2pPutGiveFollows(int type, FollowPageBean.DataBean bean);
    void m2pGetFansOrFollowSuccess(int type, boolean isMore, FollowPageBean bean);
    void m2pPutGiveFollowsSuccess(int type, FollowPageBean.DataBean bean);
    void m2pNetFail();
    void m2pGetFansFail(int type);
    void m2pGetDataComplete();
    void v2pLookOtherPeople(FollowPageBean.DataBean bean);
}
