package com.huihu.commonlib.utils;

import android.content.Context;
import android.content.pm.PackageManager;

import com.huihu.commonlib.base.BaseApplication;

/**
 * create by wangjing on 2019/3/27 0027
 * description:
 */
public class AppUtil {

    public static String getVersionName(){
        Context context = BaseApplication.getApplication();
        String name = null;
        try {
            name =context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            name = "";
        }
        return name;
    }

}
