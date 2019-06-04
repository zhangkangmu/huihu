package com.huihu.module_home.home.model;

import com.huihu.module_home.home.homeinterface.IHomeModel;
import com.huihu.module_home.home.homeinterface.IHomePresenter;

public class ImpHomeModel implements IHomeModel {
    private final IHomePresenter iHomePresenter;

    public ImpHomeModel(IHomePresenter iHomePresenter) {
        this.iHomePresenter = iHomePresenter;
    }
}
