package com.huihu.commonlib.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.os.Build.VERSION_CODES.KITKAT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static android.os.Build.VERSION_CODES.N;

/**
 * 类说明：修改状态栏
 * 1.4.4以上系统提供了设置状态栏透明的方法
 * 2.5.0以上系统提供了修改状态栏背景的方法
 * 3.6.0以上系统提供了修改状态栏图标颜色的方法（支持深色和浅色两种）
 * 4.6.0以下个别厂商提供了修改状态栏图标颜色的方法（小米，OPPO，魅族）
 * 5.小米厂商会和系统自带的改变图标颜色的方法有冲突（需要2种都写上）
 *
 * @author yh
 * @date 2018/8/9
 */
public class StatusBarUtil {

    //目前只支持2种图标
    /**
     * 浅色图标和文字（白色）
     */
    public static final int STATUS_BAR_LIGHT_ICON = 0;
    /**
     * 深色图标和文字（黑色）
     */
    public static final int STATUS_BAR_DART_ICON = 1;
    /**
     * 默认图标颜色
     */
    private static final int DEFAULT_STATUS_BAR_ICON = STATUS_BAR_LIGHT_ICON;
    /**
     * 默认状态栏颜色App的颜色
     */
    private static final int DEFAULT_STATUS_BAR_COLOR = Color.parseColor("#2ea9df");


    /**
     * 设置状态栏透明
     *
     * @param activity 需要设置的Activity
     */
    @Deprecated
    @TargetApi(KITKAT)
    public static void setStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 设置状态栏透明，图标为白色
     * 1.状态栏如果是透明，默认肯定是要内容布局延伸到状态栏的
     *
     * @param activity Activity
     */
    public static void setTransparentWithLightIcon(Activity activity) {
        setStatusBarBgColor(activity, Color.TRANSPARENT);
    }

    /**
     * 设置状态栏透明，图标为黑色
     * 注意: 如果不能设置黑色图标,StatusBar背景默认为App主色调的背景颜色，图标默认为白色
     *
     * @param activity Activity
     */
    public static void setTransparentWithBlackIcon(Activity activity) {
        if (isSupportBlackIcon()) {
            setStatusBarBgColorAndIcon(activity, Color.TRANSPARENT, STATUS_BAR_DART_ICON);
        } else {
            setStatusBarBgColor(activity, DEFAULT_STATUS_BAR_COLOR);
        }
    }

    /**
     * Set StatusBar Color
     *
     * @param activity 需要设置的Activity
     * @param colorInt 背景资源
     */
    @TargetApi(LOLLIPOP)
    public static void setStatusBarBgColor(Activity activity, @ColorInt int colorInt) {
        setStatusBarBgColorAndIcon(activity, colorInt, DEFAULT_STATUS_BAR_ICON);
    }

    /**
     * Set StatusBar Color
     *
     * @param activity 需要设置的Activity
     * @param colorRes 背景资源
     */
    @TargetApi(LOLLIPOP)
    public static void setStatusBarBgColorRes(Activity activity, @ColorRes int colorRes) {
        setStatusBarBgColorAndIcon(activity, ContextCompat.getColor(activity, colorRes), DEFAULT_STATUS_BAR_ICON);
    }

    /**
     * 设置状态栏的背景以及背景图标颜色
     *
     * @param activity 需要设置的Activity
     * @param colorRes 背景资源
     * @param BgIcon   图片模式（STATUS_BAR_LIGHT_ICON，STATUS_BAR_DARK_ICON）
     */
    public static void setStatusBarBgColorResAndIcon(Activity activity, @ColorRes int colorRes, int BgIcon) {
        setStatusBarBgColorAndIcon(activity, ContextCompat.getColor(activity, colorRes), BgIcon);
    }

    /**
     * 设置状态栏的背景以及背景图标颜色
     *
     * @param activity 需要设置的Activity
     * @param colorRes 背景资源
     * @param BgIcon   图片模式（STATUS_BAR_LIGHT_ICON，STATUS_BAR_DARK_ICON）
     */
    @TargetApi(LOLLIPOP)
    public static void setStatusBarBgColorAndIcon(Activity activity, @ColorInt int colorRes, int BgIcon) {
        if (Build.VERSION.SDK_INT < LOLLIPOP) {
            return;
        }
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (BgIcon == STATUS_BAR_DART_ICON) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏图标黑色显示效果
            } else if (SystemProducerUtil.isMIUI()) {
                //各个手机产商的适配
                adapterMIUI6(window);//这里默认5.1以上的设备都是MIUI6以上
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                return;
            } else if (SystemProducerUtil.isFlyme()) {
                try {
                    adapterFlyMe(window, false);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ManufacturerInfoUtils.isOppo()) {
                adapterOPPO(window, false);
            }
        } else {
            int vis = window.getDecorView().getSystemUiVisibility();
            vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            window.getDecorView().setSystemUiVisibility(vis);
        }
        adaptedVersionN(window);
        window.setStatusBarColor(colorRes);
    }

    public static boolean isDark(int color) {
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        return red * .299 + green * .587 + blue * .114 < 150;
    }

    /**
     * 适配AndroidN状态栏
     * 1.修复Android N 以上状态栏透明时，有一层朦胧问题
     *
     * @param window 需要改变的window
     */
    @TargetApi(N)
    private static void adaptedVersionN(Window window) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            return;
        }
        try {
            Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
            Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
            field.setAccessible(true);
            field.setInt(window.getDecorView(), Color.TRANSPARENT);  //改为透明
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置MIUI6系统的状态栏图标
     * google的actionbar存在bug，不支持沉浸代码。
     *
     * @param window 需要改变的window
     */
    private static void adapterMIUI6(Window window) {
        Class clazz = window.getClass();
        try {
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_TRANSPARENT");
            int tranceFlag = field.getInt(layoutParams);
            field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);

            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            //extraFlagField.invoke(window, tranceFlag, tranceFlag); //只需要状态栏透明
            extraFlagField.invoke(window, tranceFlag | darkModeFlag, tranceFlag | darkModeFlag); //状态栏透明且黑色字体
            //extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 改变魅族的状态栏字体为黑色，要求FlyMe4以上
     */
    private static void adapterFlyMe(Window window, boolean isLightStatusBar) throws Exception {
        WindowManager.LayoutParams lp = window.getAttributes();
        Class<?> instance = Class.forName("android.view.WindowManager$LayoutParams");
        int value = instance.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON").getInt(lp);
        Field meizuFlags = instance.getDeclaredField("meizuFlags");
        meizuFlags.setAccessible(true);
        int origin = meizuFlags.getInt(lp);
        if (isLightStatusBar) {
            meizuFlags.set(lp, origin | value);
        } else {
            meizuFlags.set(lp, -value | origin);
        }
        window.setAttributes(lp);
    }

    /**
     * 适配OPPO手机5.1以上
     */
    static final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010;

    private static void adapterOPPO(Window window, boolean lightMode) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int vis = window.getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lightMode) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (lightMode) {
                vis |= SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            } else {
                vis &= ~SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            }
        }
        window.getDecorView().setSystemUiVisibility(vis);
    }

    /**
     * 设置页面最外层布局 FitsSystemWindows 属性
     *
     * @param activity
     * @param value
     */
    public static void setFitsSystemWindows(Activity activity, boolean value) {
        ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(android.R.id.content);
        View parentView = contentFrameLayout.getChildAt(0);
        if (parentView != null && Build.VERSION.SDK_INT >= 15) {
            parentView.setFitsSystemWindows(value);
        }
    }

    /**
     * 利用反射获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 判断是否支持修改黑色图标
     *
     * @return 是否支持修改黑色图标
     */
    public static boolean isSupportBlackIcon() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M || SystemProducerUtil.isMIUI()
                || SystemProducerUtil.isFlyme() || ManufacturerInfoUtils.isOppo()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 初始化填充一个状态栏
     * @param context Context
     * @param flStatus 需要填充的flStatus高度
     */
    public static void initCustomStatusBar(Context context, FrameLayout flStatus) {
        //模拟一个状态栏的高度
        ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) flStatus.getLayoutParams();
        p.height = StatusBarUtil.getStatusBarHeight(context);
        flStatus.requestLayout();
    }

}
