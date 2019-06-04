package com.huihu.module_home.commentchilddialog.presenter;

import com.huihu.module_home.commentchilddialog.commentchilddialoginterface.ICommentChildDialogModel;
import com.huihu.module_home.commentchilddialog.commentchilddialoginterface.ICommentChildDialogPresenter;
import com.huihu.module_home.commentchilddialog.commentchilddialoginterface.ICommentChildDialogView;
import com.huihu.module_home.commentchilddialog.entity.CommentChild;
import com.huihu.module_home.commentchilddialog.entity.CommentHead;
import com.huihu.module_home.commentchilddialog.model.ImpCommentChildDialogModel;

public class ImpCommentChildDialogPresenter implements ICommentChildDialogPresenter {
    private final ICommentChildDialogModel iCommentChildDialogModel = new ImpCommentChildDialogModel(this);
    private final ICommentChildDialogView iCommentChildDialogView;

    private static final int PAGE_SIZE = 10;
    private int pageIndex;
    private long time;

    public ImpCommentChildDialogPresenter(ICommentChildDialogView iCommentChildDialogView) {
        this.iCommentChildDialogView = iCommentChildDialogView;
    }


    @Override
    public void v2pGetHeadComment(long commentId) {
        iCommentChildDialogModel.p2mGetHeadComment(commentId);
    }

    @Override
    public void m2pGetHeadCommentSuccess(CommentHead head) {
        iCommentChildDialogView.p2vGetHeadCommentSuccess(head);
    }

    @Override
    public void m2pGetHeadCommentError(String error) {
        iCommentChildDialogView.p2vGetHeadCommentError(error);
    }

    @Override
    public void v2pGetFirstChildCommentList(long commentId) {
        time = 0;
        iCommentChildDialogModel.p2mGetChildCommentList(commentId,time,PAGE_SIZE);
    }

    @Override
    public void v2pGetMoreChildCommentList(long commentId) {
        iCommentChildDialogModel.p2mGetChildCommentList(commentId,time,PAGE_SIZE);
    }

    @Override
    public void m2pGetFirstChildCommentListSuccess(CommentChild commentChild) {

        if(commentChild.getPageDatas() == null || commentChild.getPageDatas().size() == 0){
            iCommentChildDialogView.p2vShowNoData();
        }else if(commentChild.getPageDatas().size() < PAGE_SIZE) {

            time = commentChild.getPageDatas().get(commentChild.getPageDatas().size()-1).getCommentTime();
            iCommentChildDialogView.p2vGetFirstChildCommentListSuccess(commentChild);
            iCommentChildDialogView.p2vGetLastComment();
        }else {

            time = commentChild.getPageDatas().get(commentChild.getPageDatas().size()-1).getCommentTime();
            iCommentChildDialogView.p2vGetFirstChildCommentListSuccess(commentChild);
            iCommentChildDialogView.p2vNoGetLastComment();
        }


    }

    @Override
    public void m2pGetMoreChildCommentListSuccess(CommentChild commentChild) {

        if(commentChild.getPageDatas() == null || commentChild.getPageDatas().size() == 0){
            iCommentChildDialogView.p2vGetLastComment();
        }else if(commentChild.getPageDatas().size() < PAGE_SIZE) {
            time = commentChild.getPageDatas().get(commentChild.getPageDatas().size() - 1).getCommentTime();
            iCommentChildDialogView.p2vGetMoreChildCommentListSuccess(commentChild);
            iCommentChildDialogView.p2vGetLastComment();
        }else {
            time = commentChild.getPageDatas().get(commentChild.getPageDatas().size() - 1).getCommentTime();
            iCommentChildDialogView.p2vGetMoreChildCommentListSuccess(commentChild);
            iCommentChildDialogView.p2vNoGetLastComment();
        }
    }

    @Override
    public void m2pGetChildCommentListError(String error) {
        iCommentChildDialogView.p2vGetChildCommentListError(error);
    }

    @Override
    public void m2pGetChildCommentListCompleted() {
        iCommentChildDialogView.p2vGetChildCommentListCompleted();
    }


    @Override
    public void v2pPutGiveLike(long commentId, int viewpointType) {
        iCommentChildDialogModel.p2mPutGiveLike(commentId,viewpointType);
    }

    @Override
    public void m2pPutGiveLikeSuccess() {
        iCommentChildDialogView.p2vPutGiveLikeSuccess();
    }

    @Override
    public void m2pPutGiveLikeError(String msg) {
        iCommentChildDialogView.p2vPutGiveLikeError(msg);
    }

    @Override
    public void v2pPutGiveUpLike(long commentId, int viewpointType) {
        iCommentChildDialogModel.p2mPutGiveUpLike(commentId,viewpointType);
    }

    @Override
    public void m2pPutGiveUpLikeSuccess() {
        iCommentChildDialogView.p2vPutGiveUpLikeSuccess();
    }

    @Override
    public void m2pPutGiveUpLikeError(String msg) {
        iCommentChildDialogView.p2vPutGiveUpLikeError(msg);
    }


    @Override
    public void v2pDeleteComment(int commentGrand, long commentId) {
        iCommentChildDialogModel.p2mPutDeleteComment(commentGrand,commentId);
    }

    @Override
    public void m2pDeleteCommentSuccess() {
        iCommentChildDialogView.p2vDeleteCommentSuccess();

    }

    @Override
    public void m2pDeleteCommentFail(String error) {
        iCommentChildDialogView.p2vDeleteCommentFail(error);
    }

}
