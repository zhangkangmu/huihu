package com.huihu.module_circle.newcirclediscuss.entity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface CancelFollowSubCode {
    String Success = "0C01200";
    String ParameterError = "0C01201";
    //表示业务异常
    String BusinessError = "0C01202";
}
