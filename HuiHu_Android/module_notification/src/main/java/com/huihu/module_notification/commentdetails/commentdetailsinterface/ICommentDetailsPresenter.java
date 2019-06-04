package com.huihu.module_notification.commentdetails.commentdetailsinterface;

import com.huihu.module_notification.commentdetails.entity.CommentChild;
import com.huihu.module_notification.commentdetails.entity.CommentHead;

public interface ICommentDetailsPresenter {
    void v2pGetHeadComment(long commentId);
    void m2pGetHeadCommentSuccess(CommentHead head);
    void m2pGetHeadCommentError(String error);

    void v2pGetFirstChildCommentList(long commentId);
    void v2pGetMoreChildCommentList(long commentId);
    void m2pGetFirstChildCommentListSuccess(CommentChild commentChild);
    void m2pGetMoreChildCommentListSuccess(CommentChild commentChild);
    void m2pGetChildCommentListError(String error);
    void m2pGetChildCommentListCompleted();

    void v2pPutGiveLike(long commentId,int viewpointType);
    void m2pPutGiveLikeSuccess();
    void m2pPutGiveLikeError(String msg);

    void v2pPutGiveUpLike(long commentId, int viewpointType);
    void m2pPutGiveUpLikeSuccess();
    void m2pPutGiveUpLikeError(String msg);

    void v2pDeleteComment(int commentGrand, long commentId);
    void m2pDeleteCommentSuccess();
    void m2pDeleteCommentFail(String error);

}
