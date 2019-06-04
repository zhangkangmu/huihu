package com.huihu.module_notification.reply.replyinterface;

import com.huihu.module_notification.reply.entity.ReplyBean;

public interface IReplyModel {
    void m2pGetAttentionQuestionReplyList(long time, boolean isMore);
    void m2pGetMineQuestionReplyList(long time, boolean isMore);
    void m2pPutAnswerReadState(int type, ReplyBean bean);
}
