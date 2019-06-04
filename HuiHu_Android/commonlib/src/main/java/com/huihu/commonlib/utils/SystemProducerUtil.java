package com.huihu.commonlib.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by yh on 2018/8/9 000916:37
 * 类说明：手机产商工具类
 * 1.获取当前手机的产商
 * 2.是否为小米MIUI系统
 * 3.是否为华为EMUI系统
 */

public class SystemProducerUtil {

    // MIUI标识(小米)
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_NOTCH = "ro.miui.notch";
    // EMUI标识(华为)
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.version.emui";
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";
    //Flyme标识(魅族)
    private static final String KEY_FLYME_ID_FALG_KEY = "ro.build.display.id";
    private static final String KEY_FLYME_ID_FALG_VALUE_KEYWORD = "Flyme";
    private static final String KEY_FLYME_ICON_FALG = "persist.sys.use.flyme.icon";
    private static final String KEY_FLYME_SETUP_FALG = "ro.meizu.setupwizard.flyme";
    private static final String KEY_FLYME_PUBLISH_FALG = "ro.flyme.published";


    /**
     * 判断是否为小米手机系统MIUI
     * 1.MIUI 10判断失效
     *
     * @return 是否为小米手机系统MIUI
     */
    public static boolean isMIUI() {
        Properties prop = new Properties();
        boolean isMIUI;
        try {
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        isMIUI = prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        return isMIUI;
    }


    /**
     * 判断是否为华为手机系统EMUI
     *
     * @return 是否为华为手机系统EMUI
     */
    public static boolean isEMUI() {
        Properties prop = new Properties();
        boolean isEMUI;
        try {
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        isEMUI = prop.getProperty(KEY_EMUI_VERSION_CODE, null) != null
                || prop.getProperty(KEY_EMUI_API_LEVEL, null) != null
                || prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null;
        return isEMUI;
    }


    /**
     * 判断是否为魅族手机系统Flyme
     *
     * @return 是否为魅族手机系统Flyme
     */
    public static boolean isFlyme() {
        return getMeizuFlymeOSFlag().toLowerCase().contains("flyme");
    }


    public static String getMeizuFlymeOSFlag() {
        return getSystemProperty("ro.build.display.id", "");
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


    /**
     * @return 是否为小米的Notch手机(刘海屏)
     */
    public static boolean isMIUINotch() {
        boolean hasNotch = false;
        try {
            Class<?> systemProperties = Class.forName("android.os.SystemProperties");
            Method getInt = systemProperties.getMethod("getInt", String.class, int.class);
            if ((int) getInt.invoke(systemProperties, KEY_MIUI_NOTCH, 0) == 1) hasNotch = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            return hasNotch;
        }
    }

    /**
     * @return 是否为华为的刘海屏手机
     */
    public static boolean isHuaWeiNotch() {
        boolean hasNotch = false;
        try {
            Class<?> hwNotchSizeUtil = Class.forName("com.huawei.android.util.HwNotchSizeUtil");
            Method hasNotchScreen = hwNotchSizeUtil.getMethod("hasNotchScreen");
            hasNotch = (boolean) hasNotchScreen.invoke(hwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            return hasNotch;
        }
    }


    public static boolean isOPPONotch() {
        boolean hasNotch = false;
        try {
            Class<?> heterromorphism = Class.forName("com.oppo.feature.screen.heteromorphism");
            Method hasStystemFeature = heterromorphism.getMethod("hasSystemFeature");
            hasNotch = (boolean) hasStystemFeature.invoke(heterromorphism);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return hasNotch;
        }
    }

    /**
     * @return 是否为Vivo的刘海屏手机
     */
    public static boolean isVivoNotch() {
        boolean hasNotch = false;
        try {
            Class<?> ftFeature = Class.forName("android.util.FtFeature");
            Method isFeatureSupport = ftFeature.getMethod("isFeatureSupport", int.class);
            hasNotch = (boolean) isFeatureSupport.invoke(ftFeature, 0x00000020);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return hasNotch;
        }
    }

    /**
     * @return 判断是否为Notch手机
     */
    public static boolean hasNotch() {
        return isMIUINotch() || isHuaWeiNotch() || isOPPONotch() || isVivoNotch();
    }


}


