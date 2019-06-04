package com.huihu.module_mine.resetpassword.resetpasswordinterface;

public interface IResetPasswordView {

    void p2vShowLoadingDialog();

    void p2vDimissLoadingDialog();

    void p2vShowToast(String message);

    void p2vSetNextClickAble();

    void p2vSetNextUnClickAble();

    void p2vFinish();
}
