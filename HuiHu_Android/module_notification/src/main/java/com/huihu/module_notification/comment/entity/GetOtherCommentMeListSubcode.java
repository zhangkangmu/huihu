package com.huihu.module_notification.comment.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.comment.entity.GetOtherCommentMeListSubcode.BusinessError;
import static com.huihu.module_notification.comment.entity.GetOtherCommentMeListSubcode.NoUser;
import static com.huihu.module_notification.comment.entity.GetOtherCommentMeListSubcode.ParameterError;
import static com.huihu.module_notification.comment.entity.GetOtherCommentMeListSubcode.Success;

/**
 * create by wangjing on 2019/4/2 0002
 * description:
 */
@StringDef({Success, ParameterError, BusinessError, NoUser})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetOtherCommentMeListSubcode {
    String Success = "0C01D00";
    String ParameterError = "0C01D01";
    String BusinessError = "0C01D02";
    String NoUser = "0C01D03";
}
