package com.huihu.module_mine.answered.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetAnsweredInfoSubCode {
    String Success = "0C00B00";

    String ParameterError = "0C00B01";
    //表示业务异常
    String BusinessError = "0C00B02";
}
