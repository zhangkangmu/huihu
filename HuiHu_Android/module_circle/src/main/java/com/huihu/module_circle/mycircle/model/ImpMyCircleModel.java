package com.huihu.module_circle.mycircle.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.circle.entity.JoinCircleSubCode;
import com.huihu.module_circle.mycircle.entity.GetMyCircleSubCode;
import com.huihu.module_circle.mycircle.entity.MyCircleInfo;
import com.huihu.module_circle.mycircle.mycircleinterface.IMyCircleModel;
import com.huihu.module_circle.mycircle.mycircleinterface.IMyCirclePresenter;

import java.security.acl.LastOwnerException;

public class ImpMyCircleModel implements IMyCircleModel {
    private final IMyCirclePresenter iMyCirclePresenter;
    private int PAGE_SIZE=20;
    public ImpMyCircleModel(IMyCirclePresenter iMyCirclePresenter) {
        this.iMyCirclePresenter = iMyCirclePresenter;
    }

    @Override
    public void p2mGetMyCircle(long lastTime, final boolean isMore) {
        getMyCircle(lastTime, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetMyCircleSubCode
                            .Success:
                        MyCircleInfo myCircleInfo = new Gson().fromJson(returnModel.getBodyMessage(), MyCircleInfo.class);
                        iMyCirclePresenter.m2pReturnMyCircle(myCircleInfo,isMore);
                        break;
                    case GetMyCircleSubCode.BusinessError:
                    case GetMyCircleSubCode.ParameterError:
                        default:
                            break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {

            }

            @Override
            public void onCompleted() {
                iMyCirclePresenter.m2pNetComplete();
            }
        });
    }

    @Override
    public void p2mJoinCircle(long circleId, int state, final int position) {
        joinCircle(circleId, state, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()) {
                    case JoinCircleSubCode
                            .Success:
                    iMyCirclePresenter.m2pJoinCircleSuccess(position);
                    break;
                    case JoinCircleSubCode.BusinessError:
                    case JoinCircleSubCode.ParameterError:
                    case JoinCircleSubCode.UserEnterred:
                        default:
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

    private void getMyCircle(long lastTime, HuihuCallBack mhuihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.GetMyCircle, NetworkTransmissionDefine.HttpMethod.GET);
        if (lastTime!=-1) {
            httpRequestParam.addQuery("lastTime", String.valueOf(lastTime));
        }
        httpRequestParam.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        httpRequestParam.addQuery("uid",String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(httpRequestParam, mhuihuCallBack);
    }
    public void joinCircle(long circleId,int state,HuihuCallBack mCallBack){
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.PutCircleMember, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("circleId",String.valueOf(circleId));
        httpRequestParam.addQuery("uid",String.valueOf(SPUtils.getInstance().getCurrentUid()));
        httpRequestParam.addQuery("state",String.valueOf(state));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
}
