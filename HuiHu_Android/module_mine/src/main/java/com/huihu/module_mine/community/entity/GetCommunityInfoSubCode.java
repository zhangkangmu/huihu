package com.huihu.module_mine.community.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetCommunityInfoSubCode {
        String Success = "0C07600";

        String ParameterError = "0C07601";
        //表示业务异常
        String BusinessError = "0C07602";
}
