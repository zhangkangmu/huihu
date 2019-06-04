package com.huihu.module_circle.newcircleintroduction.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_circle.newcircleintroduction.entity.CircleListInfo;
import com.huihu.module_circle.newcircleintroduction.newcircleintroductioninterface.INewCircleIntroductionModel;
import com.huihu.module_circle.newcircleintroduction.newcircleintroductioninterface.INewCircleIntroductionPresenter;

public class ImpNewCircleIntroductionModel implements INewCircleIntroductionModel {
    private final INewCircleIntroductionPresenter iNewCircleIntroductionPresenter;

    public ImpNewCircleIntroductionModel(INewCircleIntroductionPresenter iNewCircleIntroductionPresenter) {
        this.iNewCircleIntroductionPresenter = iNewCircleIntroductionPresenter;
    }

    @Override
    public void p2mRequestIntroduction(long circleId, long uid) {
        getCircleMessage(circleId, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                CircleListInfo info = new Gson().fromJson(returnModel.getBodyMessage(), CircleListInfo.class);
                iNewCircleIntroductionPresenter.m2pRturnIntroduction(info);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }

    //获取圈子信息
    private void getCircleMessage(long circleId, long uid, HuihuCallBack huihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.getCircleInfo, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("circleId", "" + circleId);
        httpRequestParam.addQuery("uid", "" + uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, huihuCallBack);
    }
}
