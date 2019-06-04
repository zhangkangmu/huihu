package com.huihu.uilib.checklogin;


import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.util.CheckLoginUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * create by wangjing on 2019/3/16 0016
 * description:
 */
@Aspect
public class AOPCheckLogin {

    private static final String CHECK_LOGIN_POINT = "execution(@com.huihu.uilib.checklogin.annotation.CheckLogin * *(..))";

    @Pointcut(CHECK_LOGIN_POINT)
    public void checkLogin(){

    }

    @Around("checkLogin()")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint){
        if (CheckLoginUtil.checkLogin()){
            try {
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        } else {
            SimpleRouter.getInstance().startActivity(RouterReDefine.LOGIN_ACTIVITY, null);

        }
    }
}
