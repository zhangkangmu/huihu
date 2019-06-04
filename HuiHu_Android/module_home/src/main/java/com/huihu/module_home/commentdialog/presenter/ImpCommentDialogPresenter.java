package com.huihu.module_home.commentdialog.presenter;

import com.huihu.module_home.commentdialog.commentdialoginterface.ICommentDialogModel;
import com.huihu.module_home.commentdialog.commentdialoginterface.ICommentDialogPresenter;
import com.huihu.module_home.commentdialog.commentdialoginterface.ICommentDialogView;
import com.huihu.module_home.commentdialog.entity.AnswerInfo;
import com.huihu.module_home.commentdialog.entity.CommentBean;
import com.huihu.module_home.commentdialog.entity.UserBean;
import com.huihu.module_home.commentdialog.model.ImpCommentDialogModel;

public class ImpCommentDialogPresenter implements ICommentDialogPresenter {
    private final ICommentDialogModel iCommentDialogModel = new ImpCommentDialogModel(this);
    private final ICommentDialogView iCommentDialogView;

    private int pageIndex;
    private long time;
    private int mOederType;
    private static final int PAGE_SIZE = 10;

    public ImpCommentDialogPresenter(ICommentDialogView iCommentDialogView) {
        this.iCommentDialogView = iCommentDialogView;
    }

//    @Override
//    public void v2pGetCommentList(long ideaId, int onlyAuth, int orderType){
//        iCommentDialogModel.p2mGetCommentListByIdeaId(ideaId,onlyAuth,orderType);
//    }


    @Override
    public void v2pPutGiveLike(long commentId,int viewpointType) {
        iCommentDialogModel.p2mPutGiveLike(commentId,viewpointType);
    }

    @Override
    public void m2pPutGiveLikeSuccess() {
        iCommentDialogView.p2vPutGiveLikeSuccess();
    }

    @Override
    public void m2pPutGiveLikeError(String error) {
        iCommentDialogView.p2vPutGiveLikeError(error);
    }

    @Override
    public void v2pPutGiveUpLike(long commentId, int viewpointType) {
        iCommentDialogModel.p2mPutGiveUpLike(commentId, viewpointType);
    }

    @Override
    public void m2pPutGiveUpLikeSuccess() {
        iCommentDialogView.p2vPutGiveUpLikeSuccess();
    }

    @Override
    public void m2pPutGiveUpLikeError(String error) {
        iCommentDialogView.p2vPutGiveUpLikeError(error);
    }

    @Override
    public void v2pDeleteComment(int commentGrand, long commentId) {
        iCommentDialogModel.p2mPutDeleteComment(commentGrand,commentId);
    }

    @Override
    public void m2pDeleteCommentSuccess() {
        iCommentDialogView.p2vDeleteCommentSuccess();

    }

    @Override
    public void m2pDeleteCommentFail(String error) {
        iCommentDialogView.p2vDeleteCommentFail(error);
    }

    @Override
    public void v2pGetAnswerInfo(long ideaId) {
        iCommentDialogModel.p2mGetAnswerInfo(ideaId);
    }

    @Override
    public void m2pGetAnswerInfoSuccess(AnswerInfo answerInfo) {
        iCommentDialogView.p2vGetAnswerInfoSuccess(answerInfo);
    }

    @Override
    public void m2pGetAnswerInfoFail(String error) {
        iCommentDialogView.p2vGetAnswerInfoFail(error);
    }

    @Override
    public void v2pPutGiveFollows(long aboutId, int state) {
        iCommentDialogModel.p2mPutGiveFollows(aboutId,state);
    }

    @Override
    public void m2pGiveFollowsSuccess() {
        iCommentDialogView.p2vPutGiveFollowsSuccess();
    }

    @Override
    public void m2pGiveFollowsError(String error) {
        iCommentDialogView.p2vPutGiveFollowsError(error);
    }

    @Override
    public void v2pGetFirstCommentList(long ideaId,int onlyAuth,int orderType) {
        time = 0;
        mOederType = orderType;
        iCommentDialogModel.p2mGetCommentListByIdeaId(ideaId,onlyAuth,orderType,time,PAGE_SIZE);
    }

    @Override
    public void v2pGetMoreCommentList(long ideaId,int onlyAuth,int orderType) {
        mOederType = orderType;
        iCommentDialogModel.p2mGetCommentListByIdeaId(ideaId,onlyAuth,orderType,time,PAGE_SIZE);
    }

    @Override
    public void m2pGetFirstCommentListSuccess(CommentBean commentBean) {
        if(commentBean.getPageDatas() == null || commentBean.getPageDatas().size() == 0){
            iCommentDialogView.p2vShowNoData();
        }else if(commentBean.getPageDatas().size() < PAGE_SIZE) {

                time = commentBean.getPageDatas().get(commentBean.getPageDatas().size() - 1).getCommentTime();
                iCommentDialogView.p2vGetFirstCommentListSuccess(commentBean);
            iCommentDialogView.p2vGetLastComment();
        }else {

            time = commentBean.getPageDatas().get(commentBean.getPageDatas().size() - 1).getCommentTime();
            iCommentDialogView.p2vGetFirstCommentListSuccess(commentBean);
            iCommentDialogView.p2vNoGetLastComment();
        }
    }

    @Override
    public void m2pGetMoreCommentListSuccess(CommentBean commentBean) {
        if(commentBean.getPageDatas() == null || commentBean.getPageDatas().size() == 0){
            //TODO 已无数据
            iCommentDialogView.p2vGetLastComment();
        }else if (commentBean.getPageDatas().size() < PAGE_SIZE){

            time = commentBean.getPageDatas().get(commentBean.getPageDatas().size()-1).getCommentTime() ;
            iCommentDialogView.p2vGetMoreCommentListSuccess(commentBean);
            iCommentDialogView.p2vGetLastComment();
        }else {

            time = commentBean.getPageDatas().get(commentBean.getPageDatas().size()-1).getCommentTime() ;
            iCommentDialogView.p2vGetMoreCommentListSuccess(commentBean);
            iCommentDialogView.p2vNoGetLastComment();
        }
    }

    @Override
    public void m2pGetCommentListError(String error) {
        iCommentDialogView.p2vGetCommentListError(error);
    }

    @Override
    public void m2pGetCommentListCompleted() {
        iCommentDialogView.p2vGetCommentListCompleted();
    }

}
