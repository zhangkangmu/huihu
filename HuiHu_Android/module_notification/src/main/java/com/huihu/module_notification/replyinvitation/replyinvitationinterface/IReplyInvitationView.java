package com.huihu.module_notification.replyinvitation.replyinvitationinterface;

import com.huihu.module_notification.replyinvitation.entity.ReplyInvitationPageBean;

import java.util.List;

public interface IReplyInvitationView {
    void p2vShowFirstData(List<ReplyInvitationPageBean.ReplyInvitationBean> beans);
    void p2vShowMoreData(List<ReplyInvitationPageBean.ReplyInvitationBean> beans);
    void p2vShowNoData();
    void p2vShowNetFail();
    void p2vGetDataComplete();
    void p2vStartOtherPeople(long uid);
    void p2vStartQuestion(long questionId);
    void p2vChangeReadState(ReplyInvitationPageBean.ReplyInvitationBean bean);
}
