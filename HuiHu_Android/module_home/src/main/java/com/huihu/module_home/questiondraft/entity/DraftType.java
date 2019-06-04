package com.huihu.module_home.questiondraft.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/17
 * description:
 */
@IntDef({DraftType.question, DraftType.answer})
@Retention(RetentionPolicy.SOURCE)
public @interface DraftType {
    int question = 1;
    int answer = 2;
}
