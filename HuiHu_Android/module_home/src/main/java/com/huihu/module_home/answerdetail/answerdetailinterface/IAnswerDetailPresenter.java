package com.huihu.module_home.answerdetail.answerdetailinterface;

import com.huihu.module_home.answerdetail.entity.AnswerInfo;

public interface IAnswerDetailPresenter {
    void v2pGetAnswerInfo(long ideaId,long uid);
    void m2pReturnAnswerInfo(AnswerInfo answerInfo);
    void m2pNetFail();
    void m2pNetComplete();
    void v2pPutGiveFollows(AnswerInfo answerInfo);
    void m2pPutGiveFollows();
    void m2pPutGiveFollowsError(String subCode);
    void v2pPutGiveViewpoint(long commentId,long uid,int viewPoint,int viewPointType);
    void m2pPutGiveViewpoint(int viewpoint);
    void m2pPutGiveViewpointError(String subCode);
    //操作添加收藏和取消收藏
    void v2pConstrolCollection(long ideaId,long uid,boolean isAddCollection);
    void m2pChangeCollection(boolean isAddCollection);
}
