package com.huihu.uilib.util;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;


public class DensityUtil {

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static Point getScreenMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        return new Point(w_screen, h_screen);
    }

    public static int dip2px(Context mContext, float dipFloat) {
        float f = mContext.getResources().getDisplayMetrics().density;
        return (int) (dipFloat * f + 0.5F);
    }

    public static int px2dip(Context mContext, float pxFloat) {
        float f = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxFloat / f + 0.5F);
    }

    public static int sp2px(Context mContext, float spFloat) {
        float f = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spFloat * f + 0.5F);
    }

    public static int px2sp(Context mContext, float pxFloat) {
        float f = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxFloat / f + 0.5F);
    }
}
