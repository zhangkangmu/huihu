package com.huihu.module_home.questionandanswerlist.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/1
 * description:
 */
@IntDef({Order.ORDER_PRAISE, Order.ODEER_TIME})
@Retention(RetentionPolicy.SOURCE)
public @interface Order {
    int ORDER_PRAISE = 1;//点赞
    int ODEER_TIME = 2;//时间倒序
}
