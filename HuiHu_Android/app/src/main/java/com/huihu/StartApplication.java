package com.huihu;

import android.util.Log;

import com.huihu.commonlib.base.BaseApplication;
import com.huihu.uilib.util.TimeFormatUtils;
import com.wangjing.module_push.Push;

public class StartApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("ouyang","StartApplication------主框架APP启动");
        TimeFormatUtils.initTime();
        Push.init(this, true);
    }
}
