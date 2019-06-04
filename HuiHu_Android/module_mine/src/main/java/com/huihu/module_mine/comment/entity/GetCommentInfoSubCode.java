package com.huihu.module_mine.comment.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetCommentInfoSubCode {
    String Success = "0C01B00";

    String ParameterError = "0C01B01";
    //表示业务异常
    String BusinessError = "0C01B02";
    String NoUser="0C01B03";
}
