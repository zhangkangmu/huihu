package com.huihu.module_notification.reply.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.reply.entity.GetAttentionReplyListSubcode.BusinessError;
import static com.huihu.module_notification.reply.entity.GetAttentionReplyListSubcode.ParameterError;
import static com.huihu.module_notification.reply.entity.GetAttentionReplyListSubcode.Success;

/**
 * create by wangjing on 2019/4/9 0009
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetAttentionReplyListSubcode {
    String Success = "0C12F00";
    String ParameterError = "0C12F01";
    String BusinessError = "0C12F02";
}
