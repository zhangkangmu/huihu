package com.huihu.module_notification.commentdetails.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_notification.commentdetails.commentdetailsinterface.ICommentDetailsModel;
import com.huihu.module_notification.commentdetails.commentdetailsinterface.ICommentDetailsPresenter;
import com.huihu.module_notification.commentdetails.entity.CommentChild;
import com.huihu.module_notification.commentdetails.entity.CommentHead;

public class ImpCommentDetailsModel implements ICommentDetailsModel {
    private static final String TAG = "ImpCommentDetailsModel";

    private final ICommentDetailsPresenter iCommentDetailsPresenter;

    public ImpCommentDetailsModel(ICommentDetailsPresenter iCommentDetailsPresenter) {
        this.iCommentDetailsPresenter = iCommentDetailsPresenter;
    }


    @Override
    public void p2mGetChildCommentList(long commentId, final long time, int pageSize){
        long uid = SPUtils.getInstance().getCurrentUid();
        getChildCommentList(commentId, time, 0, pageSize, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, returnModel.getBodyMessage());
                CommentChild commentChild = new Gson().fromJson(returnModel.getBodyMessage(), CommentChild.class);
                if(time == 0){
                    iCommentDetailsPresenter.m2pGetFirstChildCommentListSuccess(commentChild);
                }else {
                    iCommentDetailsPresenter.m2pGetMoreChildCommentListSuccess(commentChild);
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentDetailsPresenter.m2pGetChildCommentListError(strErrMsg);
            }

            @Override
            public void onCompleted() {
                iCommentDetailsPresenter.m2pGetChildCommentListCompleted();
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
                iCommentDetailsPresenter.m2pPutGiveLikeSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentDetailsPresenter.m2pPutGiveLikeError(strErrMsg);
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
                iCommentDetailsPresenter.m2pPutGiveUpLikeSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentDetailsPresenter.m2pPutGiveUpLikeError(strErrMsg);
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
                iCommentDetailsPresenter.m2pDeleteCommentSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentDetailsPresenter.m2pDeleteCommentFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });

    }

    @Override
    public void p2mGetHeadComment(long commentId){
        long uid = SPUtils.getInstance().getCurrentUid();
        getIdeasByCommentId(commentId,uid ,new HuihuCallBack(){
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, returnModel.getBodyMessage());
                CommentHead head = new Gson().fromJson(returnModel.getBodyMessage(),CommentHead.class);
                iCommentDetailsPresenter.m2pGetHeadCommentSuccess(head);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentDetailsPresenter.m2pGetHeadCommentError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }


    //api/getIdeasByCommentId
    public void getIdeasByCommentId(long commentId,long uid ,HuihuCallBack callBack){
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Comments.GetIdeasByCommentId, NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("commentId",  commentId + "");
        if(uid > 0){
            param.addQuery("uid",  uid + "");
        }
        HuihuHttpUtils.httpRequest(param, callBack);
    }


    public void getChildCommentList(long commentId, long commentTime, int pageIndex, int pageSize, long uid, HuihuCallBack callBack){

        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Comments.ChildCommentList, NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("commentId",  commentId + "");
        param.addQuery("commentTime",  commentTime  + "");
        if (pageIndex > 0){
            param.addQuery("pageIndex",  pageIndex  + "");
        }
        param.addQuery("pageSize",  pageSize + "");
        if(uid > 0){
            param.addQuery("uid",  uid + "");
        }
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

}