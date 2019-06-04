package com.huihu.commonlib.db;

import com.huihu.commonlib.utils.SPUtils;

import java.util.List;

/**
 * Package com.fxchat.localdb
 * Description:本地数据库的配置文件
 * author Albert
 * date 2016/3/31 10:19
 * version V1.0
 */
public class LocalDBHelper {

    public static void init() {
        //TODO
    }

    public static String getCommonDBName() {
        return ConfigLocalDB.HUIHU_DB;
    }

    public static String getUserDBName() {
        return ConfigLocalDB.HUIHU_USER_DB_PREFIX + SPUtils.getInstance().getCurrentUid() + ".db";
    }

    public static String buildSqlRangeStr(String[] str) {
        StringBuffer inStr = new StringBuffer("(");
        for (int i = 0; i < str.length; i++) {
            String divide = "";
            if (i != 0) {
                divide = ",";
            }
            inStr.append(divide);
            inStr.append("\"");
            inStr.append(str[i]);
            inStr.append("\"");
        }
        inStr.append(")");
        return inStr.toString();
    }

    public static String buildSqlRangeStr(List<String> str) {
        String[] arraylist = str.toArray(new String[0]);
        return buildSqlRangeStr(arraylist);
    }

    private static String formatSqlStrParam(String param){
        return "\"" + param + "\"";
    }

    public static String formatWhereSql(String whereSql,Object[]params){
        if (params == null || params.length == 0){
            return whereSql;
        }
        String changedWhereSql = whereSql;
        for (int i=0;i<params.length;i++){
            String formatStr="";
            if (params[i] instanceof Number){
                if (params[i] instanceof Float || params[i] instanceof Double){
                    formatStr = "\"f\"";
                }else {
                    formatStr = "\"%d\"";
                }
            }else{
                formatStr = "\"%s\"";
            }
            changedWhereSql = changedWhereSql.replaceFirst("\\?",formatStr);
        }
        return String.format(changedWhereSql,params);
    }
}
