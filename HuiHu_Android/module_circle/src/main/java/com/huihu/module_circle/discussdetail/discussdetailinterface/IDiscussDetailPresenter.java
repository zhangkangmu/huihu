package com.huihu.module_circle.discussdetail.discussdetailinterface;

import com.huihu.module_circle.discussdetail.entity.CommentBean;
import com.huihu.module_circle.discussdetail.entity.DiscussBean;

public interface IDiscussDetailPresenter {

    void v2pGetDiscussInfo(long ideaId);
    void m2pGetDiscussInfoSuccess(DiscussBean bean);
    void m2pGetDiscussInfoError(String error);

    void v2pPutGiveLike(long id, int viewPoint, int viewPointType);
    void m2pPutGiveLikeSuccess(int viewPoint);
    void m2pPutGiveLikeError(String error);

    void p2vGiveFollows(long id, int state);
    void m2pGiveFollowsSuccess(int state);
    void m2pGiveFollowsError(String error);

    void v2pGetFirstCommentList(long ideaId, int onlyAuth, int orderType);
    void m2pGetFirstCommentListSuccess(CommentBean bean);
    void m2pGetMoreCommentListSuccess(CommentBean bean);
    void v2pGetMoreCommentList(long ideaId, int onlyAuth, int orderType);
    void m2pGetCommentListError(String error);
    void m2pGetCommentListCompleted();

    void v2pDeleteComment(int commentGrand, long commentId);
    void m2pDeleteCommentSuccess();
    void m2pDeleteCommentError(String error);
}
