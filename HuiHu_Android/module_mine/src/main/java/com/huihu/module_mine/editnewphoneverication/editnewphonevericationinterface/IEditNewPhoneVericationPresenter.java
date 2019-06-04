package com.huihu.module_mine.editnewphoneverication.editnewphonevericationinterface;

import com.huihu.module_mine.loginbyphone.entity.LoginReturnModel;

public interface IEditNewPhoneVericationPresenter {

    void v2pCheckVerification(int countryCode, String text, int sendType, String phone);

    void m2pPostValidateCodeError(String msg);
    void m2pPostValidateCodeSuccess(String code,int countryCode, int sendType, String phone);

    void m2pPutUpdatePhoneSuccess();

}
