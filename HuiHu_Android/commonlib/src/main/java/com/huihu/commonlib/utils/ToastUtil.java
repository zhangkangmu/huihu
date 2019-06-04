package com.huihu.commonlib.utils;


import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.huihu.commonlib.base.BaseApplication;


public class ToastUtil {
    private static Toast toast;//这里用这个防止点击时不停的重复创建新的toast
    private static boolean sIsCenter = false;

    /**
     * 显示Toast
     *
     * @param message 内容
     */
    public static void show(String message, int duration) {
        try {
            if (ValidateTool.getInstance().checkNull(message) || BaseApplication.getApplication() == null) {
                return;
            }
            if (toast != null) {
                toast.cancel();
            }
            toast = ToastCompat.makeText(BaseApplication.getApplication(), null, duration);
            toast.setText(message.trim());
            setIsCenter();
            toast.show();
        } catch (Exception e) {//Only the original thread that created a view hierarchy can touch its views.
            e.printStackTrace();
        }
    }

    // 设置是否中间显示
    private static void setIsCenter() {
        if (sIsCenter) {
            toast.setGravity(Gravity.CENTER, 0, 0);
            sIsCenter = false;
        }
    }

    /**
     * 中间显示Toast
     *
     * @param message 内容
     */
    public static void showCenter(String message) {
        sIsCenter = true;
        show(message, Toast.LENGTH_SHORT);
    }

    public static void showCenter(String message, int duration){
        sIsCenter = true;
        show(message,duration);
    }

    /**
     * 中间显示Toast
     *
     * @param message 内容
     */
    public static void showCenter(int message) {
        sIsCenter = true;
        show(message);
    }

    public static void reset() {
        toast = null;
    }

    /**
     * 显示Toast
     *
     * @param message 内容
     */
    public static void show(@StringRes int message) {
        if (BaseApplication.getApplication() == null) {
            return;
        }
        show(BaseApplication.getApplication().getString(message), Toast.LENGTH_SHORT);
    }

    public static void show(String message){
        show(message, Toast.LENGTH_SHORT);
    }

    /**
     * 显示Toast
     *
     * @param context 上下文
     * @param view    要显示的Toast的V
     */
    public static void showMyFaceToast(Context context, View view) {
        if (view == null || context == null) {
            return;
        }
        Toast toast = new ToastCompat(context);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }


    /**
     * 账户信息大全定制
     *
     * @param context context
     */
    public static void showAccountToast(Context context, String content, int yOffset) {
        if (toast != null) {
            toast.cancel();
        }
        toast = ToastCompat.makeText(BaseApplication.getApplication(), null, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, yOffset);
        toast.setText(content);
        toast.show();
    }
}
