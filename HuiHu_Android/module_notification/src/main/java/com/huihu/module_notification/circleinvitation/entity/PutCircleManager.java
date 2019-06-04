package com.huihu.module_notification.circleinvitation.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.circleinvitation.entity.PutCircleManager.BusinessError;
import static com.huihu.module_notification.circleinvitation.entity.PutCircleManager.ParameterError;
import static com.huihu.module_notification.circleinvitation.entity.PutCircleManager.Success;

/**
 * create by wangjing on 2019/4/13 0013
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface PutCircleManager {
    String Success = "0C13500";
    String ParameterError = "0C13501";
    String BusinessError = "0C13502";
    String ManagerIsFull = "0C13503";
}
