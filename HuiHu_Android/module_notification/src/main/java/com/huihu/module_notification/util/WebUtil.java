package com.huihu.module_notification.util;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.huihu.commonlib.base.BaseApplication;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;

/**
 * create by wangjing on 2019/4/11 0011
 * description:
 */
public class WebUtil {

    public static void startWeb(String url){
        if (TextUtils.isEmpty(url)) {
            ToastUtil.show(R.string.module_notification_url_error);
            return;
        }
        Intent intent= new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        BaseApplication.getApplication().startActivity(intent);
    }
}
