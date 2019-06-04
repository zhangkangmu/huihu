package com.huihu.module_mine.comment.commentinterface;

import com.huihu.module_mine.comment.entity.CommentInfo;
import com.huihu.module_mine.community.entity.CommunityInfo;

import java.util.List;

public interface ICommentPresenter {
    void v2pGetCommentList(long fid);
    void v2pGetMoreCommentList(CommentInfo.PageDatasBean bean,long fid);
    void m2pReturnCommentList(List<CommentInfo.PageDatasBean> mlist,boolean isMore);
    void m2pNetFail();
    void m2pNetComplete();
    void v2pDeleteComment(CommentInfo.PageDatasBean bean,int position,long fid);
    void m2pDeleteCommentSuccess(int position);
    void m2pDeleteCommentWithError(String subCode);
}
