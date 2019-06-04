package com.huihu.module_mine.followsandfollowed.entity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(RetentionPolicy.SOURCE)
public @interface GetFollowsAndFansSubCode {
    String Success = "0C02100";
    String ParameterError = "0C02101";
    //表示业务异常
    String BusinessError = "0C02102";
    String ThirdLoginError="0C02103";

}
