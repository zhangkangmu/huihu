package com.huihu.module_circle.discussdetail.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.discussdetail.discussdetailinterface.IDiscussDetailModel;
import com.huihu.module_circle.discussdetail.discussdetailinterface.IDiscussDetailPresenter;
import com.huihu.module_circle.discussdetail.entity.CommentBean;
import com.huihu.module_circle.discussdetail.entity.DiscussBean;

public class ImpDiscussDetailModel implements IDiscussDetailModel {
    private static final String TAG = "ImpDiscussDetailModel";
    private final IDiscussDetailPresenter iDiscussDetailPresenter;

    private static final int CHILD_SIZE = 2;

    public ImpDiscussDetailModel(IDiscussDetailPresenter iDiscussDetailPresenter) {
        this.iDiscussDetailPresenter = iDiscussDetailPresenter;
    }


    public void p2mGetDiscussInfo(long ideaId){
        long uid = SPUtils.getInstance().getCurrentUid();
        String deviceId = SPUtils.getInstance().getDeviceId();
        getDiscussInfo(ideaId, uid, deviceId,new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, "p2mGetDiscussInfo " + returnModel.getBodyMessage());
                DiscussBean bean = new Gson().fromJson(returnModel.getBodyMessage(),DiscussBean.class);
                iDiscussDetailPresenter.m2pGetDiscussInfoSuccess(bean);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iDiscussDetailPresenter.m2pGetDiscussInfoError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPutGiveLike(long commentId, final int viewPoint, int viewpointType) {
        long uid = SPUtils.getInstance().getCurrentUid();
        putGiveViewpoint(commentId, uid, viewPoint, viewpointType, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iDiscussDetailPresenter.m2pPutGiveLikeSuccess(viewPoint);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iDiscussDetailPresenter.m2pPutGiveLikeError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }


    //获取讨论信息
    public void getDiscussInfo(long ideaId, long uid,String deviceId, HuihuCallBack callBack){
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Circle.GetDiscussInfo, NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("ideaId", ideaId + "");
        param.addQuery("uid", uid + "");
        param.addQuery("deviceId", deviceId + "");
        HuihuHttpUtils.httpRequest(param,callBack);
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


    /**
     * 关注/取消某人
     * @param aboutId
     * @param state  0：取消 1：关注
     */
    @Override
    public void p2mPutGiveFollows(long aboutId, int followType, final int state){
        long uid = SPUtils.getInstance().getCurrentUid();
        putGiveFollows(aboutId, followType, state, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iDiscussDetailPresenter.m2pGiveFollowsSuccess(state);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iDiscussDetailPresenter.m2pGiveFollowsError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    /**
     *
     * @param aboutId
     * @param followType  1: 人 2：内容 3：分类
     * @param state   关注或取消 0：取消 1：关注
     * @param uid
     * @param callBack
     */
    public void putGiveFollows(long aboutId, int followType, int state, long uid, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Follow.PutGiveFollows, NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("aboutId", aboutId + "");
        param.addQuery("followType", followType + "");
        param.addQuery("state", state + "");
        param.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(param, callBack);
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
                    iDiscussDetailPresenter.m2pGetFirstCommentListSuccess(commentBean);
                }else{
                    iDiscussDetailPresenter.m2pGetMoreCommentListSuccess(commentBean);
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iDiscussDetailPresenter.m2pGetCommentListError(strErrMsg);
            }

            @Override
            public void onCompleted() {
                iDiscussDetailPresenter.m2pGetCommentListCompleted();
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
                iDiscussDetailPresenter.m2pDeleteCommentSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iDiscussDetailPresenter.m2pDeleteCommentError(strErrMsg);
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

    //删除评论
    public void putDeleteComment(int commentGrand, long commentId, long uid, HuihuCallBack callBack){
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Comments.PutDeleteComment, NetworkTransmissionDefine.HttpMethod.DELETE);
        param.addQuery("commentGrand", commentGrand + "");
        param.addQuery("commentId", commentId + "");
        param.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }


}
