package com.huihu.uilib.util;


import java.text.DecimalFormat;

/**
 * create by wangjing on 2019/4/1 0001
 * description:
 */
public class CountUtil {

    private static final double K = 1000d;
    private static final double W = 10000d;

    private static final char THOUSAND = 'k';
    private static final char TEN_THOUSAND = 'w';
    private static DecimalFormat format = new DecimalFormat("#.#");

    public static String toHuihuCount(long count){
        if (count < 0) return "";
        String stringCount;
        if (count < K){
            stringCount = String.valueOf(count);
        } else if (count < W){
            double number = count / K;
            stringCount = format.format(number) + THOUSAND;
        } else {
            double number = count / W;
            stringCount = format.format(number) + TEN_THOUSAND;
        }
        return stringCount;
    }
}
