package com.huihu.module_notification.notification.notificationinterface;

import android.view.View;

import com.huihu.module_notification.notification.entity.MessageSession;

import java.util.List;

public interface INotificationView {
    void p2vStartOther(int otherId);
    void p2vStartNewAnswer(int state, long questionId);
    void p2vStartDetailAnswer(long ideaId);
    void p2cChangeUnRead(MessageSession session);
    void p2vShowListData(List<MessageSession> sessionList);
    void p2vShowTopState(List<MessageSession> sessionList);
    void p2vChangeAllUnRead();
    void p2vShowDeleteMenu(MessageSession session, View view);
    void p2vDeleteSession(MessageSession session);
    void p2vReceiveNoticeMessage(MessageSession session);
}
