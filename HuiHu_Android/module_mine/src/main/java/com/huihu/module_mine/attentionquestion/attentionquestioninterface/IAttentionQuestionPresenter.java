package com.huihu.module_mine.attentionquestion.attentionquestioninterface;

import com.huihu.module_mine.attentionquestion.entity.AttentionQuestionInfo;

import java.util.List;

public interface IAttentionQuestionPresenter {
    void v2pGetAttentionQuestionList();

    void m2pReturnAttentionQuestionList(List<AttentionQuestionInfo.PageDatasBean> pageDatas,boolean isMore);

    void m2pNetFailed();

    void v2pGetMoreQuestionList(AttentionQuestionInfo.PageDatasBean pageDatasBean);

    void m2pNetComplete();
}
