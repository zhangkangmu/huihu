package com.huihu.module_home.commentdialog.commentdialoginterface;


import com.huihu.module_home.commentdialog.entity.AnswerInfo;
import com.huihu.module_home.commentdialog.entity.CommentBean;
import com.huihu.module_home.commentdialog.entity.UserBean;

public interface ICommentDialogView {

    void p2vPutGiveLikeSuccess();
    void p2vPutGiveLikeError(String msg);

    void p2vPutGiveUpLikeSuccess();
    void p2vPutGiveUpLikeError(String msg);

    void p2vDeleteCommentSuccess();
    void p2vDeleteCommentFail(String error);

    void p2vGetAnswerInfoSuccess(AnswerInfo answerInfo);
    void p2vGetAnswerInfoFail(String error);

    void p2vPutGiveFollowsSuccess();
    void p2vPutGiveFollowsError(String error);

    void p2vGetFirstCommentListSuccess(CommentBean commentBean);
    void p2vGetMoreCommentListSuccess(CommentBean commentBean);
    void p2vGetCommentListError(String error);
    void p2vGetCommentListCompleted();
    void p2vGetLastComment();
    void p2vNoGetLastComment();
    void p2vShowNoData();
}
