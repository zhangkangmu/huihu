package com.huihu.uilib.subcode;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.uilib.subcode.PutGiveFollowsSubcode.BusinessError;
import static com.huihu.uilib.subcode.PutGiveFollowsSubcode.ParameterError;
import static com.huihu.uilib.subcode.PutGiveFollowsSubcode.Success;
import static com.huihu.uilib.subcode.PutGiveFollowsSubcode.UserNoLogin;

/**
 * create by wangjing on 2019/4/2 0002
 * description:
 */
@StringDef({Success, ParameterError, BusinessError, UserNoLogin})
@Retention(value = RetentionPolicy.SOURCE)
public @interface PutGiveFollowsSubcode {
    String Success = "0C02000";
    String ParameterError = "0C02001";
    String BusinessError = "0C02002";
    String UserNoLogin = "0C02003";
}
