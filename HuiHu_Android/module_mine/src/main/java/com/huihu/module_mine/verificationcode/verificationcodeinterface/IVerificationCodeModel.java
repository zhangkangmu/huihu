package com.huihu.module_mine.verificationcode.verificationcodeinterface;

import com.huihu.module_mine.verificationcode.entity.UserSmsLoginModel;

public interface IVerificationCodeModel {

    void p2mPostLoginUserBySms(UserSmsLoginModel model);

    void p2mPostValidateCode(String text, int code, int type, String phone);

    void p2mSaveUid(int uid);
}
