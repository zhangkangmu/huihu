package com.huihu.uilib.util;

import android.annotation.SuppressLint;
import android.content.Context;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.huihu.commonlib.base.BaseApplication;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.uilib.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * create by wangjing on 2019/3/21 0021
 * description:
 */
public class TimeFormatUtils {

    private static final String TIME_DIFFERENCE_SERVER = "TIME_DIFFERENCE_SERVER";

    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm";

    private static final long SECOND = 1000;
    private static final long MINUTE = SECOND * 60;
    private static final long HOUR = MINUTE * 60;
    private static final long DAY = HOUR * 24;
    private static final long MONTH = DAY * 30;
    private static final long YEAR = DAY * 365;

    private static SimpleDateFormat normal = new SimpleDateFormat(TIME_FORMAT);

    public static SimpleDateFormat df1 = new SimpleDateFormat("yyyy/MM/dd HH:mm");//年月日时分

    private TimeFormatUtils() {

    }

    /**
     * 时间戳转时间
     */
    public static String long2Time(long timeStamp, SimpleDateFormat dateFormat) {
        try {
            Date date = new Date(timeStamp);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String toHuihuTime(long targetTime) {
        String time;
        Context context = BaseApplication.getApplication();
        long now = System.currentTimeMillis() + SPUtils.getInstance().getLongValue(TIME_DIFFERENCE_SERVER);
        long interval = now - targetTime;
        if (interval > 0) {
            long h = interval / HOUR;
            long d = interval / DAY;
            long m = interval / MONTH;
            long y = interval / YEAR;
            if (y > 0) {
                time = String.format(context.getString(R.string.uilib_before_few_year), y);
            } else if (m > 0) {
                time = String.format(context.getString(R.string.uilib_before_few_month), m);
            } else if (d > 0) {
                time = String.format(context.getString(R.string.uilib_before_few_day), d);
            } else if (h > 0) {
                time = String.format(context.getString(R.string.uilib_before_few_hour), h);
            } else {
                time = context.getString(R.string.uilib_in_one_hour);
            }
        } else {
            time = normal.format(new Date(targetTime));
        }
        return time;
    }

    public static String toNormalTime(long targetTime) {
        return normal.format(new Date(targetTime));
    }

    public static void initTime() {
        final long localTime = System.currentTimeMillis();
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Common.GetSystemTime
                , NetworkTransmissionDefine.HttpMethod.GET);
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                long serverTime = Long.valueOf(returnModel.getBodyMessage());
                long difference = serverTime - localTime;
                SPUtils.getInstance().saveLong(TIME_DIFFERENCE_SERVER, difference);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                ToastUtil.show(R.string.uilib_http_request_fail);
            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
