package com.huihu.module_home.inviteanswer.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/13
 * description:
 */

@StringDef({GetRecentLogingUserSubcode.success,
        GetRecentLogingUserSubcode.paramError,
        GetRecentLogingUserSubcode.businessException})
@Retention(RetentionPolicy.SOURCE)
public @interface GetRecentLogingUserSubcode {
    String success = "0C03D00";
    String paramError = "0C03D01";
    String businessException = "0C03D02";
}
