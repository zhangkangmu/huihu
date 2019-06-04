package com.huihu.module_circle.mydiscuss.entity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({MyDiscussSubcode.success})
@Retention(RetentionPolicy.SOURCE)
public @interface MyDiscussSubcode {
    String success = "0C07500";
    String parameterError = "0C07501";//表示参数有问题
    String businessError = "0C07502";//表示用户未登录
}
