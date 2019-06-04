package com.huihu.module_notification.replyinvitation.replyinvitationinterface;

import com.huihu.module_notification.reply.entity.AnswerReadState;
import com.huihu.module_notification.replyinvitation.entity.ReplyInvitationPageBean;

public interface IReplyInvitationPresenter {
    void v2pGetFirstListData();
    void v2pGetMoreListData();
    void m2pGetListDataSuccess(ReplyInvitationPageBean bean);
    void m2pNetFail();
    void m2pGetDataComplete();
    void m2pGetDataFail();
    void v2pLookOtherPeople(ReplyInvitationPageBean.ReplyInvitationBean bean);
    void v2pLookQuestion(ReplyInvitationPageBean.ReplyInvitationBean bean);
    void m2pReadSuccess(ReplyInvitationPageBean.ReplyInvitationBean bean, AnswerReadState state);
}
