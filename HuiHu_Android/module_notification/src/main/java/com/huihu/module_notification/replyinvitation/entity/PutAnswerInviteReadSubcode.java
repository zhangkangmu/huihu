package com.huihu.module_notification.replyinvitation.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.replyinvitation.entity.PutAnswerInviteReadSubcode.BusinessError;
import static com.huihu.module_notification.replyinvitation.entity.PutAnswerInviteReadSubcode.ParameterError;
import static com.huihu.module_notification.replyinvitation.entity.PutAnswerInviteReadSubcode.Success;

/**
 * create by wangjing on 2019/4/17 0017
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface PutAnswerInviteReadSubcode {
    String Success = "0C13900";
    String ParameterError = "0C13901";
    String BusinessError = "0C13902";
}
