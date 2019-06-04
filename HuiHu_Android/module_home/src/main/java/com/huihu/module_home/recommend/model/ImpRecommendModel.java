package com.huihu.module_home.recommend.model;

import com.huihu.module_home.recommend.recommendinterface.IRecommendModel;
import com.huihu.module_home.recommend.recommendinterface.IRecommendPresenter;

public class ImpRecommendModel implements IRecommendModel {
    private final IRecommendPresenter iRecommendPresenter;

    public ImpRecommendModel(IRecommendPresenter iRecommendPresenter) {
        this.iRecommendPresenter = iRecommendPresenter;
    }
}
