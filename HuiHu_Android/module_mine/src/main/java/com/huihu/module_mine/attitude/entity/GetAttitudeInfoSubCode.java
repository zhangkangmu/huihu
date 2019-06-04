package com.huihu.module_mine.attitude.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetAttitudeInfoSubCode {
    String Success = "0C03900";

    String ParameterError = "0C03901";
    //表示业务异常
    String BusinessError = "0C03902";
    String NoUser="0C03905";
    String UnLogin="0C03903";
}
