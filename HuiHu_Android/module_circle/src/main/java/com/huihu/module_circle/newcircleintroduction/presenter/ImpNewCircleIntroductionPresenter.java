package com.huihu.module_circle.newcircleintroduction.presenter;

import com.huihu.module_circle.newcircleintroduction.entity.CircleListInfo;
import com.huihu.module_circle.newcircleintroduction.newcircleintroductioninterface.INewCircleIntroductionModel;
import com.huihu.module_circle.newcircleintroduction.newcircleintroductioninterface.INewCircleIntroductionPresenter;
import com.huihu.module_circle.newcircleintroduction.newcircleintroductioninterface.INewCircleIntroductionView;
import com.huihu.module_circle.newcircleintroduction.model.ImpNewCircleIntroductionModel;

public class ImpNewCircleIntroductionPresenter implements INewCircleIntroductionPresenter {
    private final INewCircleIntroductionModel iNewCircleIntroductionModel = new ImpNewCircleIntroductionModel(this);
    private final INewCircleIntroductionView iNewCircleIntroductionView;

    public ImpNewCircleIntroductionPresenter(INewCircleIntroductionView iNewCircleIntroductionView) {
        this.iNewCircleIntroductionView = iNewCircleIntroductionView;
    }

    @Override
    public void v2pRequestIntroduction(long circleId, long uid) {
        iNewCircleIntroductionModel.p2mRequestIntroduction(circleId, uid);
    }

    @Override
    public void m2pRturnIntroduction(CircleListInfo info) {
        iNewCircleIntroductionView.p2vRturnIntroduction(info);
    }
}
