package com.huihu.module_circle.circlesetting.presenter;

import com.google.gson.JsonObject;
import com.huihu.module_circle.circlesetting.circlesettinginterface.ICircleSettingModel;
import com.huihu.module_circle.circlesetting.circlesettinginterface.ICircleSettingPresenter;
import com.huihu.module_circle.circlesetting.circlesettinginterface.ICircleSettingView;
import com.huihu.module_circle.circlesetting.model.ImpCircleSettingModel;

public class ImpCircleSettingPresenter implements ICircleSettingPresenter {
    private final ICircleSettingModel iCircleSettingModel = new ImpCircleSettingModel(this);
    private final ICircleSettingView iCircleSettingView;

    public ImpCircleSettingPresenter(ICircleSettingView iCircleSettingView) {
        this.iCircleSettingView = iCircleSettingView;
    }

    @Override
    public void v2pEditIntroduct(JsonObject jsonObject) {
        iCircleSettingModel.p2mEditIntroduct(jsonObject);
    }
}
