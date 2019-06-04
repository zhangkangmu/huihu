package com.huihu.module_mine.loginbyphone.logininterface;

public interface ILoginModel {
    void p2mSendLoginSms(int code, String phone, int type);
}
