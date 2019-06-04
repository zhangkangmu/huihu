package com.huihu.module_mine.editnewphone.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_mine.editnewphone.editnewphoneinterface.IEditNewPhoneModel;
import com.huihu.module_mine.editnewphone.editnewphoneinterface.IEditNewPhonePresenter;


public class ImpEditNewPhoneModel implements IEditNewPhoneModel {
    private final IEditNewPhonePresenter iEditNewPhonePresenter;

    public ImpEditNewPhoneModel(IEditNewPhonePresenter iEditNewPhonePresenter) {
        this.iEditNewPhonePresenter = iEditNewPhonePresenter;
    }

    private String SUBCODE_WRONG_PARAM = "01";
    private String SUBCODE_BUSINESS_EXCEPTION = "02";
    private String SUBCODE_SUCCESS = "10";
    private String SUBCODE_FREQUENCY = "11";

    public void p2mPostSendCode(int clientType, int countryCode, int sendType, String telePhone) {
        postSendCode(clientType, countryCode, sendType, telePhone, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                iEditNewPhonePresenter.m2pPostSendCodeSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iEditNewPhonePresenter.m2pPostSendCodeFailed(errCode, strErrMsg);
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
}
