package com.huihu.commonlib.db;

import android.os.Environment;

import java.io.File;

/**
 * Package com.fxchat.localdb
 * Description:本地数据库的配置文件
 * author Albert
 * date 2016/3/31 10:19
 * version V1.0
 */
public class ConfigLocalDB {

    //全局DB
    public final static String HUIHU_DB = "huihu.db";
    //个人DB前缀
    public final static String HUIHU_USER_DB_PREFIX = "huihu";

    //全局DB版本
    private final static int COM_DB_VERSION = 1;
    //个人DB版本
    private final static int USER_DB_VERSION = 1;

    //汇乎数据库文件目录
    public final static String HUIHU_DATABASE_DIR = Environment.getExternalStorageDirectory() + File.separator + "huihudata" + File.separator + "database" + File.separator;
}
