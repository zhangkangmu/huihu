package com.huihu.module_home.addquestioncategory.entitiy;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/2
 * description:
 */
@StringDef({PostQuestionSubcode.success,
        PostQuestionSubcode.paramError,
        PostQuestionSubcode.businessException,
        PostQuestionSubcode.banned,
        PostQuestionSubcode.frequent,
        PostQuestionSubcode.contentLimit,
        PostQuestionSubcode.titleExist})
@Retention(RetentionPolicy.SOURCE)
public @interface PostQuestionSubcode {
    String success = "0C01000";
    String paramError = "0C01001";
    String businessException = "0C01002";
    String banned = "0C01003";//用户被禁言
    String frequent = "0C01004";//发布提问太频繁
    String contentLimit = "0C01005";//发布内容已达上限
    String titleExist = "0C01006";//标题已存在
}
