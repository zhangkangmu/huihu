package com.huihu.module_notification.replyinvitation.replyinvitationinterface;


import com.huihu.module_notification.replyinvitation.entity.ReplyInvitationPageBean;

public interface IReplyInvitationModel {
    void p2mGetReplyInvitationListNet(int pageIndex);
    void p2mPutInviteRead(ReplyInvitationPageBean.ReplyInvitationBean bean);
}
