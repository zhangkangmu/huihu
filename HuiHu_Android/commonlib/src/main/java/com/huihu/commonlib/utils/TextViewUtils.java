package com.huihu.commonlib.utils;

import android.text.TextPaint;
import android.widget.TextView;

/**
 * create by wangjing on 2019/3/20 0020
 * description:
 */
public class TextViewUtils {

    public static void setTextFakeBold(TextView textView) {
        TextPaint paint = textView.getPaint();
        paint.setFakeBoldText(true);
    }

    public static void cancelTextFakeBold(TextView textView) {
        TextPaint paint = textView.getPaint();
        paint.setFakeBoldText(false);
    }
}
