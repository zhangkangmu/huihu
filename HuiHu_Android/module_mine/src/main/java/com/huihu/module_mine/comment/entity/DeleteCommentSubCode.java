package com.huihu.module_mine.comment.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface DeleteCommentSubCode {
    String Success = "0C01800";

    String ParameterError = "0C01801";
    //表示业务异常
    String BusinessError = "0C01802";
    String UnLogin="0C01803";
}
