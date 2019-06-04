package com.huihu.module_home.commentchilddialog.commentchilddialoginterface;

import com.huihu.module_home.commentchilddialog.entity.CommentChild;
import com.huihu.module_home.commentchilddialog.entity.CommentHead;

public interface ICommentChildDialogView {

    void p2vGetHeadCommentSuccess(CommentHead head);
    void p2vGetHeadCommentError(String error);

    void p2vGetFirstChildCommentListSuccess(CommentChild commentChild);
    void p2vGetMoreChildCommentListSuccess(CommentChild commentChild);
    void p2vGetChildCommentListError(String error);
    void p2vGetChildCommentListCompleted();
    void p2vShowNoData();

    void p2vPutGiveLikeSuccess();
    void p2vPutGiveLikeError(String msg);

    void p2vPutGiveUpLikeSuccess();
    void p2vPutGiveUpLikeError(String msg);

    void p2vDeleteCommentSuccess();
    void p2vDeleteCommentFail(String error);

    void p2vGetLastComment();
    void p2vNoGetLastComment();
}
