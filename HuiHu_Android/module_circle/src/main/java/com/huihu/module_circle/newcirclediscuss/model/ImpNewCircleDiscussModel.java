package com.huihu.module_circle.newcirclediscuss.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.newcirclediscuss.entity.CancelFollowSubCode;
import com.huihu.module_circle.newcirclediscuss.entity.GetNewCircleDiscussListSubcode;
import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;
import com.huihu.module_circle.newcirclediscuss.entity.PutGiveFollowsSubCode;
import com.huihu.module_circle.newcirclediscuss.newcirclediscussinterface.INewCircleDiscussModel;
import com.huihu.module_circle.newcirclediscuss.newcirclediscussinterface.INewCircleDiscussPresenter;

import java.util.List;

public class ImpNewCircleDiscussModel implements INewCircleDiscussModel {
    private final INewCircleDiscussPresenter iNewCircleDiscussPresenter;

    public ImpNewCircleDiscussModel(INewCircleDiscussPresenter iNewCircleDiscussPresenter) {
        this.iNewCircleDiscussPresenter = iNewCircleDiscussPresenter;
    }

    @Override
    public void m2pRequestCircleDiscuss(long circleId, long lastTime, int pageSize, long uid, final boolean isMore) {
        getNewCircleDiscussList(circleId,lastTime,pageSize,uid,new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetNewCircleDiscussListSubcode.success:
                        NewCircleDiscussInfo info = new Gson().fromJson(returnModel.getBodyMessage(), NewCircleDiscussInfo.class);
                        List<NewCircleDiscussInfo.PageDatasBean> datas = info.getPageDatas();
                        iNewCircleDiscussPresenter.m2pReturnCircleDiscussList(datas,isMore);
                        break;
                    case GetNewCircleDiscussListSubcode.ParameterError:
                    case GetNewCircleDiscussListSubcode.BusinessError:
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iNewCircleDiscussPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iNewCircleDiscussPresenter.m2pNetComplete();
            }
        });
    }

    @Override
    public void p2mPutGiveFollows(final NewCircleDiscussInfo.PageDatasBean.UserInfoBean userInfo) {
        getFollow(userInfo,new HuihuCallBack(){

            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutGiveFollowsSubCode
                            .Success:
                        if (userInfo.getFollow()!=0) {
                            userInfo.setFollow(0);
                        }else {
                            userInfo.setFollow(1);
                        }
                        iNewCircleDiscussPresenter.m2pReturnSuccesAttention();
                        break;
                    case PutGiveFollowsSubCode.UnLogin:
                    case CancelFollowSubCode.ParameterError:
                    case CancelFollowSubCode.BusinessError:
                    default:
                        iNewCircleDiscussPresenter.m2pPutGiveFollowsError(returnModel.getSubCode());
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
    //点击关注
    private void getFollow(NewCircleDiscussInfo.PageDatasBean.UserInfoBean userInfo, HuihuCallBack huihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Follow.PutGiveFollows, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("aboutId", ""+userInfo.getUid());
        httpRequestParam.addQuery("followType", "1");
        if (userInfo.getFollow()!=0) {
            httpRequestParam.addQuery("state", "0");
        }else {
            httpRequestParam.addQuery("state","1");
        }
        httpRequestParam.addQuery("uid", ""+SPUtils.getInstance().getCurrentUid());
        HuihuHttpUtils.httpRequest(httpRequestParam, huihuCallBack);

    }

    //获取圈子信息
    private void getNewCircleDiscussList(long circleId, long lastTime, int pageSize, long uid, HuihuCallBack huihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.getCircleDiscuss, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("circleId", "" + circleId);
        httpRequestParam.addQuery("lastTime", "" + lastTime);
        httpRequestParam.addQuery("pageSize", ""+pageSize);
        httpRequestParam.addQuery("uid", ""+uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, huihuCallBack);
    }
}
