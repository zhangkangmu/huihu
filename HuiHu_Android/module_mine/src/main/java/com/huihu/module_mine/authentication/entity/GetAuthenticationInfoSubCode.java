package com.huihu.module_mine.authentication.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetAuthenticationInfoSubCode {
    String Success = "0C04400";
    //表示业务异常
    String BusinessError = "0C04402";
    String UnLogin="0C04401";
}
