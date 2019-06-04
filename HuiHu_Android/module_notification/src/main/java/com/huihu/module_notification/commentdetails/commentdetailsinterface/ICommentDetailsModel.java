package com.huihu.module_notification.commentdetails.commentdetailsinterface;

public interface ICommentDetailsModel {
    void p2mGetHeadComment(long commentId);
    void p2mGetChildCommentList(long commentId,long time,int pageSize);

    void p2mPutGiveLike(long commentId, int viewpointType);
    void p2mPutGiveUpLike(long commentId, int viewpointType);
    void p2mPutDeleteComment(int commentGrand, long commentId);

}
