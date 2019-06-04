package com.huihu.module_mine.verificationcode.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/1
 * description:
 */
@StringDef({PostLoginUserBySmsSubcode.success,
        PostLoginUserBySmsSubcode.paramError,
        PostLoginUserBySmsSubcode.businessException,
        PostLoginUserBySmsSubcode.thirdFail,
        PostLoginUserBySmsSubcode.loginFail,
        PostLoginUserBySmsSubcode.registerFail,
        PostLoginUserBySmsSubcode.registerSuccess})
@Retention(RetentionPolicy.SOURCE)
public @interface PostLoginUserBySmsSubcode {
    String success = "0C00400";
    String paramError = "0C00401";
    String businessException = "0C00402";
    String thirdFail = "0C00403";
    String loginFail = "0C00404";
    String registerFail = "0C00405";
    String registerSuccess = "0C00406";
}
