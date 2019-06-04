package com.huihu.module_notification.util;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;

/**
 * create by wangjing on 2019/4/11 0011
 * description:
 */
public class ReadUtil {

    public static final String Success = "0C12C00";
    public static final String BusinessError = "0C12C02";

    public static void readNotice(int noticeId){
        HuihuHttpUtils.httpRequest(getParam(noticeId), new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
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

    public static void readNotice(int noticeId, HuihuCallBack callBack){
        HuihuHttpUtils.httpRequest(getParam(noticeId), callBack);
    }

    private static HttpRequestParam getParam(int noticeId){
            HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.PutUserNoticeRead
                    , NetworkTransmissionDefine.HttpMethod.PUT);
            param.addQuery("noticeId", String.valueOf(noticeId));
            param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
            return param;
    }
}
