package com.huihu.module_notification.reply.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.reply.entity.PutAnswerReadStateSubcode.BusinessError;
import static com.huihu.module_notification.reply.entity.PutAnswerReadStateSubcode.ParameterError;
import static com.huihu.module_notification.reply.entity.PutAnswerReadStateSubcode.Success;

/**
 * create by wangjing on 2019/4/13 0013
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface PutAnswerReadStateSubcode {
    String Success = "0C13800";
    String ParameterError = "0C13801";
    String BusinessError = "0C13802";
}
