package com.huihu.module_mine.comment.commentinterface;

import com.huihu.module_mine.comment.entity.CommentInfo;

public interface ICommentModel {
    void p2mGetCommentList(long time ,long fid,boolean isMore);
    void p2mDeleteComment(CommentInfo.PageDatasBean bean, int position,long fid);
}
