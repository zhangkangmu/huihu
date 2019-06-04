package com.huihu.module_circle.circlesetting.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_circle.circlesetting.circlesettinginterface.ICircleSettingModel;
import com.huihu.module_circle.circlesetting.circlesettinginterface.ICircleSettingPresenter;
import com.huihu.module_circle.createcircle.entity.CreateCircleInfo;
import com.huihu.module_circle.createcircle.entity.CreateCircleSubCode;

public class ImpCircleSettingModel implements ICircleSettingModel {
    private final ICircleSettingPresenter iCircleSettingPresenter;

    public ImpCircleSettingModel(ICircleSettingPresenter iCircleSettingPresenter) {
        this.iCircleSettingPresenter = iCircleSettingPresenter;
    }

    @Override
    public void p2mEditIntroduct(JsonObject jsonObject) {

    }
    private void postCircle(JsonObject jsonObject, HuihuCallBack mhuihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.PostCircle, NetworkTransmissionDefine.HttpMethod.POST);
        httpRequestParam.addBody(String.valueOf(jsonObject));
        HuihuHttpUtils.httpRequest(httpRequestParam, mhuihuCallBack);
    }
}
