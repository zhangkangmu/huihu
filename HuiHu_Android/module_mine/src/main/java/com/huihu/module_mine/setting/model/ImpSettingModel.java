package com.huihu.module_mine.setting.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_mine.setting.settinginterface.ISettingModel;
import com.huihu.module_mine.setting.settinginterface.ISettingPresenter;

public class ImpSettingModel implements ISettingModel {
    private final ISettingPresenter iSettingPresenter;

    public ImpSettingModel(ISettingPresenter iSettingPresenter) {
        this.iSettingPresenter = iSettingPresenter;
    }

    @Override
    public void p2mPutLoginOut(String uid) {
        putLoginOut(uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {

            }

            @Override
            public void onError(int errCode, String strErrMsg) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }

    private void putLoginOut(String uid, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.LoginAndRegister.putLoginOut, NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("uid", uid);
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
