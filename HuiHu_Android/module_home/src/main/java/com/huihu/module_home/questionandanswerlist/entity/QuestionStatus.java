package com.huihu.module_home.questionandanswerlist.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/4
 * description:
 */
@IntDef({QuestionStatus.release, QuestionStatus.violation, QuestionStatus.review})
@Retention(RetentionPolicy.SOURCE)
public @interface QuestionStatus {
    int release = 1;//发布
    int violation = 2;//违规下架
    int review = 3;//重新审核
}
