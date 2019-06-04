package com.huihu.module_home.questiondraft.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/17
 * description:
 */
@StringDef({GetDraftListSubcode.success, GetDraftListSubcode.paramError, GetDraftListSubcode.businessException})
@Retention(RetentionPolicy.SOURCE)
public @interface GetDraftListSubcode {
    String success = "0C08E00";
    String paramError = "0C08E01";
    String businessException = "0C08E02";
}
