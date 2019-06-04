package com.huihu.module_notification.commentdetails.presenter;

import com.huihu.module_notification.commentdetails.commentdetailsinterface.ICommentDetailsModel;
import com.huihu.module_notification.commentdetails.commentdetailsinterface.ICommentDetailsPresenter;
import com.huihu.module_notification.commentdetails.commentdetailsinterface.ICommentDetailsView;
import com.huihu.module_notification.commentdetails.entity.CommentChild;
import com.huihu.module_notification.commentdetails.entity.CommentHead;
import com.huihu.module_notification.commentdetails.model.ImpCommentDetailsModel;

public class ImpCommentDetailsPresenter implements ICommentDetailsPresenter {
    private final ICommentDetailsModel iCommentDetailsModel = new ImpCommentDetailsModel(this);
    private final ICommentDetailsView iCommentDetailsView;

    public ImpCommentDetailsPresenter(ICommentDetailsView iCommentDetailsView) {
        this.iCommentDetailsView = iCommentDetailsView;
    }


    private static final int PAGE_SIZE = 10;
    private int pageIndex;
    private long time;


    @Override
    public void v2pGetHeadComment(long commentId) {
        iCommentDetailsModel.p2mGetHeadComment(commentId);
    }

    @Override
    public void m2pGetHeadCommentSuccess(CommentHead head) {
        iCommentDetailsView.p2vGetHeadCommentSuccess(head);
    }

    @Override
    public void m2pGetHeadCommentError(String error) {
        iCommentDetailsView.p2vGetHeadCommentError(error);
    }

    @Override
    public void v2pGetFirstChildCommentList(long commentId) {
        time = 0;
        iCommentDetailsModel.p2mGetChildCommentList(commentId,time,PAGE_SIZE);
    }

    @Override
    public void v2pGetMoreChildCommentList(long commentId) {
        iCommentDetailsModel.p2mGetChildCommentList(commentId,time,PAGE_SIZE);
    }

    @Override
    public void m2pGetFirstChildCommentListSuccess(CommentChild commentChild) {

        if(commentChild.getPageDatas() == null || commentChild.getPageDatas().size() == 0){
            iCommentDetailsView.p2vShowNoData();
        }else if(commentChild.getPageDatas().size() < PAGE_SIZE) {

            time = commentChild.getPageDatas().get(commentChild.getPageDatas().size()-1).getCommentTime();
            iCommentDetailsView.p2vGetFirstChildCommentListSuccess(commentChild);
            iCommentDetailsView.p2vGetLastComment();
        }else {

            time = commentChild.getPageDatas().get(commentChild.getPageDatas().size()-1).getCommentTime();
            iCommentDetailsView.p2vGetFirstChildCommentListSuccess(commentChild);
            iCommentDetailsView.p2vNoGetLastComment();
        }


    }

    @Override
    public void m2pGetMoreChildCommentListSuccess(CommentChild commentChild) {

        if(commentChild.getPageDatas() == null || commentChild.getPageDatas().size() == 0){
            iCommentDetailsView.p2vGetLastComment();
        }else if(commentChild.getPageDatas().size() < PAGE_SIZE) {
            time = commentChild.getPageDatas().get(commentChild.getPageDatas().size() - 1).getCommentTime();
            iCommentDetailsView.p2vGetMoreChildCommentListSuccess(commentChild);
            iCommentDetailsView.p2vGetLastComment();
        }else {
            time = commentChild.getPageDatas().get(commentChild.getPageDatas().size() - 1).getCommentTime();
            iCommentDetailsView.p2vGetMoreChildCommentListSuccess(commentChild);
            iCommentDetailsView.p2vNoGetLastComment();
        }
    }

    @Override
    public void m2pGetChildCommentListError(String error) {
        iCommentDetailsView.p2vGetChildCommentListError(error);
    }

    @Override
    public void m2pGetChildCommentListCompleted() {
        iCommentDetailsView.p2vGetChildCommentListCompleted();
    }


    @Override
    public void v2pPutGiveLike(long commentId, int viewpointType) {
        iCommentDetailsModel.p2mPutGiveLike(commentId,viewpointType);
    }

    @Override
    public void m2pPutGiveLikeSuccess() {
        iCommentDetailsView.p2vPutGiveLikeSuccess();
    }

    @Override
    public void m2pPutGiveLikeError(String msg) {
        iCommentDetailsView.p2vPutGiveLikeError(msg);
    }

    @Override
    public void v2pPutGiveUpLike(long commentId, int viewpointType) {
        iCommentDetailsModel.p2mPutGiveUpLike(commentId,viewpointType);
    }

    @Override
    public void m2pPutGiveUpLikeSuccess() {
        iCommentDetailsView.p2vPutGiveUpLikeSuccess();
    }

    @Override
    public void m2pPutGiveUpLikeError(String msg) {
        iCommentDetailsView.p2vPutGiveUpLikeError(msg);
    }


    @Override
    public void v2pDeleteComment(int commentGrand, long commentId) {
        iCommentDetailsModel.p2mPutDeleteComment(commentGrand,commentId);
    }

    @Override
    public void m2pDeleteCommentSuccess() {
        iCommentDetailsView.p2vDeleteCommentSuccess();

    }

    @Override
    public void m2pDeleteCommentFail(String error) {
        iCommentDetailsView.p2vDeleteCommentFail(error);
    }

}
