package com.huihu.module_mine.setting.presenter;

import com.huihu.module_mine.setting.settinginterface.ISettingModel;
import com.huihu.module_mine.setting.settinginterface.ISettingPresenter;
import com.huihu.module_mine.setting.settinginterface.ISettingView;
import com.huihu.module_mine.setting.model.ImpSettingModel;

public class ImpSettingPresenter implements ISettingPresenter {
    private final ISettingModel iSettingModel = new ImpSettingModel(this);
    private final ISettingView iSettingView;

    public ImpSettingPresenter(ISettingView iSettingView) {
        this.iSettingView = iSettingView;
    }

    @Override
    public void v2pExitLogin(String uid) {
        iSettingModel.p2mPutLoginOut(uid);
    }
}
