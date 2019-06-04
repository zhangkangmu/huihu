package com.huihu.module_mine.loginbypassword.loginbypasswordinterface;

public interface ILoginByPasswordPresenter {

    void v2pCheckVaild(int inputType, String string);

    void v2pCheckValidByNext(boolean isConfirm, String phone, String password);

    void v2pForgetPsw(String phone);

    void m2pStartVerification();

    void m2pLoginFailed(String msg);

    void m2pLoginSuccess(String message);
}
