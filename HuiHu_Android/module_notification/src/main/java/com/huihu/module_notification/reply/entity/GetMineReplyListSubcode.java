package com.huihu.module_notification.reply.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.reply.entity.GetMineReplyListSubcode.BusinessError;
import static com.huihu.module_notification.reply.entity.GetMineReplyListSubcode.ParameterError;
import static com.huihu.module_notification.reply.entity.GetMineReplyListSubcode.Success;

/**
 * create by wangjing on 2019/4/9 0009
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetMineReplyListSubcode {
    String Success = "0C13400";
    String ParameterError = "0C13401";
    String BusinessError = "0C13402";
}
