package com.huihu.module_mine.loginbyphone.logininterface;

public interface ILoginPresenter {

    void v2pCheckPhoneEmpty(String phone);

    void v2pCheckPhoneValid(boolean isConfirm, int countryCode, String phone, int sendType);

    void m2pSendLoginSmsSuccess();

    void m2pSendLoginSmsFailed(int code, String msg);

    void m2pSubcodeWrong(String message);
}
