package com.huihu.module_notification.systemnotification.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.systemnotification.entity.SystemNoticeType.HasDetail;
import static com.huihu.module_notification.systemnotification.entity.SystemNoticeType.Normal;

/**
 * create by wangjing on 2019/4/3 0003
 * description:
 */
@IntDef({Normal, HasDetail})
@Retention(value = RetentionPolicy.SOURCE)
public @interface SystemNoticeType {
    int Normal = 1;
    int HasDetail = 2;
}
