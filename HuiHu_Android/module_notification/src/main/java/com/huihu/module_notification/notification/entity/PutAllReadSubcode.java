package com.huihu.module_notification.notification.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.notification.entity.PutAllReadSubcode.BusinessError;
import static com.huihu.module_notification.notification.entity.PutAllReadSubcode.Success;

/**
 * create by wangjing on 2019/4/13 0013
 * description:
 */
@StringDef({Success, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface PutAllReadSubcode {
    String Success = "0C13100";
    String BusinessError = "0C13102";
}
