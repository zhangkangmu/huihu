package com.huihu.module_home.answerdetail.answerdetailinterface;

import com.huihu.module_home.answerdetail.entity.AnswerInfo;

public interface IAnswerDetailModel {
    void p2mGetAnswerInfo(long ideaId,long uid);

    void p2mPutGiveFollows(AnswerInfo answerInfo);

    void p2mPutGiveViewpoint(long commentId, long uid, int viewPoint, int viewPointType);

    void p2mConstrolCollection(long ideaId,long uid,boolean isAddCollection);
}
