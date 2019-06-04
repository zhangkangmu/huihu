package com.huihu.module_notification.notification.notificationinterface;

import com.huihu.module_notification.notification.entity.MessageSession;

public interface INotificationModel {
    void p2mGetMessageSessionListNet();
    void p2mPutAllReadNet();
    void p2mDeleteSessionNet(MessageSession session);
}
