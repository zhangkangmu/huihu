package com.huihu.module_mine.comment.presenter;

import com.huihu.module_mine.comment.commentinterface.ICommentModel;
import com.huihu.module_mine.comment.commentinterface.ICommentPresenter;
import com.huihu.module_mine.comment.commentinterface.ICommentView;
import com.huihu.module_mine.comment.entity.CommentInfo;
import com.huihu.module_mine.comment.model.ImpCommentModel;
import com.huihu.module_mine.community.entity.CommunityInfo;

import java.util.List;

public class ImpCommentPresenter implements ICommentPresenter {
    private final ICommentModel iCommentModel = new ImpCommentModel(this);
    private final ICommentView iCommentView;

    public ImpCommentPresenter(ICommentView iCommentView) {
        this.iCommentView = iCommentView;
    }

    @Override
    public void v2pGetCommentList(long fid) {
        iCommentModel.p2mGetCommentList(0,fid,false);
    }

    @Override
    public void v2pGetMoreCommentList(CommentInfo.PageDatasBean bean,long fid) {
        iCommentModel.p2mGetCommentList(bean.getCommentTime(),fid,true);
    }

    @Override
    public void m2pReturnCommentList(List<CommentInfo.PageDatasBean> mlist,boolean isMore) {
        if (mlist!=null&&mlist.size()>0){
            if (isMore){
                iCommentView.p2vGetMoreCommentListSuccess(mlist);
            }else {
                iCommentView.p2vGetCommentListSuccess(mlist);
            }
        }else {
            if (!isMore){
                iCommentView.p2vShowNoData();
            }
        }


    }

    @Override
    public void m2pNetFail() {


    }

    @Override
    public void m2pNetComplete() {
        iCommentView.p2vGetDataEnd();
    }

    @Override
    public void v2pDeleteComment(CommentInfo.PageDatasBean bean, int position,long fid) {
       iCommentModel.p2mDeleteComment(bean,position,fid);
    }

    @Override
    public void m2pDeleteCommentSuccess(int position) {
        iCommentView.p2vDeleteCommentSuccess(position);
    }

    @Override
    public void m2pDeleteCommentWithError(String subCode) {
        iCommentView.p2vDeleteCommentWithError(subCode);
    }
}
