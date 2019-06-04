package com.huihu.module_mine.selectcountry.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * create by ouyangjianfeng on 2019/4/9
 * description:
 */
@StringDef({SelectCountrySubcode.success, SelectCountrySubcode.businessException, SelectCountrySubcode.thirdFail})
@Retention(RetentionPolicy.SOURCE)
public @interface SelectCountrySubcode {
    String success = "0C02800";
    String businessException = "0C02801";
    String thirdFail = "0C02802";
}
