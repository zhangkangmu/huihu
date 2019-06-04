package com.huihu.module_home.questionandanswerlist.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/1
 * description:
 */
@StringDef({GetAnswerListSubcode.success,
        GetAnswerListSubcode.paramError,
        GetAnswerListSubcode.businessException})
@Retention(RetentionPolicy.SOURCE)
public @interface GetQuestionInfoSubcode {
    String success = "0C00D00";
    String paramError = "0C00D01";
    String businessException = "0C00D02";
}
