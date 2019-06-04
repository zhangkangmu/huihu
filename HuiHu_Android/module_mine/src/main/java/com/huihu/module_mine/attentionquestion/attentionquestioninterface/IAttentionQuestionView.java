package com.huihu.module_mine.attentionquestion.attentionquestioninterface;

import com.huihu.module_mine.attentionquestion.entity.AttentionQuestionInfo;

import java.util.List;

public interface IAttentionQuestionView {
    void p2vReturnAttentionQuestionList(List<AttentionQuestionInfo.PageDatasBean> pageDatas);

    void p2vNetFailed();

    void p2vShowNoData();

    void p2vReturnMoreAttentionQuestionList(List<AttentionQuestionInfo.PageDatasBean> pageDatas);

    void p2vNetComplete();
}
