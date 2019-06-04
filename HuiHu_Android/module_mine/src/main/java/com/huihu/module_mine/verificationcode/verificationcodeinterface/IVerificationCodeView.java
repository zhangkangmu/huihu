package com.huihu.module_mine.verificationcode.verificationcodeinterface;

public interface IVerificationCodeView {

    void p2vStartReset();

    void p2vShowLoadingDialog();

    void p2vDismissLoadingDialog();

    void p2vShowToast(String msg);

    void p2vFinishLogin();

    void p2vClearInput();
}
