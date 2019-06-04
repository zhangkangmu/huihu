package com.huihu.module_circle.discussdetail.presenter;

import com.huihu.module_circle.discussdetail.discussdetailinterface.IDiscussDetailModel;
import com.huihu.module_circle.discussdetail.discussdetailinterface.IDiscussDetailPresenter;
import com.huihu.module_circle.discussdetail.discussdetailinterface.IDiscussDetailView;
import com.huihu.module_circle.discussdetail.entity.CommentBean;
import com.huihu.module_circle.discussdetail.entity.DiscussBean;
import com.huihu.module_circle.discussdetail.model.ImpDiscussDetailModel;

public class ImpDiscussDetailPresenter implements IDiscussDetailPresenter {
    private final IDiscussDetailModel iDiscussDetailModel = new ImpDiscussDetailModel(this);
    private final IDiscussDetailView iDiscussDetailView;

    //点赞类型：内容
    private static final int VIEW_POINT_TYPE = 1;
    //关注类型：人
    private static final int FOLLOW_TYPE = 1;

    private int mOederType;
    private static final int PAGE_SIZE = 10;
    private long time;

    public ImpDiscussDetailPresenter(IDiscussDetailView iDiscussDetailView) {
        this.iDiscussDetailView = iDiscussDetailView;
    }

    @Override
    public void v2pGetDiscussInfo(long ideaId) {
        iDiscussDetailModel.p2mGetDiscussInfo(ideaId);
    }

    @Override
    public void m2pGetDiscussInfoSuccess(DiscussBean bean) {
        iDiscussDetailView.p2vGetDiscussInfoSuccess(bean);
    }

    @Override
    public void m2pGetDiscussInfoError(String error) {
        iDiscussDetailView.p2vGetDiscussInfoError(error);
    }

    @Override
    public void v2pPutGiveLike(long id, int viewPoint,int viewPointType) {
        iDiscussDetailModel.p2mPutGiveLike(id,viewPoint,VIEW_POINT_TYPE);
    }

    @Override
    public void m2pPutGiveLikeSuccess(int viewPoint) {
        iDiscussDetailView.p2vPutGiveLikeSuccess(viewPoint);
    }

    @Override
    public void m2pPutGiveLikeError(String error) {
        iDiscussDetailView.p2vPutGiveLikeError(error);
    }

    @Override
    public void p2vGiveFollows(long id,  int state) {
        iDiscussDetailModel.p2mPutGiveFollows(id,FOLLOW_TYPE,state);
    }

    @Override
    public void m2pGiveFollowsSuccess(int state) {
        iDiscussDetailView.p2vGiveFollowsSuccess(state);
    }

    @Override
    public void m2pGiveFollowsError(String error) {
        iDiscussDetailView.p2vGiveFollowsError(error);
    }


    @Override
    public void v2pGetFirstCommentList(long ideaId,int onlyAuth,int orderType) {
        time = 0;
        mOederType = orderType;
        iDiscussDetailModel.p2mGetCommentListByIdeaId(ideaId,onlyAuth,orderType,time,PAGE_SIZE);
    }

    @Override
    public void v2pGetMoreCommentList(long ideaId,int onlyAuth,int orderType) {
        mOederType = orderType;
        iDiscussDetailModel.p2mGetCommentListByIdeaId(ideaId,onlyAuth,orderType,time,PAGE_SIZE);
    }

    @Override
    public void m2pGetFirstCommentListSuccess(CommentBean commentBean) {
        if(commentBean.getPageDatas() == null || commentBean.getPageDatas().size() == 0){
            iDiscussDetailView.p2vShowNoData();
        }else if(commentBean.getPageDatas().size() < PAGE_SIZE) {

            time = commentBean.getPageDatas().get(commentBean.getPageDatas().size() - 1).getCommentTime();
            iDiscussDetailView.p2vGetFirstCommentListSuccess(commentBean);
            iDiscussDetailView.p2vGetLastComment();
        }else {

            time = commentBean.getPageDatas().get(commentBean.getPageDatas().size() - 1).getCommentTime();
            iDiscussDetailView.p2vGetFirstCommentListSuccess(commentBean);
            iDiscussDetailView.p2vNoGetLastComment();
        }
    }

    @Override
    public void m2pGetMoreCommentListSuccess(CommentBean commentBean) {
        if(commentBean.getPageDatas() == null || commentBean.getPageDatas().size() == 0){
            //TODO 已无数据
            iDiscussDetailView.p2vGetLastComment();
        }else if (commentBean.getPageDatas().size() < PAGE_SIZE){

            time = commentBean.getPageDatas().get(commentBean.getPageDatas().size()-1).getCommentTime() ;
            iDiscussDetailView.p2vGetMoreCommentListSuccess(commentBean);
            iDiscussDetailView.p2vGetLastComment();
        }else {

            time = commentBean.getPageDatas().get(commentBean.getPageDatas().size()-1).getCommentTime() ;
            iDiscussDetailView.p2vGetMoreCommentListSuccess(commentBean);
            iDiscussDetailView.p2vNoGetLastComment();
        }
    }

    @Override
    public void m2pGetCommentListError(String error) {
        iDiscussDetailView.p2vGetCommentListError(error);
    }

    @Override
    public void m2pGetCommentListCompleted() {
        iDiscussDetailView.p2vGetCommentListCompleted();
    }


    @Override
    public void v2pDeleteComment(int commentGrand, long commentId) {
        iDiscussDetailModel.p2mPutDeleteComment(commentGrand,commentId);
    }

    @Override
    public void m2pDeleteCommentSuccess() {

    }

    @Override
    public void m2pDeleteCommentError(String error) {

    }
}
