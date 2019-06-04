package com.huihu.module_notification.intdef;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.intdef.NoticeId.ATTENTION;
import static com.huihu.module_notification.intdef.NoticeId.CIRCLE_INVITE;
import static com.huihu.module_notification.intdef.NoticeId.COMMENT;
import static com.huihu.module_notification.intdef.NoticeId.REPLY;
import static com.huihu.module_notification.intdef.NoticeId.REPLY_INVITE;
import static com.huihu.module_notification.intdef.NoticeId.SYSTEM_MESSAGE;
import static com.huihu.module_notification.intdef.NoticeId.SYSTEM_NOTIFY;

/**
 * create by wangjing on 2019/3/19 0019
 * description:通知类型
 */
@IntDef({REPLY, ATTENTION, COMMENT, REPLY_INVITE, CIRCLE_INVITE, SYSTEM_MESSAGE, SYSTEM_NOTIFY})
@Retention(RetentionPolicy.SOURCE)
public @interface NoticeId {
    int REPLY = 1;//回答
    int ATTENTION = 2;//关注
    int COMMENT = 3;//评论
    int REPLY_INVITE = 4;//回答邀请
    int CIRCLE_INVITE = 5;//圈子邀请
    int SYSTEM_MESSAGE = 6;//系统消息
    int SYSTEM_NOTIFY = 7;//系统通知
    int LIKE_ME = 20;//点赞
    int MULTI_CLIENT_LOGIN = 100;//多客户端登录
}
