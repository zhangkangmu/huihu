package com.huihu.module_home.answerdetail.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_home.answerdetail.answerdetailinterface.IAnswerDetailModel;
import com.huihu.module_home.answerdetail.answerdetailinterface.IAnswerDetailPresenter;
import com.huihu.module_home.answerdetail.entity.AnswerInfo;
import com.huihu.module_home.answerdetail.entity.GetAnswerDetailSubCode;
import com.huihu.module_home.answerdetail.entity.PutGiveViewpointSubCode;
import com.huihu.module_home.popularIdea.enity.CancelFollowSubCode;
import com.huihu.module_home.popularIdea.enity.PutGiveFollowsSubCode;

public class ImpAnswerDetailModel implements IAnswerDetailModel {
    private final IAnswerDetailPresenter iAnswerDetailPresenter;

    public ImpAnswerDetailModel(IAnswerDetailPresenter iAnswerDetailPresenter) {
        this.iAnswerDetailPresenter = iAnswerDetailPresenter;
    }

    @Override
    public void p2mGetAnswerInfo(long ideaId,long uid) {
        getAnswerInfo(ideaId,uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetAnswerDetailSubCode
                            .Success:
                        AnswerInfo answerInfo = new Gson().fromJson(returnModel.getBodyMessage(), AnswerInfo.class);
                        iAnswerDetailPresenter.m2pReturnAnswerInfo(answerInfo);
                        break;
                    case GetAnswerDetailSubCode.BusinessError:
                    case GetAnswerDetailSubCode.ParameterError:
                        default:
                            break;
                }

            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iAnswerDetailPresenter.m2pNetFail();

            }

            @Override
            public void onCompleted() {
                iAnswerDetailPresenter.m2pNetComplete();
            }
        });

    }

    @Override
    public void p2mPutGiveFollows(final AnswerInfo bean) {
        putGiveFollows(bean,new HuihuCallBack(){
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutGiveFollowsSubCode
                            .Success:
                        if (bean.getUserInfo().getFollow()!=0) {
                            bean.getUserInfo().setFollow(0);
                        }else {
                            bean.getUserInfo().setFollow(1);
                        }
                        iAnswerDetailPresenter.m2pPutGiveFollows();
                        break;
                    case PutGiveFollowsSubCode.UnLogin:
                    case CancelFollowSubCode.ParameterError:
                    case CancelFollowSubCode.BusinessError:
                    default:
                        iAnswerDetailPresenter.m2pPutGiveFollowsError(returnModel.getSubCode());
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }

    //作出观点
    @Override
    public void p2mPutGiveViewpoint(long commentId, long uid, final int viewPoint, int viewPointType) {
            putGiveViewpoint(commentId, uid, viewPoint, viewPointType, new HuihuCallBack() {
                @Override
                public void onSuccess(ReturnModel returnModel) {
                    switch (returnModel.getSubCode()){
                        case PutGiveViewpointSubCode
                                .Success:
                            iAnswerDetailPresenter.m2pPutGiveViewpoint(viewPoint);
                            break;
                        case   PutGiveViewpointSubCode.BusinessError:
                        case   PutGiveViewpointSubCode.ParameterError:
                        case   PutGiveViewpointSubCode.UnLogin:
                            default:
                                iAnswerDetailPresenter.m2pPutGiveViewpointError(returnModel.getSubCode());
                                break;
                    }
                }

                @Override
                public void onError(int errCode, String strErrMsg) {

                }

                @Override
                public void onCompleted() {

                }
            });
    }

    @Override
    public void p2mConstrolCollection(long ideaId, long uid, final boolean isAddCollection) {
        controlCollection(ideaId,uid,isAddCollection,new HuihuCallBack(){
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iAnswerDetailPresenter.m2pChangeCollection(isAddCollection);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }


    HttpRequestParam httpRequestParam;
    private void controlCollection(long ideaId,long uid,boolean isAddCollecton,HuihuCallBack mCallBack) {
        if (isAddCollecton){
            httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Collection.postCollection, NetworkTransmissionDefine.HttpMethod.POST);
        }else {
            httpRequestParam=new HttpRequestParam(CurLocales.instance().API.Collection.deleteCollection, NetworkTransmissionDefine.HttpMethod.DELETE);
        }
        httpRequestParam.addQuery("ideaId", String.valueOf(ideaId));
        httpRequestParam.addQuery("uid", String.valueOf(uid));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

    private void getAnswerInfo(long ideaId,long uid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.GetAnswerInfo, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("ideaId", String.valueOf(ideaId));
        httpRequestParam.addQuery("uid", String.valueOf(uid));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
    private void putGiveFollows( AnswerInfo bean,HuihuCallBack mCallBack){
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Follow.PutGiveFollows, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("aboutId", String.valueOf(bean.getUserInfo().getUid()));
        httpRequestParam.addQuery("followType", "1");
        if (bean.getUserInfo().getFollow()!=0) {
            httpRequestParam.addQuery("state", "0");
        }else {
            httpRequestParam.addQuery("state","1");
        }
        httpRequestParam.addQuery("uid",  ""+SPUtils.getInstance().getCurrentUid());
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }


    private void putGiveViewpoint( long commentId, long uid, int viewPoint, int viewPointType,HuihuCallBack mCallBack){
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.ViewPoint.PutGiveViewpoint, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("commentId", String.valueOf(commentId));
        httpRequestParam.addQuery("uid", String.valueOf(uid));
        //0取消1赞同2反对
        httpRequestParam.addQuery("viewpoint",  String.valueOf(viewPoint));
        httpRequestParam.addQuery("viewpointType",  String.valueOf(viewPointType));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
}
