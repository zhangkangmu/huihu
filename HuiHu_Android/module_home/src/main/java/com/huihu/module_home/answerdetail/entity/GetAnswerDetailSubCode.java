package com.huihu.module_home.answerdetail.entity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetAnswerDetailSubCode {
    String Success = "0C00800";

    String ParameterError = "0C00801";
    //表示业务异常
    String BusinessError = "0C00802";
}
