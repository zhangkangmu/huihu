package com.huihu.module_circle.createcircle.entity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(RetentionPolicy.SOURCE)
public @interface CreateCircleSubCode {
    String Success = "0C06E00";

    String ParameterError = "0C06E01";
    //表示业务异常
    String BusinessError = "0C06E02";
    //创建圈子上限
    String CreateLimited="0C06E03";
}
