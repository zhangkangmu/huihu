package com.huihu.commonlib.simpleRouter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SimpleRouterClassRegister {
    String key();

    @SimpleRouterObj.SimpleRouterRegisterType
    int type(); //类型是SimpleRouter里的定义
}
