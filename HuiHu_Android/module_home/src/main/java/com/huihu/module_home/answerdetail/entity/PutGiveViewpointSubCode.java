package com.huihu.module_home.answerdetail.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(RetentionPolicy.SOURCE)
public @interface PutGiveViewpointSubCode {
    String Success = "0C03800";

    String ParameterError = "0C03801";
    //表示业务异常
    String BusinessError = "0C03802";
    String UnLogin="0C03803";
}
