package com.huihu.module_circle.discuss.entity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(RetentionPolicy.SOURCE)
public @interface GetRecemendDiscussSubCode {
    String Success = "0C07C00";

    String ParameterError = "0C07C01";
    //表示业务异常
    String BusinessError = "0C07C02";
}
