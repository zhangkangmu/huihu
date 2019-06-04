package com.huihu.module_notification.systemnotification.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_notification.intdef.GetUserNoticeByNoticeIdSubcode;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.systemnotification.entity.SystemNoticePageBean;
import com.huihu.module_notification.systemnotification.systemnotificationinterface.ISystemNotificationModel;
import com.huihu.module_notification.systemnotification.systemnotificationinterface.ISystemNotificationPresenter;

public class ImpSystemNotificationModel implements ISystemNotificationModel {

    private static final int PAGE_SIZE = 20;

    private final ISystemNotificationPresenter iSystemNotificationPresenter;

    public ImpSystemNotificationModel(ISystemNotificationPresenter iSystemNotificationPresenter) {
        this.iSystemNotificationPresenter = iSystemNotificationPresenter;
    }

    @Override
    public void p2mGetSystemNoticeListNet(final int pageIndex) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.GetUserNoticeByNoticeIdOfPage
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("noticeId", String.valueOf(NoticeId.SYSTEM_NOTIFY));
        param.addQuery("pageIndex", String.valueOf(pageIndex));
        param.addQuery("pageSize", String.valueOf(20));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetUserNoticeByNoticeIdSubcode.Success:
                        SystemNoticePageBean bean = new Gson().fromJson(returnModel.getBodyMessage()
                                , SystemNoticePageBean.class);
                        iSystemNotificationPresenter.m2pGetListDataSuccess(bean);
                        break;
                    case GetUserNoticeByNoticeIdSubcode.ParameterError:
                        break;
                    case GetUserNoticeByNoticeIdSubcode.BusinessError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iSystemNotificationPresenter.m2pNetFail();
                if (pageIndex == 1) iSystemNotificationPresenter.m2pGetDataFail();
            }

            @Override
            public void onCompleted() {
                iSystemNotificationPresenter.m2pGetDataComplete();
            }
        });
    }
}
