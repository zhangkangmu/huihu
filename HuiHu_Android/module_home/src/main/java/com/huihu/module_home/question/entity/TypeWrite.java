package com.huihu.module_home.question.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/19
 * description:
 */
@IntDef({TypeWrite.question, TypeWrite.discuss})
@Retention(RetentionPolicy.SOURCE)
public @interface TypeWrite {
    int question = 1;
    int discuss = 2;
}
