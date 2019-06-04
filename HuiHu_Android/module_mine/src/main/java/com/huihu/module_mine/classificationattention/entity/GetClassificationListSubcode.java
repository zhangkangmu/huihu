package com.huihu.module_mine.classificationattention.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({GetClassificationListSubcode.success})
@Retention(RetentionPolicy.SOURCE)
public @interface GetClassificationListSubcode {
    String success = "0C02300";
    String unLogin = "0C02301";//表示用户未登录
}
