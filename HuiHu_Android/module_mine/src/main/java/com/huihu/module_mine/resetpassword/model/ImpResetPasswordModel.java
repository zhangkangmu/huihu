package com.huihu.module_mine.resetpassword.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_mine.resetpassword.entity.PutUpdatePasswordByCodeSubcode;
import com.huihu.module_mine.resetpassword.resetpasswordinterface.IResetPasswordModel;
import com.huihu.module_mine.resetpassword.resetpasswordinterface.IResetPasswordPresenter;

public class ImpResetPasswordModel implements IResetPasswordModel {
    private final IResetPasswordPresenter iResetPasswordPresenter;

    public ImpResetPasswordModel(IResetPasswordPresenter iResetPasswordPresenter) {
        this.iResetPasswordPresenter = iResetPasswordPresenter;
    }

    @Override
    public void p2mPutUpdatePasswordByCode(String phone, String password, String vcode) {
        putUpdatePasswordByCode(phone, password, vcode, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case PutUpdatePasswordByCodeSubcode.success:
                        iResetPasswordPresenter.m2pUpdatePswSuccess(model.getMessage());
                        break;
                    case PutUpdatePasswordByCodeSubcode.paramError:
                    case PutUpdatePasswordByCodeSubcode.businessException:
                    case PutUpdatePasswordByCodeSubcode.thirdFail:
                        iResetPasswordPresenter.m2pHttpRequestFailed(model.getMessage());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iResetPasswordPresenter.m2pHttpRequestFailed(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    private void putUpdatePasswordByCode(String phone, String password, String vcode, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.User.putUpdatePasswordByCode, NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("account", phone);
        param.addQuery("newPassword", password);
        param.addQuery("vcode", vcode);
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
