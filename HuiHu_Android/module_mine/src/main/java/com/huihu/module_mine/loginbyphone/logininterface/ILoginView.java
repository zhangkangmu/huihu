package com.huihu.module_mine.loginbyphone.logininterface;

public interface ILoginView {


    void p2vSetNextClickAble();

    void p2vSetNextUnClickAble();

    void p2vStartVerifcation();

    void p2vDismissDialog();

    void p2vShowToast(String msg);

    void p2vShowLoadDialog();
}
