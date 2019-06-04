package com.huihu.module_circle.circle.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.circle.circleinterface.ICircleModel;
import com.huihu.module_circle.circle.circleinterface.ICirclePresenter;
import com.huihu.module_circle.circle.entity.CircleBaseBean;
import com.huihu.module_circle.circle.entity.CircleInfo;
import com.huihu.module_circle.circle.entity.GetCircleSubCode;
import com.huihu.module_circle.circle.entity.JoinCircleSubCode;

public class ImpCircleModel implements ICircleModel {
    private final ICirclePresenter iCirclePresenter;
    private int PAGE_SIZE=3;
    public ImpCircleModel(ICirclePresenter iCirclePresenter) {
        this.iCirclePresenter = iCirclePresenter;
    }




    @Override
    public void p2mGetCircle() {
        getCircle(new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
            switch (returnModel.getSubCode()){
                case GetCircleSubCode
                        .Success:
                    CircleInfo circleInfo = new Gson().fromJson(returnModel.getBodyMessage(), CircleInfo.class);
                    iCirclePresenter.m2pReturnCircleInfo(circleInfo);
                    break;
                case GetCircleSubCode.ParameterError:
                case GetCircleSubCode.BusinessError:
                    default:
                        break;
            }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCirclePresenter.m2pNetFail();

            }

            @Override
            public void onCompleted() {
                iCirclePresenter.m2pNetComplete();
            }
        });
    }

    @Override
    public void p2mJoinCircle(long circleId, final int state, final int position, final int type) {
        joinCircle(circleId,state, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case JoinCircleSubCode
                            .Success:
                        iCirclePresenter.m2pJoinCircleSuccess(position,type);
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
                iCirclePresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iCirclePresenter.m2pNetComplete();
            }
        });
    }

    public void getCircle(HuihuCallBack mCallBack){
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.GetCircle, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        httpRequestParam.addQuery("uid",String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
    public void joinCircle(long circleId,int state,HuihuCallBack mCallBack){
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.PutCircleMember, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("circleId",String.valueOf(circleId));
        httpRequestParam.addQuery("uid",String.valueOf(SPUtils.getInstance().getCurrentUid()));
        httpRequestParam.addQuery("state",String.valueOf(state));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
}
