package com.huihu.module_circle.mycircle.entity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(RetentionPolicy.SOURCE)
public @interface GetMyCircleSubCode {
    String Success = "0C07300";

    String ParameterError = "0C07301";
    //表示业务异常
    String BusinessError = "0C07302";
}
