package com.huihu.module_home.answerdetail.answerdetailinterface;

import com.huihu.module_home.answerdetail.entity.AnswerInfo;

public interface IAnswerDetailView {
    void p2vShowNoData();
    void p2vGetDataEnd();
    void p2vReturnAnswerInfo(AnswerInfo answerInfo);
    void p2vPutGiveFollows();
    void p2vPutGiveFollowsError(String subCode);
    void p2vPutGiveViewpoint(int viewpoint);
    void p2vPutGiveViewpointError(String subCode);
    void p2vChangeCollection(boolean isAddCollection);
}
