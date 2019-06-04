package com.huihu.module_notification.fans.fansinterface;

import com.huihu.module_notification.fans.entity.FollowPageBean;

import java.util.List;

public interface IFansView {
    void p2vShowFirstData(int type, List<FollowPageBean.DataBean> beans);
    void p2vShowMoreData(int type, List<FollowPageBean.DataBean> beans);
    void p2vShowNoData(int type);
    void p2vShowNoNet(int type);
    void p2vGetDataComplete();
    void p2vChangeFollowViewState(int type, FollowPageBean.DataBean bean);
    void p2vStartOtherPeople(long uid);
}
