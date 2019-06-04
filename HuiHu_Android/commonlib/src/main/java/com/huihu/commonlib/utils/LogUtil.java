package com.huihu.commonlib.utils;


import android.util.Log;


public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int LEVEL;

    public static void debug(Class cls, String msg) {
        if (Constant.isDebug) {
            Log.w(cls.getSimpleName(), msg);
//            Logger.getInstance(cls).setDebug(true);
//            Logger.getInstance(cls).debug(msg);
        }
    }

    public static void debug(String cls, String msg) {
        if (Constant.isDebug) {
            Log.w(cls, msg);
        }
    }

    static {
        if (Constant.isDebug) {
            LEVEL = VERBOSE;//暂时需要检测一些日志，先不屏蔽
        } else {
            LEVEL = NOTHING;
        }
    }

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }
}
