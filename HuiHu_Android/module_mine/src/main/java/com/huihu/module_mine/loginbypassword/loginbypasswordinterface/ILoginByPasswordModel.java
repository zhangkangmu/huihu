package com.huihu.module_mine.loginbypassword.loginbypasswordinterface;

import com.huihu.module_mine.loginbypassword.entity.UserLoginModel;

public interface ILoginByPasswordModel {

    void p2mPostLoginUser(UserLoginModel model);

    void p2mPostSendCode(int clientType, int countryCode, int sendType, String telePhone);
}
