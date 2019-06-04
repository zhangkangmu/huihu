package com.huihu.module_notification.like.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.like.entity.GetLikeMeListSubcode.BusinessError;
import static com.huihu.module_notification.like.entity.GetLikeMeListSubcode.ParameterError;
import static com.huihu.module_notification.like.entity.GetLikeMeListSubcode.Success;
import static com.huihu.module_notification.like.entity.GetLikeMeListSubcode.UserNoLogin;

/**
 * create by wangjing on 2019/4/2 0002
 * description:
 */
@StringDef({Success, ParameterError, BusinessError, UserNoLogin})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetLikeMeListSubcode {
    String Success = "0C03A00";
    String ParameterError = "0C03A01";
    String BusinessError = "0C03A02";
    String UserNoLogin = "0C03A03";
}
