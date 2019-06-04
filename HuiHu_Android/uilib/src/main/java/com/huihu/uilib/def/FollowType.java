package com.huihu.uilib.def;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.uilib.def.FollowType.CATEGORY;
import static com.huihu.uilib.def.FollowType.CONTENT;
import static com.huihu.uilib.def.FollowType.GROUP;
import static com.huihu.uilib.def.FollowType.HUMAN;

/**
 * create by wangjing on 2019/4/2 0002
 * description:
 */
@IntDef({HUMAN, CONTENT, CATEGORY, GROUP})
@Retention(value = RetentionPolicy.SOURCE)
public @interface FollowType {
    int HUMAN = 1;
    int CONTENT = 2;
    int CATEGORY = 3;
    int GROUP = 4;
}

