package com.huihu.uilib.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

/**
 * create by wangjing on 2019/4/12 0012
 * description:
 */
public class MarkUtil {
    public static void markPerson(Context context, String url, ImageView view){
        if (!TextUtils.isEmpty(url)) {
            view.setVisibility(View.VISIBLE);
            ImgTools.showImageView(context, url, view);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
