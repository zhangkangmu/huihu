package com.huihu.module_mine.verificationcode.verificationcodeinterface;

import com.huihu.module_mine.loginbyphone.entity.LoginReturnModel;

public interface IVerificationCodePresenter {

    void v2pCheckVerification(int countryCode, String text, int sendType, String phone);

    void m2pStartReset();

    void m2pVerificationFailed(String msg);

    void m2pLoginBySmsSuccess(LoginReturnModel loginReturnModel);
}
