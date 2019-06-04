package com.huihu.module_home.commentdialog.commentdialoginterface;

public interface ICommentDialogModel {

    void p2mGetCommentListByIdeaId(long ideaId, int onlyAuth, int orderType, long time, int pageSize);

    void p2mPutGiveLike(long commentId, int viewpointType);
    void p2mPutGiveUpLike(long commentId, int viewpointType);

    void p2mPutDeleteComment(int commentGrand, long commentId);

    void p2mGetAnswerInfo(long ideaId);

    void p2mPutGiveFollows(long aboutId,int state);

}
