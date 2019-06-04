package com.huihu.module_notification.systemnotification.systemnotificationinterface;

import com.huihu.module_notification.systemnotification.entity.SystemNoticePageBean;

public interface ISystemNotificationPresenter {
    void v2pGetFirstData();
    void v2pGetMoreData();
    void m2pGetDataComplete();
    void m2pNetFail();
    void m2pGetDataFail();
    void m2pGetListDataSuccess(SystemNoticePageBean bean);
}
