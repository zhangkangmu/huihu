package com.huihu.module_mine.resetpassword.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/1
 * description:
 */
@StringDef({PutUpdatePasswordByCodeSubcode.success,
        PutUpdatePasswordByCodeSubcode.paramError,
        PutUpdatePasswordByCodeSubcode.businessException,
        PutUpdatePasswordByCodeSubcode.thirdFail})
@Retention(RetentionPolicy.SOURCE)
public @interface PutUpdatePasswordByCodeSubcode {
    String success = "0C02900";
    String paramError = "0C02901";
    String businessException = "0C02902";
    String thirdFail = "0C02903";
}
