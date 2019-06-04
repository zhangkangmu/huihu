package com.huihu.module_home.categorydetail.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_home.categorydetail.entity.CategoryInfoSubcode.BusinessError;
import static com.huihu.module_home.categorydetail.entity.CategoryInfoSubcode.ParameterError;
import static com.huihu.module_home.categorydetail.entity.CategoryInfoSubcode.Success;

/**
 * create by wangjing on 2019/4/1 0001
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface CategoryInfoSubcode {
    String Success = "0C06400";
    String ParameterError = "0C06401";
    String BusinessError = "0C06402";
}
