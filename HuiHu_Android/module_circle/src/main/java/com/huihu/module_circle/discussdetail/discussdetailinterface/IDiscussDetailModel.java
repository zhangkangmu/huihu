package com.huihu.module_circle.discussdetail.discussdetailinterface;

public interface IDiscussDetailModel {

    void p2mGetDiscussInfo(long ideaId);
    void p2mPutGiveLike(long id, int viewPoint, int viewpointType);
    void p2mPutGiveFollows(long aboutId, int followType, int state);


    void p2mGetCommentListByIdeaId(long ideaId, int onlyAuth, int orderType, final long time, int pageSize);
    void p2mPutDeleteComment(int commentGrand, long commentId);

}
