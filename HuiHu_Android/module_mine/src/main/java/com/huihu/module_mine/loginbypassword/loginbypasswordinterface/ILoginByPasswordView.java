package com.huihu.module_mine.loginbypassword.loginbypasswordinterface;

public interface ILoginByPasswordView {

    void p2vSetNextInvalid();

    void p2vSetNextVaild();

    void p2vShowPhoneCorrect();

    void p2vHidePhoneCorrect();

    void showToast(String s);

    void p2vStartVerification();

    void p2vShowLoadingDialog();

    void p2vShowToast(String msg);

    void p2vDissmissLoadingDialog();
}
