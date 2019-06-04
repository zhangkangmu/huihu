package com.huihu.module_mine.editnewphoneverication.editnewphonevericationinterface;

import com.huihu.module_mine.verificationcode.entity.UserSmsLoginModel;

public interface IEditNewPhoneVericationModel {

    void p2mPostValidateCode(String text, int code, int type, String phone);

    void p2mPutUpdatePhone(String phone);


}
