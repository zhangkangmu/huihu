package com.huihu.module_home.commentdialog.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_home.commentdialog.entity.AnswerInfo;
import com.huihu.module_home.commentdialog.commentdialoginterface.ICommentDialogModel;
import com.huihu.module_home.commentdialog.commentdialoginterface.ICommentDialogPresenter;
import com.huihu.module_home.commentdialog.entity.CommentBean;
import com.huihu.module_home.commentdialog.entity.UserBean;

public class ImpCommentDialogModel implements ICommentDialogModel {
    private static final String TAG = "ImpCommentDialogModel";
    private final ICommentDialogPresenter iCommentDialogPresenter;
    private static final int CHILD_SIZE = 2;
    private static final int ORDER_LIKE = 2;


    public ImpCommentDialogModel(ICommentDialogPresenter iCommentDialogPresenter) {
        this.iCommentDialogPresenter = iCommentDialogPresenter;
    }

    @Override
    public void p2mGetCommentListByIdeaId(long ideaId, int onlyAuth, int orderType, final long time, int pageSize) {
        long uid = SPUtils.getInstance().getCurrentUid();
        getCommentListByIdeaId(CHILD_SIZE, time, ideaId, onlyAuth, orderType, 0, pageSize, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, returnModel.getBodyMessage());
                CommentBean commentBean = new Gson().fromJson(returnModel.getBodyMessage(), CommentBean.class);
                if(time == 0){
                    iCommentDialogPresenter.m2pGetFirstCommentListSuccess(commentBean);
                }else{
                    iCommentDialogPresenter.m2pGetMoreCommentListSuccess(commentBean);
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentDialogPresenter.m2pGetCommentListError(strErrMsg);
            }

            @Override
            public void onCompleted() {
                iCommentDialogPresenter.m2pGetCommentListCompleted();
            }
        });

    }


    /**
     * 点赞评论
     * @param commentId
     */
    @Override
    public void p2mPutGiveLike(long commentId, int viewpointType) {
        long uid = SPUtils.getInstance().getCurrentUid();
        putGiveViewpoint(commentId, uid, 1, viewpointType, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iCommentDialogPresenter.m2pPutGiveLikeSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentDialogPresenter.m2pPutGiveLikeError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    /**
     * 取消评论点赞
     * @param commentId
     */
    @Override
    public void p2mPutGiveUpLike(long commentId, int viewpointType) {
        long uid = SPUtils.getInstance().getCurrentUid();
        putGiveViewpoint(commentId, uid, 0, viewpointType, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iCommentDialogPresenter.m2pPutGiveUpLikeSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentDialogPresenter.m2pPutGiveUpLikeError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    /**
     *删除评论
     * @param commentGrand  评论等级：一级评论 = 1 ， 二级评论或回复 = 2
     * @param commentId
     */
    @Override
    public void p2mPutDeleteComment(int commentGrand, long commentId){
        long uid =  SPUtils.getInstance().getCurrentUid();
        Log.d(TAG,"p2mPutDeleteComment " + commentGrand + " " + commentId + " " + uid);
        putDeleteComment(commentGrand, commentId, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iCommentDialogPresenter.m2pDeleteCommentSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentDialogPresenter.m2pDeleteCommentFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });

    }

    //获取评论
    public void getCommentListByIdeaId(int childSize, long commentTime, long ideaId, int onlyAuth, int orderType, int pageIndex, int pageSize, long uid, HuihuCallBack callBack) {
        Log.d(TAG, "ideaId " + ideaId + " commentTime " + commentTime + " childSize " + childSize + " onlyAuth " + onlyAuth + " pageIndex " + pageIndex + " orderType " + orderType);
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Comments.CommentListByIdeaId, NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("childSize", childSize + "");
        param.addQuery("commentTime", commentTime + "");
        param.addQuery("ideaId", ideaId + "");
        param.addQuery("onlyAuth", onlyAuth + "");
        param.addQuery("orderType", orderType + "");
        param.addQuery("pageIndex", pageIndex + "");
        if (pageSize > 0) {
            param.addQuery("pageSize", pageSize + "");
        }
        if (uid > 0) {
            param.addQuery("uid", uid + "");
        }
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    //获取问题信息
    @Override
    public void p2mGetAnswerInfo(long ideaId){
        long uid =  SPUtils.getInstance().getCurrentUid();
        getAnwserInfo(ideaId, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, "p2mGetAnswerInfo " + returnModel.getBodyMessage());
                AnswerInfo answerInfo = new Gson().fromJson(returnModel.getBodyMessage(), AnswerInfo.class);
                iCommentDialogPresenter.m2pGetAnswerInfoSuccess(answerInfo);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentDialogPresenter.m2pGetAnswerInfoFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });

    }

    /**
     * 关注/取消某人
     * @param aboutId
     * @param state  0：取消 1：关注
     */
    @Override
    public void p2mPutGiveFollows(long aboutId,int state){
        long uid = SPUtils.getInstance().getCurrentUid();
        putGiveFollows(aboutId, 1, state, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iCommentDialogPresenter.m2pGiveFollowsSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentDialogPresenter.m2pGiveFollowsError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    //关注
    public void putGiveFollows(long aboutId, int followType, int state, long uid, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Follow.PutGiveFollows, NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("aboutId", aboutId + "");
        param.addQuery("followType", followType + "");
        param.addQuery("state", state + "");
        param.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }


    /**
     * @param commentId     评论id
     * @param uid           点赞人id
     * @param viewpoint     是否点赞 0：取消 1：赞同 2：反对
     * @param viewpointType 1：内容 2：评论 3：二级评论
     * @param callBack
     */
    public void putGiveViewpoint(long commentId, long uid, int viewpoint, int viewpointType, HuihuCallBack callBack) {
        Log.d(TAG, "commentId " + commentId + "uid " + uid + "viewpoint " + viewpoint + " viewpointType " + viewpointType);
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.ViewPoint.PutGiveViewpoint, NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("commentId", commentId + "");
        param.addQuery("uid", uid + "");
        param.addQuery("viewpoint", viewpoint + "");
        param.addQuery("viewpointType", viewpointType + "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    //删除评论
    public void putDeleteComment(int commentGrand, long commentId, long uid, HuihuCallBack callBack){
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Comments.PutDeleteComment, NetworkTransmissionDefine.HttpMethod.DELETE);
        param.addQuery("commentGrand", commentGrand + "");
        param.addQuery("commentId", commentId + "");
        param.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    //获取回答信息
    public void getAnwserInfo(long ideaId , long uid, HuihuCallBack callBack){
        Log.d(TAG, "getAnwserInfo ideaId " + ideaId + " uid " + uid);
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Ideas.GetAnswerInfo, NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("ideaId", ideaId + "");
        param.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
