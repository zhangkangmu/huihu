package com.huihu.commonlib.task;

import org.xutils.x;

/*************************************************
 * Copyright (C), 2017-2021, Bailun Tech. Co., Ltd.
 * File name: BaseActivity.java
 * Author: kingpang   Version: v1.0   Date: 2017/10/19
 * Description: null
 *              1、...
 * Other: null
 * Function List: null
 * History:
 *          kingpang 2017/10/19 v1.0 Create 
 *************************************************/
public class SimpleTask {
    public static void runInBackground(Runnable runnable)
    {
        x.task().run(runnable);
    }

    public static void post2MainUIThread(Runnable runnable)
    {
        x.task().post(runnable);
    }
}
