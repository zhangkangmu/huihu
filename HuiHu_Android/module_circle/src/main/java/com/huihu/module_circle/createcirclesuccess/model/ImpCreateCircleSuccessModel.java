package com.huihu.module_circle.createcirclesuccess.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.createcirclesuccess.createcirclesuccessinterface.ICreateCircleSuccessModel;
import com.huihu.module_circle.createcirclesuccess.createcirclesuccessinterface.ICreateCircleSuccessPresenter;
import com.huihu.module_circle.createcirclesuccess.entity.CreatedCircleInfo;
import com.huihu.module_circle.createcirclesuccess.entity.GetCircleInfoSubCode;

public class ImpCreateCircleSuccessModel implements ICreateCircleSuccessModel {
    private final ICreateCircleSuccessPresenter iCreateCircleSuccessPresenter;

    public ImpCreateCircleSuccessModel(ICreateCircleSuccessPresenter iCreateCircleSuccessPresenter) {
        this.iCreateCircleSuccessPresenter = iCreateCircleSuccessPresenter;
    }

    @Override
    public void p2mvGetCircleInfo(final long circleId) {
        getCircleInfo(circleId, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetCircleInfoSubCode
                            .Success:
                        CreatedCircleInfo createdCircleInfo = new Gson().fromJson(returnModel.getBodyMessage(), CreatedCircleInfo.class);
                       iCreateCircleSuccessPresenter.m2pReturnCreatedCircleInfo(createdCircleInfo);
                        break;
                    case GetCircleInfoSubCode.BusinessError:
                    case GetCircleInfoSubCode.ParameterError:
                    case GetCircleInfoSubCode.WithoutCircle:
                    case GetCircleInfoSubCode.CircleDeleted:
                        iCreateCircleSuccessPresenter.m2pReturnCreatedCircleInfoError(returnModel.getSubCode());
                        default:
                            break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCreateCircleSuccessPresenter.m2pNetError();
            }

            @Override
            public void onCompleted() {
                iCreateCircleSuccessPresenter.m2pNetComplete();
            }
        });
    }
    private void getCircleInfo(long circleId, HuihuCallBack mhuihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.getCircleInfo, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("circleId",String.valueOf(circleId));
        httpRequestParam.addQuery("uid",String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(httpRequestParam, mhuihuCallBack);
    }
}
