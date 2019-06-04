package com.huihu.module_mine.verificationcode.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.loginbyphone.entity.LoginReturnModel;
import com.huihu.module_mine.verificationcode.entity.PostLoginUserBySmsSubcode;
import com.huihu.module_mine.verificationcode.entity.PostValidateCodeSubcode;
import com.huihu.module_mine.verificationcode.entity.UserSmsLoginModel;
import com.huihu.module_mine.verificationcode.verificationcodeinterface.IVerificationCodeModel;
import com.huihu.module_mine.verificationcode.verificationcodeinterface.IVerificationCodePresenter;

public class ImpVerificationCodeModel implements IVerificationCodeModel {
    private final IVerificationCodePresenter iVerificationCodePresenter;


    public ImpVerificationCodeModel(IVerificationCodePresenter iVerificationCodePresenter) {
        this.iVerificationCodePresenter = iVerificationCodePresenter;
    }

    @Override
    public void p2mPostLoginUserBySms(UserSmsLoginModel model) {
        postLoginUserBySms(model, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case PostLoginUserBySmsSubcode.success:
                        LoginReturnModel loginReturnModel = new Gson().fromJson(model.getBodyMessage(), LoginReturnModel.class);
                        iVerificationCodePresenter.m2pLoginBySmsSuccess(loginReturnModel);
                        break;
                    case PostLoginUserBySmsSubcode.paramError:
                    case PostLoginUserBySmsSubcode.businessException:
                    case PostLoginUserBySmsSubcode.thirdFail:
                    case PostLoginUserBySmsSubcode.loginFail:
                    case PostLoginUserBySmsSubcode.registerFail:
                    case PostLoginUserBySmsSubcode.registerSuccess:
                        iVerificationCodePresenter.m2pVerificationFailed(model.getMessage());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iVerificationCodePresenter.m2pVerificationFailed(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPostValidateCode(String text, int code, int type, String phone) {
        postValidateCode(text, code, type, phone, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case PostValidateCodeSubcode.success:
                        iVerificationCodePresenter.m2pStartReset();
                        break;
                    case PostValidateCodeSubcode.paramError:
                    case PostValidateCodeSubcode.businessException:
                    case PostValidateCodeSubcode.thirdFail:
                    case PostValidateCodeSubcode.verificationFail:
                        iVerificationCodePresenter.m2pVerificationFailed(model.getMessage());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iVerificationCodePresenter.m2pVerificationFailed(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mSaveUid(int uid) {
        SPUtils.getInstance().saveCurrentUid(uid);
    }

    /**
     * 重置密码时验证验证码
     */
    private void postValidateCode(String text, int code, int type, String phone, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Sms.postValidateCode, NetworkTransmissionDefine.HttpMethod.POST);
        param.addQuery("code", text);
        param.addQuery("countryCode", code + "");
        param.addQuery("sendType", type + "");
        param.addQuery("telephone", phone);
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    /**
     * 短信登录
     */
    private void postLoginUserBySms(UserSmsLoginModel model, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.LoginAndRegister.postLoginUserBySms, NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(model));
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
