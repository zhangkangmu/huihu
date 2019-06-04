package com.huihu.module_circle.createcircle.presenter;

import com.google.gson.JsonObject;
import com.huihu.module_circle.createcircle.createcircleinterface.ICreateCircleModel;
import com.huihu.module_circle.createcircle.createcircleinterface.ICreateCirclePresenter;
import com.huihu.module_circle.createcircle.createcircleinterface.ICreateCircleView;
import com.huihu.module_circle.createcircle.entity.CreateCircleInfo;
import com.huihu.module_circle.createcircle.model.ImpCreateCircleModel;

public class ImpCreateCirclePresenter implements ICreateCirclePresenter {
    private final ICreateCircleModel iCreateCircleModel = new ImpCreateCircleModel(this);
    private final ICreateCircleView iCreateCircleView;

    public ImpCreateCirclePresenter(ICreateCircleView iCreateCircleView) {
        this.iCreateCircleView = iCreateCircleView;
    }

    @Override
    public void v2pCreateCircle(JsonObject jsonObject) {
        iCreateCircleModel.p2mCreateCircle(jsonObject);
    }

    @Override
    public void m2pReturnCircleInfo(CreateCircleInfo createCircleInfo) {
        iCreateCircleView.p2vReturnCircleInfo(createCircleInfo);
    }

    @Override
    public void m2pNetFail() {

    }

    @Override
    public void m2pNetComplete() {

    }

    @Override
    public void m2pReturnCircleInfoError(String subCode) {
        iCreateCircleView.p2vReturnCircleInfoError(subCode);
    }
}
