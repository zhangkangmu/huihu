package com.huihu.module_notification.comment.commentinterface;

import com.huihu.module_notification.comment.entity.CommentPageBean;

public interface ICommentPresenter {
    void v2pGetFirstData();
    void v2pGetMoreData(CommentPageBean.DataBean lastDataBean);
    void m2pGetOtherCommentListSuccess(CommentPageBean bean, boolean isMore);
    void m2pNetFail();
    void m2pNetComplete();
    void m2pGetDataFail();
}
