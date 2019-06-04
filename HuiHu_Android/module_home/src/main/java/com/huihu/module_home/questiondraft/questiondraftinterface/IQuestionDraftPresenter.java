package com.huihu.module_home.questiondraft.questiondraftinterface;

import com.huihu.module_home.questiondraft.model.GetDraftModel;

public interface IQuestionDraftPresenter {

    void v2pGetDraftList(int timeStamp, int pagesize, int question, int uid);

    void m2pGetDraftListSuccess(GetDraftModel model);

    void m2pGetDraftListFail(String message);
}
