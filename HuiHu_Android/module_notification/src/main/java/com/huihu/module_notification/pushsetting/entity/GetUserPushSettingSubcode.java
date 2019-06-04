package com.huihu.module_notification.pushsetting.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.pushsetting.entity.GetUserPushSettingSubcode.BusinessError;
import static com.huihu.module_notification.pushsetting.entity.GetUserPushSettingSubcode.ParameterError;
import static com.huihu.module_notification.pushsetting.entity.GetUserPushSettingSubcode.Success;

/**
 * create by wangjing on 2019/4/2 0002
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetUserPushSettingSubcode {
    String Success = "0C01401";
    String ParameterError = "0C01402";
    String BusinessError = "0C01403";
}
