package com.huihu.module_notification.notification.presenter;

import android.view.View;

import com.google.gson.Gson;
import com.huihu.commonlib.callback.ShareDataChangedCallback;
import com.huihu.commonlib.shareData.ShareDataUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.notification.entity.MessageSession;
import com.huihu.module_notification.notification.entity.NoticeSessionBean;
import com.huihu.module_notification.notification.entity.session.AnswerSession;
import com.huihu.module_notification.notification.notificationinterface.INotificationModel;
import com.huihu.module_notification.notification.notificationinterface.INotificationPresenter;
import com.huihu.module_notification.notification.notificationinterface.INotificationView;
import com.huihu.module_notification.notification.model.ImpNotificationModel;
import com.huihu.uilib.ShareDataKey;
import com.huihu.uilib.def.NetDataBoolean;

import java.util.List;

public class ImpNotificationPresenter implements INotificationPresenter {

    private final INotificationModel iNotificationModel = new ImpNotificationModel(this);
    private final INotificationView iNotificationView;
    private static final String SubscribeName = "NoticeMessageSubscribeNotificationIPresenter";

    public ImpNotificationPresenter(INotificationView iNotificationView) {
        this.iNotificationView = iNotificationView;
    }

    @Override
    public void v2pStartOther(MessageSession session) {
        if (session.getNoticeId() == NoticeId.REPLY){
            if (session.getInfo() instanceof AnswerSession){
                AnswerSession answerSession = (AnswerSession) session.getInfo();
                if (answerSession.getAnswerList().size() > 1){
                    iNotificationView.p2vStartNewAnswer(session.getMessageStatus(), ((AnswerSession) session.getInfo()).getIdeaId());
                } else {
                    iNotificationView.p2vStartDetailAnswer(((AnswerSession) session.getInfo()).getAnswerList().get(0).getAnswerId());
                }
            }
        } else {
            iNotificationView.p2vStartOther(session.getNoticeId());
        }
    }

    @Override
    public void v2pStartOther(int otherId) {
        iNotificationView.p2vStartOther(otherId);
    }

    @Override
    public void v2pGetListData() {
        iNotificationModel.p2mGetMessageSessionListNet();
    }

    @Override
    public void v2pAllRead() {
        iNotificationModel.p2mPutAllReadNet();
    }

    @Override
    public void v2pMenu(MessageSession session, View view) {
        if (session.getNoticeId() == NoticeId.REPLY){
            iNotificationView.p2vShowDeleteMenu(session, view);
        }
    }

    @Override
    public void v2pChangeMessageState(MessageSession session) {
        if (session.getNoticeId() != NoticeId.REPLY_INVITE) {
            session.setMessageStatus(NetDataBoolean.NET_TRUE);
        }
        iNotificationView.p2cChangeUnRead(session);
    }

    @Override
    public void v2pDeleteSession(MessageSession session) {
        iNotificationModel.p2mDeleteSessionNet(session);
    }

    @Override
    public void v2pSubscribeNoticeMessage() {
        ShareDataUtils.getInstance().subscribe(ShareDataKey.MessageKey, SubscribeName, new ShareDataChangedCallback() {
            @Override
            public void update(Object obj) {
                if (obj instanceof String){
                    String body = (String) obj;
                    MessageSession session = new Gson().fromJson(body, MessageSession.class);
                    iNotificationView.p2vReceiveNoticeMessage(session);
                }
            }
        });
    }

    @Override
    public void v2pUnSubscribeNoticeMessage() {
        ShareDataUtils.getInstance().unSubscribe(ShareDataKey.MessageKey, SubscribeName);
    }

    @Override
    public void m2pGetListDataSuccess(NoticeSessionBean bean) {
        if (bean.getMessageSessionList() != null){
            iNotificationView.p2vShowListData(bean.getMessageSessionList());
        }
        if (bean.getMessageSessionList() != null){
            iNotificationView.p2vShowTopState(bean.getMessageSessionHeader());
        }
    }

    @Override
    public void m2pAllReadSuccess() {
        iNotificationView.p2vChangeAllUnRead();
    }

    @Override
    public void m2pDeleteSessionSuccess(MessageSession session) {
        iNotificationView.p2vDeleteSession(session);
    }

    @Override
    public void m2pNetFail() {
        ToastUtil.show(R.string.uilib_http_request_fail);
    }

}
