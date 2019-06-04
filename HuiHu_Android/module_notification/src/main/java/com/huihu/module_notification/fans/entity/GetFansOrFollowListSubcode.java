package com.huihu.module_notification.fans.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_notification.fans.entity.GetFansOrFollowListSubcode.BusinessError;
import static com.huihu.module_notification.fans.entity.GetFansOrFollowListSubcode.ParameterError;
import static com.huihu.module_notification.fans.entity.GetFansOrFollowListSubcode.Success;
import static com.huihu.module_notification.fans.entity.GetFansOrFollowListSubcode.ThirdPartyError;

/**
 * create by wangjing on 2019/4/2 0002
 * description:
 */
@StringDef({Success, ParameterError, BusinessError, ThirdPartyError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface GetFansOrFollowListSubcode {
    String Success = "0C02100";
    String ParameterError = "0C02101";
    String BusinessError = "0C02102";
    String ThirdPartyError = "0C02103";
}
