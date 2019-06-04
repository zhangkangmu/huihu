package com.huihu.module_notification.notification.entity;

import android.support.annotation.StringDef;
import android.support.annotation.StringRes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.notification.entity.DeleteSessionAnswerSubcode.ParameterError;
import static com.huihu.module_notification.notification.entity.DeleteSessionAnswerSubcode.Success;


/**
 * create by wangjing on 2019/4/15 0015
 * description:
 */
@StringDef({Success, ParameterError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface DeleteSessionAnswerSubcode {
    String Success = "0C13000";
    String ParameterError = "0C13001";
}
