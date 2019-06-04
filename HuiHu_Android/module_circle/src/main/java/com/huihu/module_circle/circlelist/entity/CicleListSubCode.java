package com.huihu.module_circle.circlelist.entity;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({})
@Retention(value = RetentionPolicy.SOURCE)
public @interface CicleListSubCode {
    String Success = "0C06F00";
    String ParameterError = "0C06F01";  //参数有问题
    String BusinessError = "0C06F02";   //表示业务异常
    String CircleNotExist = "0C06F03";   //圈子不存在
    String CircleDeleted = "0C06F04";   //圈子被删除
}
