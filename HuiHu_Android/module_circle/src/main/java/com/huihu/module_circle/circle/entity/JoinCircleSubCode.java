package com.huihu.module_circle.circle.entity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(RetentionPolicy.SOURCE)
public @interface JoinCircleSubCode {
    String Success = "0C07A00";

    String ParameterError = "0C07A01";
    //表示业务异常
    String BusinessError = "0C07A02";
    String UserEnterred="0C07A03";
}
