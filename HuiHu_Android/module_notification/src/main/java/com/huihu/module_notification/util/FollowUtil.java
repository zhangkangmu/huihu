package com.huihu.module_notification.util;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.uilib.def.FollowType;

/**
 * create by wangjing on 2019/4/12 0012
 * description:
 */
public class FollowUtil {
    public static void follow(long aboutId, @FollowType int followType, int state, long uid, HuihuCallBack callBack){
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Follow.PutGiveFollows
                , NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("aboutId", String.valueOf(aboutId));
        param.addQuery("followType", String.valueOf(followType));
        param.addQuery("state", String.valueOf(state));
        param.addQuery("uid", String.valueOf(uid));
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
