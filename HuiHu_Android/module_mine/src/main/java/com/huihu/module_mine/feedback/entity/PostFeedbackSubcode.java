package com.huihu.module_mine.feedback.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_mine.feedback.entity.PostFeedbackSubcode.BusinessError;
import static com.huihu.module_mine.feedback.entity.PostFeedbackSubcode.ParameterError;
import static com.huihu.module_mine.feedback.entity.PostFeedbackSubcode.Success;
import static com.huihu.module_mine.feedback.entity.PostFeedbackSubcode.ThirdError;
import static com.huihu.module_mine.feedback.entity.PostFeedbackSubcode.UserNoLogin;

/**
 * create by wangjing on 2019/4/2 0002
 * description:
 */
@StringDef({Success, ParameterError, BusinessError, ThirdError, UserNoLogin})
@Retention(value = RetentionPolicy.SOURCE)
public @interface PostFeedbackSubcode {
    String Success = "0C03700";
    String ParameterError = "0C03701";
    String BusinessError = "0C03702";
    String ThirdError = "0C03703";
    String UserNoLogin = "0C03704";
}
