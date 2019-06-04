package com.huihu.module_notification.notification.notificationinterface;

import android.view.View;

import com.huihu.module_notification.notification.entity.MessageSession;
import com.huihu.module_notification.notification.entity.NoticeSessionBean;

public interface INotificationPresenter {
    void v2pStartOther(MessageSession session);
    void v2pStartOther(int otherId);
    void v2pGetListData();
    void v2pAllRead();
    void v2pMenu(MessageSession session, View view);
    void v2pChangeMessageState(MessageSession session);
    void v2pDeleteSession(MessageSession session);
    void v2pSubscribeNoticeMessage();
    void v2pUnSubscribeNoticeMessage();
    void m2pGetListDataSuccess(NoticeSessionBean bean);
    void m2pAllReadSuccess();
    void m2pDeleteSessionSuccess(MessageSession session);
    void m2pNetFail();
}
