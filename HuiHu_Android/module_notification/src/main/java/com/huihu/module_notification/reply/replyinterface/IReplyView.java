package com.huihu.module_notification.reply.replyinterface;

import com.huihu.module_notification.reply.entity.ReplyBean;

import java.util.List;

public interface IReplyView {
    void p2vShowFirstDataMine(List<ReplyBean> beans);
    void p2vShowMoreDataMine(List<ReplyBean> beans);
    void p2vShowFirstDataAttention(List<ReplyBean> beans);
    void p2vShowMoreDataAttention(List<ReplyBean> beans);
    void p2vChangeReplyReadStats(int type, ReplyBean bean);
    void p2vGetDataComplete();
    void p2vGetDataFailMine();
    void p2vGetDataFailAttention();
    void p2vShowNoDataMine();
    void p2vShowNoDataAttention();
    void p2vStartAnswer(long answerId);
    void p2vStartQuestion(long questionId);
    void p2vStartOtherPeople(long uid);
}
