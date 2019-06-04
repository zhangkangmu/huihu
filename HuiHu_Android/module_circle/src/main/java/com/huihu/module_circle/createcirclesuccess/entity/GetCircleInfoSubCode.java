package com.huihu.module_circle.createcirclesuccess.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(RetentionPolicy.SOURCE)
public @interface GetCircleInfoSubCode {
    String Success = "0C06F00";

    String ParameterError = "0C06F01";
    //表示业务异常
    String BusinessError = "0C06F02";
    String WithoutCircle="0C06F03";
    String CircleDeleted="0C06F04";
}
