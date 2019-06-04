package com.huihu.module_mine.editnewphone.editnewphoneinterface;

public interface IEditNewPhonePresenter {

    void v2pCheckPhoneEmpty(String phone);

    void v2pCheckPhoneValid(int countryCode,String phone,int sendType);

    void m2pPostSendCodeSuccess();

    void m2pPostSendCodeFailed(int code, String msg);

    void m2pSubcodeWrong(String message);
}
