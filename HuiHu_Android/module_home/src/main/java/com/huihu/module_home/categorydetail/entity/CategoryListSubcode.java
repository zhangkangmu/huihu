package com.huihu.module_home.categorydetail.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.module_home.categorydetail.entity.CategoryListSubcode.BusinessError;
import static com.huihu.module_home.categorydetail.entity.CategoryListSubcode.ParameterError;
import static com.huihu.module_home.categorydetail.entity.CategoryListSubcode.Success;

/**
 * create by wangjing on 2019/4/1 0001
 * description:
 */
@StringDef({Success, ParameterError, BusinessError})
@Retention(value = RetentionPolicy.SOURCE)
public @interface CategoryListSubcode {
    String Success = "0C06500";
    String ParameterError = "0C06501";
    String BusinessError = "0C06502";
}
