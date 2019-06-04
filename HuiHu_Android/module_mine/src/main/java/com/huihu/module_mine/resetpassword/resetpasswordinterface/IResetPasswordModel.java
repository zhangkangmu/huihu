package com.huihu.module_mine.resetpassword.resetpasswordinterface;

public interface IResetPasswordModel {

    void p2mPutUpdatePasswordByCode(String phone, String password, String vcode);
}
