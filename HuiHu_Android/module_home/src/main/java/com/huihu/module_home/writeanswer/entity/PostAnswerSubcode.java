package com.huihu.module_home.writeanswer.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/2
 * description:
 */
@Retention(RetentionPolicy.SOURCE)
public @interface PostAnswerSubcode {
    String success = "0C00F00";
    String paramError = "0C00F01";
    String businessException = "0C00F02";
    String banned = "0C00F03";//用户被禁言
    String obtained = "0C00F04";//内容被下架
    String hasAnswered = "0C00F05";//已回答过
}
