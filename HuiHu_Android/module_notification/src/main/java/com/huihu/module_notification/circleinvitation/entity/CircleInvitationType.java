package com.huihu.module_notification.circleinvitation.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.circleinvitation.entity.CircleInvitationType.AllowedManager;
import static com.huihu.module_notification.circleinvitation.entity.CircleInvitationType.CancelManagerPermission;
import static com.huihu.module_notification.circleinvitation.entity.CircleInvitationType.InviteJoinCircle;
import static com.huihu.module_notification.circleinvitation.entity.CircleInvitationType.InviteManagerCircle;
import static com.huihu.module_notification.circleinvitation.entity.CircleInvitationType.JoinedCircle;
import static com.huihu.module_notification.circleinvitation.entity.CircleInvitationType.ManagerFull;
import static com.huihu.module_notification.circleinvitation.entity.CircleInvitationType.OtherRefuseManagerCircle;
import static com.huihu.module_notification.circleinvitation.entity.CircleInvitationType.RefusedManager;

/**
 * create by wangjing on 2019/4/9 0009
 * description:
 */
@IntDef({InviteJoinCircle, InviteManagerCircle, OtherRefuseManagerCircle, CancelManagerPermission
        , JoinedCircle, RefusedManager, AllowedManager, ManagerFull})
@Retention(value = RetentionPolicy.SOURCE)
public @interface CircleInvitationType {
    int InviteJoinCircle = 1;
    int InviteManagerCircle = 2;
    int OtherRefuseManagerCircle = 3;
    int CancelManagerPermission = 4;
    int JoinedCircle = 5;
    int RefusedManager = 6;
    int AllowedManager = 7;
    int ManagerFull = 8;
}
