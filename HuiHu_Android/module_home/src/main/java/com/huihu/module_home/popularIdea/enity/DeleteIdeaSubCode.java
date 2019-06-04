package com.huihu.module_home.popularIdea.enity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface DeleteIdeaSubCode {
    String Success = "0C00600";
    String ParameterError = "0C00601";
    //表示业务异常
    String BusinessError = "0C00602";
}
