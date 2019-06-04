package com.huihu.commonlib.net;

import com.bailun.bl_commonlib.callback.CommLibCallback;
import com.bailun.bl_commonlib.net.NetworkTransmissionUtils;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.google.gson.Gson;

/**
 * create by wangjing on 2019/3/19 0019
 * description:
 */
public class HuihuHttpUtils {

    private static final int CODE_SUCCESS = 0;

    public static void httpRequest(final HttpRequestParam httpRequestParam, final HuihuCallBack callBack) {
        NetworkTransmissionUtils.getInstance().httpRequest(httpRequestParam, new CommLibCallback<String>() {
            @Override
            public void onSuccess(String s) {
                if (callBack != null) {
                    ReturnModel model = new Gson().fromJson(s, ReturnModel.class);
                    if (model.getCode() == CODE_SUCCESS) {
                        model.setSubCode(model.getSubCode().substring(1));
                        callBack.onSuccess(model);
                    } else {
                        callBack.onError(model.getCode(), model.getMessage());
                    }
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                if (callBack != null) {
                    callBack.onError(errCode, strErrMsg);
                }
            }

            @Override
            public void onCompleted() {
                if (callBack != null) {
                    callBack.onCompleted();
                }
            }
        });
    }
}
