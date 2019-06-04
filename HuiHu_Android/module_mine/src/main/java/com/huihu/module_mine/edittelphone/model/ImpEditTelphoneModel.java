package com.huihu.module_mine.edittelphone.model;

import com.huihu.module_mine.edittelphone.edittelphoneinterface.IEditTelphoneModel;
import com.huihu.module_mine.edittelphone.edittelphoneinterface.IEditTelphonePresenter;

public class ImpEditTelphoneModel implements IEditTelphoneModel {
    private final IEditTelphonePresenter iEditTelphonePresenter;

    public ImpEditTelphoneModel(IEditTelphonePresenter iEditTelphonePresenter) {
        this.iEditTelphonePresenter = iEditTelphonePresenter;
    }
}
