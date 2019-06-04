package com.huihu.module_circle.circle.entity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(RetentionPolicy.SOURCE)
public @interface GetCircleSubCode {
    String Success = "0C07900";

    String ParameterError = "0C07901";
    //表示业务异常
    String BusinessError = "0C07902";
}
