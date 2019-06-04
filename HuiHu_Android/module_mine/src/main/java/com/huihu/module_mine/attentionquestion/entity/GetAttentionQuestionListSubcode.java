package com.huihu.module_mine.attentionquestion.entity;


import android.support.annotation.StringDef;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({GetAttentionQuestionListSubcode.success})
@Retention(RetentionPolicy.SOURCE)
public @interface GetAttentionQuestionListSubcode {
    String success = "0C02200";
    String unLogin = "0C02201";//表示用户未登录
}
