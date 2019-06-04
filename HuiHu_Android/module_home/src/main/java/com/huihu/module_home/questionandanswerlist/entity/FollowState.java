package com.huihu.module_home.questionandanswerlist.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/8
 * description:
 */
@IntDef({FollowState.unFollow, FollowState.followed})
@Retention(RetentionPolicy.SOURCE)
public @interface FollowState {
    int unFollow = 0;
    int followed = 1;
}
