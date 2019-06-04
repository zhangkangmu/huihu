package com.huihu.uilib.def;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.uilib.def.NetDataBoolean.NET_FALSE;
import static com.huihu.uilib.def.NetDataBoolean.NET_TRUE;

/**
 * create by wangjing on 2019/4/2 0002
 * description:
 */
@IntDef({NET_TRUE, NET_FALSE})
@Retention(value = RetentionPolicy.SOURCE)
public @interface NetDataBoolean {
    int NET_TRUE = 1;
    int NET_FALSE = 0;
}
