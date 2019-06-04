package com.huihu.module_mine.loginbyphone.model;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/1
 * description:
 */
@StringDef({})
@Retention(RetentionPolicy.SOURCE)
public @interface GetSendLoginSmsSubcode {
    String success = "0C00104";
    String paramError = "0C00105";//传参有问题
    String businessException = "0C00106";//业务异常
    String thirdFail = "0C00107";//第三方接口调用失败
    String frequent = "0C00108";//短信请求频率过高
}
