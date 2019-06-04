package com.huihu.module_circle.discussdetail.discussdetailinterface;

import com.huihu.module_circle.discussdetail.entity.CommentBean;
import com.huihu.module_circle.discussdetail.entity.DiscussBean;

public interface IDiscussDetailView {
    void p2vGetDiscussInfoSuccess(DiscussBean bean);
    void p2vGetDiscussInfoError(String error);

    void p2vPutGiveLikeSuccess(int type);
    void p2vPutGiveLikeError(String error);

    void p2vGiveFollowsSuccess(int state);
    void p2vGiveFollowsError(String error);

    void p2vShowNoData();
    void p2vGetFirstCommentListSuccess(CommentBean commentBean);
    void p2vGetLastComment();
    void p2vNoGetLastComment();
    void p2vGetMoreCommentListSuccess(CommentBean commentBean);
    void p2vGetCommentListError(String error);
    void p2vGetCommentListCompleted();

}
