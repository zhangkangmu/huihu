package com.huihu.module_mine.editnewphoneverication.model;


import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.editnewphoneverication.editnewphonevericationinterface.IEditNewPhoneVericationModel;
import com.huihu.module_mine.editnewphoneverication.editnewphonevericationinterface.IEditNewPhoneVericationPresenter;

public class ImpEditNewPhoneVericationModel implements IEditNewPhoneVericationModel {
    private final IEditNewPhoneVericationPresenter iEditNewPhoneVericationPresenter;
    private String SUBCODE_SUCCESS = "01";

    public ImpEditNewPhoneVericationModel(IEditNewPhoneVericationPresenter iEditNewPhoneVericationPresenter) {
        this.iEditNewPhoneVericationPresenter = iEditNewPhoneVericationPresenter;
    }


    @Override
    public void p2mPostValidateCode(final String text, final int code, final int type, final String phone) {
        postValidateCode(text, code, type, phone, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                iEditNewPhoneVericationPresenter.m2pPostValidateCodeSuccess(text,code,type,phone);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iEditNewPhoneVericationPresenter.m2pPostValidateCodeError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void p2mPutUpdatePhone(String tel) {
        String uid = SPUtils.getInstance().getCurrentUid() + "";
        putUpdatePhone(tel, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iEditNewPhoneVericationPresenter.m2pPutUpdatePhoneSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iEditNewPhoneVericationPresenter.m2pPostValidateCodeError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void putUpdatePhone(String phoneNumber, String uid, HuihuCallBack callBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.User.putUpdatePhone, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("phoneNumber", phoneNumber + "");
        httpRequestParam.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(httpRequestParam, callBack);
    }


    private void postValidateCode(String text, int code, int type, String phone, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Sms.postValidateCode, NetworkTransmissionDefine.HttpMethod.POST);
        param.addQuery("code", text);
        param.addQuery("countryCode", code + "");
        param.addQuery("sendType", type + "");
        param.addQuery("telephone", phone);
        HuihuHttpUtils.httpRequest(param, callBack);
    }


}
