package com.huihu.module_mine.loginbypassword.entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/1
 * description:
 */
@Retention(RetentionPolicy.SOURCE)
public @interface PostLoginUserSubcode {
    String success = "0C02500";
    String thirdFail = "0C02501";//第三方接口调用失败
    String noBindPhone = "0C02502";//未绑定手机号
    String hadBindPhone = "0C02503";//已绑定手机号
    String businessException = "0C02504";//业务异常
}
