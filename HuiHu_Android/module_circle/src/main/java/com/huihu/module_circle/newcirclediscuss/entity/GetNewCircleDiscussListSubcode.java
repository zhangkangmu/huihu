package com.huihu.module_circle.newcirclediscuss.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({GetNewCircleDiscussListSubcode.success})
@Retention(RetentionPolicy.SOURCE)
public @interface GetNewCircleDiscussListSubcode {
    String success = "0C07400";
    String ParameterError = "0C07401";//传入的参数有问题
    String BusinessError = "0C07402";//业务异常
}
