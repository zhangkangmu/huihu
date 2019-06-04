package com.huihu.module_circle.newcirclerecommend.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({NewCircleRecommendListSubcode.SUCCESS})
@Retention(RetentionPolicy.SOURCE)
public @interface NewCircleRecommendListSubcode {
    String SUCCESS = "0C07D00";
    String BUSINESSERROR = "0C07D02";//表示业务异常
    String PARAMETERERROR = "0C07D01";//表示参数异常
}
