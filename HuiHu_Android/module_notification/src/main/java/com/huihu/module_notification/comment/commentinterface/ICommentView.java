package com.huihu.module_notification.comment.commentinterface;

import com.huihu.module_notification.comment.entity.CommentPageBean;

import java.util.List;

public interface ICommentView {
    void p2vShowFirstData(List<CommentPageBean.DataBean> dataBeans);
    void p2vShowMoreData(List<CommentPageBean.DataBean> dataBeans);
    void p2vShowNoData();
    void p2vGetDataEnd();
    void p2vShowGetDataFail();
}
