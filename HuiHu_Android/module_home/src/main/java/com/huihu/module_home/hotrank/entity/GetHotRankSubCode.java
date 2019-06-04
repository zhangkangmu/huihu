package com.huihu.module_home.hotrank.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetHotRankSubCode {
    String Success = "0C00A00";

    String ParameterError = "0C00A01";
    //表示业务异常
    String BusinessError = "0C00A02";
}
