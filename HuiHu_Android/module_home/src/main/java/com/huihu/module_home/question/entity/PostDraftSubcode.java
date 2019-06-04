package com.huihu.module_home.question.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/17
 * description:
 */
@StringDef({PostDraftSubcode.success, PostDraftSubcode.paramError, PostDraftSubcode.businessException})
@Retention(RetentionPolicy.SOURCE)
public @interface PostDraftSubcode {
    String success = "0C08C00";
    String paramError = "0C08C01";
    String businessException = "0C08C02";
}
