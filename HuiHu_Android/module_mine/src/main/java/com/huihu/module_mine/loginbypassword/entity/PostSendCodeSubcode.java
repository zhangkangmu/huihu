package com.huihu.module_mine.loginbypassword.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/1
 * description:
 */
@Retention(RetentionPolicy.SOURCE)
public @interface PostSendCodeSubcode {
    String success = "0C02600";
    String paramError = "0C02601";
    String businessException = "0C02602";
    String thirdFail = "0C02603";//第三方接口调用失败
    String frequent = "0C02604";//短信请求频率过高
}
