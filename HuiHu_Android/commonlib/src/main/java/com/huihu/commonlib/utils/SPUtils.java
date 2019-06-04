package com.huihu.commonlib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.huihu.commonlib.base.BaseApplication;

import java.util.Set;
import java.util.UUID;

/**
 * create by ouyangjianfeng on 2019/3/15
 * description: sp工具类
 */
public class SPUtils {

    private static SPUtils instance;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor spEditor;
    private static Context context;

    private static final String HUIHU = "huihu";

    public static SPUtils getInstance() {
        if (context == null) {
            context = BaseApplication.getApplication();
        }

        if (instance == null) {
            instance = new SPUtils();
        }
        return instance;
    }

    private SPUtils() {
        sp = context.getSharedPreferences(HUIHU, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void removeKey(String key) {
        spEditor.remove(key);
        spEditor.commit();
    }

    public void saveString(String key, String value) {
        spEditor.putString(key, value);
        spEditor.commit();
    }

    public void saveInt(String key, int value) {
        spEditor.putInt(key, value);
        spEditor.commit();
    }

    public void saveLong(String key, long value) {
        spEditor.putLong(key, value);
        spEditor.commit();
    }

    public void saveFloat(String key, float value) {
        spEditor.putFloat(key, value);
        spEditor.commit();
    }

    public void saveBoolean(String key, boolean value) {
        spEditor.putBoolean(key, value);
        spEditor.commit();
    }

    public void saveStringSet(String key, Set<String> values) {
        spEditor.putStringSet(key, values);
        spEditor.commit();
    }

    public int getIntegerValue(String key) {
        return sp.getInt(key, 0);
    }

    public boolean getBooleanValue(String key) {
        return sp.getBoolean(key, false);
    }

    public String getStringValue(String key) {
        return sp.getString(key, "");
    }

    public long getLongValue(String key) {
        return sp.getLong(key, 0);
    }


    public void saveCurrentUid(int uid) {
        saveInt(Constant.CURRENT_UID, uid);
    }

    public int getCurrentUid() {
        return sp.getInt(Constant.CURRENT_UID, Constant.DEFAULT_UID);
    }

    /**
     * 获取汇聊在这个设备的唯一号，用于用户踢人，因为有些用户会把手机唯一识别号关掉，获取不到,所以采用这个方式
     */

    private String mDeviceId;

    public String getDeviceId() {
        if (mDeviceId == null) {
            String deviceId = sp.getString("hhDeviceId", "");
            if (ValidateTool.getInstance().checkNull(deviceId)) {
                deviceId = UUID.randomUUID().toString();
                saveString("hhDeviceId", deviceId);
            }
            mDeviceId = deviceId;
        }
        return mDeviceId;
    }
}
