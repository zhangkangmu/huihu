package com.huihu.module_notification.circleinvitation.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.circleinvitation.entity.PutCircleMember.BusinessError;
import static com.huihu.module_notification.circleinvitation.entity.PutCircleMember.ParameterError;
import static com.huihu.module_notification.circleinvitation.entity.PutCircleMember.Success;

/**
 * create by wangjing on 2019/4/11 0011
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface PutCircleMember {
    String Success = "0C07A00";
    String ParameterError = "0C07A01";
    String BusinessError = "0C07A02";
    String AlreadyJoined = "0C07A03";
}
