package com.huihu.module_home.recommend.presenter;

import com.huihu.module_home.recommend.recommendinterface.IRecommendModel;
import com.huihu.module_home.recommend.recommendinterface.IRecommendPresenter;
import com.huihu.module_home.recommend.recommendinterface.IRecommendView;
import com.huihu.module_home.recommend.model.ImpRecommendModel;

public class ImpRecommendPresenter implements IRecommendPresenter {
    private final IRecommendModel iRecommendModel = new ImpRecommendModel(this);
    private final IRecommendView iRecommendView;

    public ImpRecommendPresenter(IRecommendView iRecommendView) {
        this.iRecommendView = iRecommendView;
    }

    @Override
    public void m2pGetRecommendList() {

    }
}
