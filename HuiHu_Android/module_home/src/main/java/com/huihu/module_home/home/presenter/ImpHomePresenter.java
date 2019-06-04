package com.huihu.module_home.home.presenter;

import com.huihu.module_home.home.homeinterface.IHomeModel;
import com.huihu.module_home.home.homeinterface.IHomePresenter;
import com.huihu.module_home.home.homeinterface.IHomeView;
import com.huihu.module_home.home.model.ImpHomeModel;

public class ImpHomePresenter implements IHomePresenter {
    private final IHomeModel iHomeModel = new ImpHomeModel(this);
    private final IHomeView iHomeView;

    public ImpHomePresenter(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
    }
}
