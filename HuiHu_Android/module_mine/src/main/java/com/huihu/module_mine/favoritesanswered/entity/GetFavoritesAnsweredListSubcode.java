package com.huihu.module_mine.favoritesanswered.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({GetFavoritesAnsweredListSubcode.Success})
@Retention(RetentionPolicy.SOURCE)
public @interface GetFavoritesAnsweredListSubcode {
    String Success = "0C08200";
    String ParameterError  = "0C08201";//表示参数有问题
    String BusinessError = "0C08202";//表示业务异常

    String DeletedSuccess = "0C08400";//删除成功
    String DeletedError = "0C08401";//表示删除参数有问题
    String DeletedBusinessError = "0C08402";//表示删除业务异常
}