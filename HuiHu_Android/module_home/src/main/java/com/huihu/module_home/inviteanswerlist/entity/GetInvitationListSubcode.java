package com.huihu.module_home.inviteanswerlist.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/18
 * description:
 */
@StringDef({GetInvitationListSubcode.success, GetInvitationListSubcode.paramError})
@Retention(RetentionPolicy.SOURCE)
public @interface GetInvitationListSubcode {
    String success = "0C13200";
    String paramError = "0C13201";
}
