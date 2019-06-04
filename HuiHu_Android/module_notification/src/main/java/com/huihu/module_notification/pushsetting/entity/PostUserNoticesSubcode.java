package com.huihu.module_notification.pushsetting.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.pushsetting.entity.PostUserNoticesSubcode.BusinessError;
import static com.huihu.module_notification.pushsetting.entity.PostUserNoticesSubcode.ParameterError;
import static com.huihu.module_notification.pushsetting.entity.PostUserNoticesSubcode.Success;

/**
 * create by wangjing on 2019/4/2 0002
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface PostUserNoticesSubcode {
    String Success = "0C01303";
    String ParameterError = "0C01301";
    String BusinessError = "0C01302";
}
