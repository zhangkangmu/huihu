package com.wangjing.module_push;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.bailun.localeslibrary.CurLocales;
import com.huihu.commonlib.utils.SPUtils;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * create by wangjing on 2019/4/16 0016
 * description:
 */
public class Push {

    private static final int ALIAS_SEQUENCE = 0x0001;
    private static final int TAGS_SEQUENCE = 0x0010;

    static boolean isDebugPush;

    public static void init(Application application, boolean isDebug){
        JPushInterface.setDebugMode(isDebug);
        JPushInterface.init(application);
        isDebugPush = isDebug;
        initUser(application);
    }

    public static void initUser(Context context){
        String tag = getTag();
        long uid = SPUtils.getInstance().getCurrentUid();
        JPushInterface.setAlias(context, ALIAS_SEQUENCE, new StringBuilder().append(tag).append('_').append(uid).toString());
        Set<String> set = new HashSet<>();
        set.add(tag);
        JPushInterface.setTags(context, TAGS_SEQUENCE, set);
    }

    static String getTag(){
        String tag = "";
        switch (CurLocales.instance().getCurEnvironment()){
            case DEBUG:
                tag = "Dev";
                break;
            case TEST:
                tag = "Test";
                break;
            case PRE_RELEASE:
                tag = "Pre";
                break;
            case RELEASE:
                tag = "Release";
                break;
        }
        return tag;
    }

    static void logD(String tag, String msg){
        if (isDebugPush) Log.d(tag, msg);
    }
}
