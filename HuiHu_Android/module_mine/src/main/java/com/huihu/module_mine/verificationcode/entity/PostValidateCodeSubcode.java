package com.huihu.module_mine.verificationcode.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/1
 * description:
 */
@StringDef({PostValidateCodeSubcode.success,
        PostValidateCodeSubcode.paramError,
        PostValidateCodeSubcode.businessException,
        PostValidateCodeSubcode.thirdFail,
        PostValidateCodeSubcode.verificationFail})
@Retention(RetentionPolicy.SOURCE)
public @interface PostValidateCodeSubcode {
    String success = "0C02700";
    String paramError = "0C02701";
    String verificationFail = "0C02702";
    String businessException = "0C02703";
    String thirdFail = "0C02704";
}
