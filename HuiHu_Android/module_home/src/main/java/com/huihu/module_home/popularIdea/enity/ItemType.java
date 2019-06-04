package com.huihu.module_home.popularIdea.enity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/3
 * description:
 */
@IntDef({ItemType.question, ItemType.answer, ItemType.discuss})
@Retention(RetentionPolicy.SOURCE)
public @interface ItemType {
    int question = 1;
    int answer = 2;
    int discuss = 3;
}
