package com.huihu.module_notification.newreply.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.newreply.entity.GetNewReplySubcode.BusinessError;
import static com.huihu.module_notification.newreply.entity.GetNewReplySubcode.ParameterError;
import static com.huihu.module_notification.newreply.entity.GetNewReplySubcode.Success;

/**
 * create by wangjing on 2019/4/11 0011
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetNewReplySubcode {
    String Success = "0C09600";
    String ParameterError = "0C09601";
    String BusinessError = "0C09602";
}
