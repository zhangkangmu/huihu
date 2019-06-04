package com.huihu.module_notification.fans.fansinterface;

import com.huihu.module_notification.fans.entity.FollowPageBean;

public interface IFansModel {
    void p2mGetFansOrFollowListNet(long followId, int type, boolean isMore);
    void p2mPutGiveFollows(int type, FollowPageBean.DataBean bean);
}
