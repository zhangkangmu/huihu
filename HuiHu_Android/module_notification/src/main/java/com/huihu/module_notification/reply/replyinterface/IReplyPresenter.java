package com.huihu.module_notification.reply.replyinterface;

import com.huihu.module_notification.reply.entity.AnswerReadState;
import com.huihu.module_notification.reply.entity.ReplyBean;

import java.util.List;

public interface IReplyPresenter {
    void v2pGetFirstDataAttention();
    void v2pGetFirstDataMine();
    void v2pGetMoreDataAttention(ReplyBean bean);
    void v2pGetMoreDataMine(ReplyBean bean);
    void v2pReadAnswer(int type, ReplyBean bean);
    void v2pReadQuestion(int type, ReplyBean bean);
    void v2pLookOtherPeople(ReplyBean bean);
    void m2pGetDataSuccessAttention(List<ReplyBean> beans, boolean isMore);
    void m2pGetDataSuccessMine(List<ReplyBean> beans, boolean isMore);
    void m2pNetFail();
    void m2pGetDataFailAttention();
    void m2pGetDataFailMine();
    void m2pGetDataComplete();
    void m2pPutReadStateSuccess(int type, ReplyBean bean, AnswerReadState state);
}
