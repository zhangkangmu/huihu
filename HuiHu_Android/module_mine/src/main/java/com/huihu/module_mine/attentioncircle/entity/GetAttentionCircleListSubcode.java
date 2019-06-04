package com.huihu.module_mine.attentioncircle.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({GetAttentionCircleListSubcode.success})
@Retention(RetentionPolicy.SOURCE)
public @interface GetAttentionCircleListSubcode {
    String success = "0C02400";
    String unLogin = "0C02401";//表示用户未登录
}
