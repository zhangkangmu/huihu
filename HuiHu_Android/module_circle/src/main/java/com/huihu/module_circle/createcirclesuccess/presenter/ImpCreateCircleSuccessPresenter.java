package com.huihu.module_circle.createcirclesuccess.presenter;

import com.huihu.module_circle.createcirclesuccess.createcirclesuccessinterface.ICreateCircleSuccessModel;
import com.huihu.module_circle.createcirclesuccess.createcirclesuccessinterface.ICreateCircleSuccessPresenter;
import com.huihu.module_circle.createcirclesuccess.createcirclesuccessinterface.ICreateCircleSuccessView;
import com.huihu.module_circle.createcirclesuccess.entity.CreatedCircleInfo;
import com.huihu.module_circle.createcirclesuccess.model.ImpCreateCircleSuccessModel;

public class ImpCreateCircleSuccessPresenter implements ICreateCircleSuccessPresenter {
    private final ICreateCircleSuccessModel iCreateCircleSuccessModel = new ImpCreateCircleSuccessModel(this);
    private final ICreateCircleSuccessView iCreateCircleSuccessView;

    public ImpCreateCircleSuccessPresenter(ICreateCircleSuccessView iCreateCircleSuccessView) {
        this.iCreateCircleSuccessView = iCreateCircleSuccessView;
    }

    @Override
    public void v2pGetCircleInfo(long circleId) {
        iCreateCircleSuccessModel.p2mvGetCircleInfo(circleId);
    }

    @Override
    public void m2pReturnCreatedCircleInfo(CreatedCircleInfo createdCircleInfo) {
        iCreateCircleSuccessView.p2vReturnCreatedCircleInfo(createdCircleInfo);
    }

    @Override
    public void m2pNetComplete() {

    }

    @Override
    public void m2pReturnCreatedCircleInfoError(String subCode) {

    }

    @Override
    public void m2pNetError() {

    }
}
