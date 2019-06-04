package com.huihu.module_mine.resetpassword.resetpasswordinterface;

public interface IResetPasswordPresenter {

    void v2pResetPsw(String phone, String password, String vcode);

    void m2pHttpRequestFailed(String msg);

    void m2pUpdatePswSuccess(String message);

    void v2pCheckNextState(String password);
}
