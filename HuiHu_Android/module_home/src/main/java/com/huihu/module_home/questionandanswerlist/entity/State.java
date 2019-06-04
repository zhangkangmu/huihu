package com.huihu.module_home.questionandanswerlist.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/8
 * description:
 */
@IntDef({State.cancel, State.putAttention})
@Retention(RetentionPolicy.SOURCE)
public @interface State {
    int cancel = 0;
    int putAttention = 1;
}
