package com.huihu.module_notification.notification.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.notification.entity.GetMessageSessionListSubcode.BusinessError;
import static com.huihu.module_notification.notification.entity.GetMessageSessionListSubcode.ParameterError;
import static com.huihu.module_notification.notification.entity.GetMessageSessionListSubcode.Success;

/**
 * create by wangjing on 2019/4/3 0003
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetMessageSessionListSubcode {
    String Success = "0C12E00";
    String ParameterError = "0C12E01";
    String BusinessError = "0C12E02";
}
