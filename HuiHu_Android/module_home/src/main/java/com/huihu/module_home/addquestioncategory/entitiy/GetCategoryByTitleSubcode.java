package com.huihu.module_home.addquestioncategory.entitiy;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/2
 * description:
 */
@StringDef({GetCategoryByTitleSubcode.success, GetCategoryByTitleSubcode.paramError, GetCategoryByTitleSubcode.businessException})
@Retention(RetentionPolicy.SOURCE)
public @interface GetCategoryByTitleSubcode {
    String success = "0C06600";
    String paramError = "0C06601";
    String businessException = "0C06602";
}
