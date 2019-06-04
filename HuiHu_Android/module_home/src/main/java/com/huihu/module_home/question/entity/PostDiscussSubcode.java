package com.huihu.module_home.question.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/19
 * description:
 */
@StringDef({PostDiscussSubcode.success,
        PostDiscussSubcode.paramError,
        PostDiscussSubcode.businessException,
        PostDiscussSubcode.banned,
        PostDiscussSubcode.frequently,
        PostDiscussSubcode.limited})
@Retention(RetentionPolicy.SOURCE)
public @interface PostDiscussSubcode {
    String success = "0C07700";
    String paramError = "0C07701";
    String businessException = "0C07702";
    String banned = "0C07703";
    String frequently = "0C07704";
    String limited = "0C07705";
}
