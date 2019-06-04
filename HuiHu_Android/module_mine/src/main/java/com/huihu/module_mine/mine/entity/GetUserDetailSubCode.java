package com.huihu.module_mine.mine.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(RetentionPolicy.SOURCE)
public @interface GetUserDetailSubCode {
    String Success = "0C02F00";

    String ParameterError = "0C02F01";
    //表示业务异常
    String BusinessError = "0C02F02";
    String ThirdError="0C02F03";
    String UnLogin="0C02F04";
}
