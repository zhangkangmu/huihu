package com.huihu.module_home.addquestioncategory.entitiy;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/19
 * description:
 */
@IntDef({TypeMatch.matchTitle, TypeMatch.matchKeyword})
@Retention(RetentionPolicy.SOURCE)
public @interface TypeMatch {
    int matchTitle = 1;
    int matchKeyword = 2;
}
