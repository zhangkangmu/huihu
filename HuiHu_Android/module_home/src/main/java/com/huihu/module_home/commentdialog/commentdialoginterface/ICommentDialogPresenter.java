package com.huihu.module_home.commentdialog.commentdialoginterface;

import com.huihu.module_home.commentdialog.entity.AnswerInfo;
import com.huihu.module_home.commentdialog.entity.CommentBean;
import com.huihu.module_home.commentdialog.entity.UserBean;

public interface ICommentDialogPresenter {

    void v2pPutGiveLike(long commentId,int viewpointType);
    void m2pPutGiveLikeSuccess();
    void m2pPutGiveLikeError(String msg);

    void v2pPutGiveUpLike(long commentId, int viewpointType);
    void m2pPutGiveUpLikeSuccess();
    void m2pPutGiveUpLikeError(String msg);

    void v2pDeleteComment(int commentGrand, long commentId);
    void m2pDeleteCommentSuccess();
    void m2pDeleteCommentFail(String error);

    void v2pGetAnswerInfo(long ideaId);
    void m2pGetAnswerInfoSuccess(AnswerInfo answerInfo);
    void m2pGetAnswerInfoFail(String  error);

    void v2pPutGiveFollows(long aboutId,int state);
    void m2pGiveFollowsSuccess();
    void m2pGiveFollowsError(String error);

    void v2pGetFirstCommentList(long ideaId,int onlyAuth,int orderType);
    void v2pGetMoreCommentList(long ideaId,int onlyAuth,int orderType);
    void m2pGetFirstCommentListSuccess(CommentBean commentBean);
    void m2pGetMoreCommentListSuccess(CommentBean commentBean);
    void m2pGetCommentListError(String error);
    void m2pGetCommentListCompleted();

}
