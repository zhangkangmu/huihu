package com.huihu.module_home.questionandanswerlist.questionandanswerlistinterface;

import com.huihu.module_home.questionandanswerlist.entity.AnswerModel;
import com.huihu.module_home.questionandanswerlist.entity.QuestionDetailModel;

import java.util.List;

public interface IQuestionAndAnswerListView {

    void p2vShowQuestionDetail(QuestionDetailModel model);

    void p2vShowAnswer(List<AnswerModel.PageDatasBean> list);

    void p2vShowToast(String msg);

    void setButtonState();

    void p2vCheckHasData();

    void p2vRefreshFinish();

    List<AnswerModel.PageDatasBean> p2vGetDatas();

    void p2vShowContent();

    void p2vShowMyselfQusetionRelease();

    void p2vShowOtherQuestion();
}
