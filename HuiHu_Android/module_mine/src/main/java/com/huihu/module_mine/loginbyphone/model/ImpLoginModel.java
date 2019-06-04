package com.huihu.module_mine.loginbyphone.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_mine.loginbyphone.logininterface.ILoginModel;
import com.huihu.module_mine.loginbyphone.logininterface.ILoginPresenter;

public class ImpLoginModel implements ILoginModel {
    private final ILoginPresenter iLoginPresenter;

    public ImpLoginModel(ILoginPresenter iLoginPresenter) {
        this.iLoginPresenter = iLoginPresenter;
    }

    @Override
    public void p2mSendLoginSms(int code, String phone, int type) {
        getSendLoginSms(code, phone, type, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case GetSendLoginSmsSubcode.success:
                        iLoginPresenter.m2pSendLoginSmsSuccess();
                        break;
                    case GetSendLoginSmsSubcode.paramError:
                    case GetSendLoginSmsSubcode.businessException:
                    case GetSendLoginSmsSubcode.thirdFail:
                    case GetSendLoginSmsSubcode.frequent:
                        iLoginPresenter.m2pSubcodeWrong(model.getMessage());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iLoginPresenter.m2pSendLoginSmsFailed(errCode, strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    private void getSendLoginSms(int counTryCode, String phoneNumber, int sendType, HuihuCallBack callback) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.LoginAndRegister.getSendLoginSms, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("countryCode", counTryCode + "");
        httpRequestParam.addQuery("phoneNumber", phoneNumber);
        httpRequestParam.addQuery("sendType", sendType + "");
        HuihuHttpUtils.httpRequest(httpRequestParam, callback);
    }
}
