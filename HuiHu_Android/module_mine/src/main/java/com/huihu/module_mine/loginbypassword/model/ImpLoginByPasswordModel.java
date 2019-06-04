package com.huihu.module_mine.loginbypassword.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_mine.loginbypassword.entity.PostLoginUserSubcode;
import com.huihu.module_mine.loginbypassword.entity.PostSendCodeSubcode;
import com.huihu.module_mine.loginbypassword.entity.UserLoginModel;
import com.huihu.module_mine.loginbypassword.loginbypasswordinterface.ILoginByPasswordModel;
import com.huihu.module_mine.loginbypassword.loginbypasswordinterface.ILoginByPasswordPresenter;

public class ImpLoginByPasswordModel implements ILoginByPasswordModel {
    private final ILoginByPasswordPresenter iLoginByPasswordPresenter;

    public ImpLoginByPasswordModel(ILoginByPasswordPresenter iLoginByPasswordPresenter) {
        this.iLoginByPasswordPresenter = iLoginByPasswordPresenter;
    }

    @Override
    public void p2mPostLoginUser(UserLoginModel model) {
        postLoginUser(model, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case PostLoginUserSubcode.success:
                        iLoginByPasswordPresenter.m2pLoginSuccess(model.getMessage());
                        break;
                    case PostLoginUserSubcode.thirdFail:
                    case PostLoginUserSubcode.noBindPhone:
                    case PostLoginUserSubcode.hadBindPhone:
                    case PostLoginUserSubcode.businessException:
                        iLoginByPasswordPresenter.m2pLoginFailed(model.getMessage());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iLoginByPasswordPresenter.m2pLoginFailed(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPostSendCode(int clientType, int countryCode, int sendType, String telePhone) {
        postSendCode(clientType, countryCode, sendType, telePhone, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case PostSendCodeSubcode.success:
                        iLoginByPasswordPresenter.m2pStartVerification();
                        break;
                    case PostSendCodeSubcode.paramError:
                    case PostSendCodeSubcode.businessException:
                    case PostSendCodeSubcode.thirdFail:
                    case PostSendCodeSubcode.frequent:
                        iLoginByPasswordPresenter.m2pLoginFailed(model.getMessage());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iLoginByPasswordPresenter.m2pLoginFailed(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    private void postSendCode(int clientType, int countryCode, int sendType, String telePhone, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Sms.postSendCode, NetworkTransmissionDefine.HttpMethod.POST);
        param.addQuery("clientType", clientType + "");
        param.addQuery("countryCode", countryCode + "");
        param.addQuery("sendType", sendType + "");
        param.addQuery("telephone", telePhone);
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    private void postLoginUser(UserLoginModel model, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.LoginAndRegister.postLoginUser, NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(model));
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
