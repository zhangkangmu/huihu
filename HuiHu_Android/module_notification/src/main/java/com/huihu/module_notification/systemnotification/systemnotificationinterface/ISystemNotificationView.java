package com.huihu.module_notification.systemnotification.systemnotificationinterface;

import com.huihu.module_notification.systemnotification.entity.SystemNoticePageBean;

import java.util.List;

public interface ISystemNotificationView {
    void p2vShowFirstData(List<SystemNoticePageBean.SystemNoticeBean> sessions);
    void p2vShowMoreData(List<SystemNoticePageBean.SystemNoticeBean> sessions);
    void p2vShowNoData();
    void p2vShowNetError();
    void p2vShowGetDataComplete();
}
