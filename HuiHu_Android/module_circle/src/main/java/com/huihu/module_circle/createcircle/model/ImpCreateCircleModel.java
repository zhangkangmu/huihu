package com.huihu.module_circle.createcircle.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_circle.createcircle.createcircleinterface.ICreateCircleModel;
import com.huihu.module_circle.createcircle.createcircleinterface.ICreateCirclePresenter;
import com.huihu.module_circle.createcircle.entity.CreateCircleInfo;
import com.huihu.module_circle.createcircle.entity.CreateCircleSubCode;

public class ImpCreateCircleModel implements ICreateCircleModel {
    private final ICreateCirclePresenter iCreateCirclePresenter;

    public ImpCreateCircleModel(ICreateCirclePresenter iCreateCirclePresenter) {
        this.iCreateCirclePresenter = iCreateCirclePresenter;
    }

    @Override
    public void p2mCreateCircle(JsonObject jsonObject) {
        postCircle(jsonObject, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case CreateCircleSubCode
                            .Success:
                        CreateCircleInfo createCircleInfo = new Gson().fromJson(returnModel.getBodyMessage(), CreateCircleInfo.class);
                        iCreateCirclePresenter.m2pReturnCircleInfo(createCircleInfo);
                        break;
                    case CreateCircleSubCode.BusinessError:
                    case CreateCircleSubCode.ParameterError:
                    case CreateCircleSubCode.CreateLimited:
                        iCreateCirclePresenter.m2pReturnCircleInfoError(returnModel.getSubCode());
                        default:
                            break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCreateCirclePresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iCreateCirclePresenter.m2pNetComplete();

            }
        });
    }
    private void postCircle(JsonObject jsonObject, HuihuCallBack mhuihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.PostCircle, NetworkTransmissionDefine.HttpMethod.POST);
        httpRequestParam.addBody(String.valueOf(jsonObject));
        HuihuHttpUtils.httpRequest(httpRequestParam, mhuihuCallBack);
    }
}
