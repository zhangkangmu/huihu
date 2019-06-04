package com.huihu.module_mine.edittelphone.presenter;

import com.huihu.module_mine.edittelphone.edittelphoneinterface.IEditTelphoneModel;
import com.huihu.module_mine.edittelphone.edittelphoneinterface.IEditTelphonePresenter;
import com.huihu.module_mine.edittelphone.edittelphoneinterface.IEditTelphoneView;
import com.huihu.module_mine.edittelphone.model.ImpEditTelphoneModel;

public class ImpEditTelphonePresenter implements IEditTelphonePresenter {
    private final IEditTelphoneModel iEditTelphoneModel = new ImpEditTelphoneModel(this);
    private final IEditTelphoneView iEditTelphoneView;

    public ImpEditTelphonePresenter(IEditTelphoneView iEditTelphoneView) {
        this.iEditTelphoneView = iEditTelphoneView;
    }
}
