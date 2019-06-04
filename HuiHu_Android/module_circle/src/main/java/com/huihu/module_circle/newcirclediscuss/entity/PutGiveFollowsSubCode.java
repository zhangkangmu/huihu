package com.huihu.module_circle.newcirclediscuss.entity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface PutGiveFollowsSubCode {
    String Success = "0C02000";
    String ParameterError = "0C02001";
    //表示业务异常
    String BusinessError = "0C02002";
    String UnLogin="0C02003";
}
