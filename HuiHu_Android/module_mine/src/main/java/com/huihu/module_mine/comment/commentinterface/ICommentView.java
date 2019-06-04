package com.huihu.module_mine.comment.commentinterface;

import com.huihu.module_mine.comment.entity.CommentInfo;

import java.util.List;

public interface ICommentView {
void p2vGetCommentListSuccess(List<CommentInfo.PageDatasBean> msg);
void p2vGetMoreCommentListSuccess(List<CommentInfo.PageDatasBean> msg);
void p2vGetCommentListFailed();
void p2vShowNoData();
void p2vGetDataEnd();
void p2vDeleteCommentSuccess(int position);
void p2vDeleteCommentWithError(String subCode);

}
