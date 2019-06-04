package com.huihu.module_home.questionandanswerlist.questionandanswerlistinterface;

import com.huihu.module_home.questionandanswerlist.entity.AnswerModel;
import com.huihu.module_home.questionandanswerlist.entity.GetAnswerInfoParamModel;
import com.huihu.module_home.questionandanswerlist.entity.QuestionDetailModel;

public interface IQuestionAndAnswerListPresenter {

    void v2pGetQuestionInfo(long ideaId);

    void m2pGetQuestionInfoSuccess(QuestionDetailModel model);

    void v2pGetAnswerList(GetAnswerInfoParamModel model);

    void m2pGetAnswerInfoSuccess(AnswerModel answerModel);

    void m2pGetAnswerInfoFail(String message);

    void v2pPutGiveFollows(long aboutId, int followType, int state, int uid);

    void m2pPutGiveFollowsFail(String msg);

    void m2pPutGiveFollowsSuccess();

    void m2pRefreshFinish();

    void m2pPutGivePersonSuccess();

    void v2pShowShareQuestionDialog(int uid, int state);
}
