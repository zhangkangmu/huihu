package com.huihu.module_notification.intdef;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.intdef.MessageStatus.DELETE;
import static com.huihu.module_notification.intdef.MessageStatus.READ;
import static com.huihu.module_notification.intdef.MessageStatus.UNREAD;

/**
 * create by wangjing on 2019/3/21 0021
 * description:
 */
@IntDef({UNREAD, READ, DELETE})
@Retention(RetentionPolicy.SOURCE)
public @interface MessageStatus {
    int UNREAD = 0;
    int READ = 1;
    int DELETE = 2;
}
