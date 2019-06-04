package com.huihu.module_home.questionandanswerlist.questionandanswerlistinterface;

import com.huihu.module_home.questionandanswerlist.entity.GetAnswerInfoParamModel;

public interface IQuestionAndAnswerListModel {

    void p2mGetQuestionInfo(long ideaId);

    void p2mGetAnswerList(GetAnswerInfoParamModel model);

    void p2mPutGiveFollows(long id, int type, int state, int uid);

    void p2mPostCollection(long id, int uid);
}
