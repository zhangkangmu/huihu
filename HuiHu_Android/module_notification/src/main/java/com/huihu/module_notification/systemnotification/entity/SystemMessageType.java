package com.huihu.module_notification.systemnotification.entity;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.systemnotification.entity.SystemMessageType.ANSWER;
import static com.huihu.module_notification.systemnotification.entity.SystemMessageType.QUESTION;
import static com.huihu.module_notification.systemnotification.entity.SystemMessageType.WEB;

/**
 * create by wangjing on 2019/4/4 0004
 * description:
 */
@IntDef({QUESTION, ANSWER, WEB})
@Retention(value = RetentionPolicy.SOURCE)
public @interface SystemMessageType {
    int QUESTION = 1;
    int ANSWER = 2;
    int WEB = 3;
}
