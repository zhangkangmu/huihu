package com.huihu.module_home.answerdetail.presenter;

import com.huihu.module_home.answerdetail.answerdetailinterface.IAnswerDetailModel;
import com.huihu.module_home.answerdetail.answerdetailinterface.IAnswerDetailPresenter;
import com.huihu.module_home.answerdetail.answerdetailinterface.IAnswerDetailView;
import com.huihu.module_home.answerdetail.entity.AnswerInfo;
import com.huihu.module_home.answerdetail.model.ImpAnswerDetailModel;

public class ImpAnswerDetailPresenter implements IAnswerDetailPresenter {
    private final IAnswerDetailModel iAnswerDetailModel = new ImpAnswerDetailModel(this);
    private final IAnswerDetailView iAnswerDetailView;

    public ImpAnswerDetailPresenter(IAnswerDetailView iAnswerDetailView) {
        this.iAnswerDetailView = iAnswerDetailView;
    }

    @Override
    public void v2pGetAnswerInfo(long ideaId,long uid) {
        iAnswerDetailModel.p2mGetAnswerInfo(ideaId,uid);
    }

    @Override
    public void m2pReturnAnswerInfo(AnswerInfo answerInfo) {
        iAnswerDetailView.p2vReturnAnswerInfo(answerInfo);

    }

    @Override
    public void m2pNetFail() {

    }

    @Override
    public void m2pNetComplete() {
        iAnswerDetailView.p2vGetDataEnd();
    }

    @Override
    public void v2pPutGiveFollows(AnswerInfo answerInfo) {
        iAnswerDetailModel.p2mPutGiveFollows(answerInfo);
    }

    @Override
    public void m2pPutGiveFollows() {
        iAnswerDetailView.p2vPutGiveFollows();
    }

    @Override
    public void m2pPutGiveFollowsError(String subCode) {
        iAnswerDetailView.p2vPutGiveFollowsError(subCode);
    }

    @Override
    public void v2pPutGiveViewpoint(long commentId, long uid, int viewPoint, int viewPointType) {
        iAnswerDetailModel.p2mPutGiveViewpoint(commentId,uid,viewPoint,viewPointType);
    }

    @Override
    public void m2pPutGiveViewpoint(int viewpoint) {
        iAnswerDetailView.p2vPutGiveViewpoint(viewpoint);
    }

    @Override
    public void m2pPutGiveViewpointError(String subCode) {
        iAnswerDetailView.p2vPutGiveViewpointError(subCode);
    }


    @Override
    public void v2pConstrolCollection(long ideaId,long uid,boolean isAddCollection) {
        iAnswerDetailModel.p2mConstrolCollection(ideaId,uid,isAddCollection);
    }

    @Override
    public void m2pChangeCollection(boolean isAddCollection) {
        iAnswerDetailView.p2vChangeCollection(isAddCollection);
    }
}
