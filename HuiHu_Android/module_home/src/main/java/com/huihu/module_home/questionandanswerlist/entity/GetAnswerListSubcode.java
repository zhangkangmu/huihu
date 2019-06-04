package com.huihu.module_home.questionandanswerlist.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/1
 * description:
 */
@StringDef({GetAnswerListSubcode.success})
@Retention(RetentionPolicy.SOURCE)
public @interface GetAnswerListSubcode {
    String success = "0C00900";
    String businessException = "0C00901";//业务异常
    String paramError = "0C00902";//传参有问题
}
