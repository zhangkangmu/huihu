package com.huihu.uilib.model;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/10
 * description:
 */
@IntDef({TextStyle.level_first, TextStyle.level_second})
@Retention(RetentionPolicy.SOURCE)
public @interface TextStyle {
    int level_first = 1;
    int level_second = 2;
}
