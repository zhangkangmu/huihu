package com.huihu.module_notification.intdef;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by wangjing on 2019/4/3 0003
 * description:
 */
@StringDef
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetUserNoticeByNoticeIdSubcode {
    String Success = "0C12D00";
    String ParameterError = "0C12D01";
    String BusinessError = "0C12D02";
}
