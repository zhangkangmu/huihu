package com.huihu.module_notification.systemmessage.model;

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
import com.huihu.module_notification.systemmessage.entity.SystemMessagePageBean;
import com.huihu.module_notification.systemmessage.interfaces.SystemMessageMVP;

public class ImpSystemMessageModel implements SystemMessageMVP.IModel {

    private static final int PAGE_SIZE = 20;
    private final SystemMessageMVP.IPresenter iPresenter;

    public ImpSystemMessageModel(SystemMessageMVP.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void p2mGetSystemMessageListNet(final int pageIndex) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.GetUserNoticeByNoticeIdOfPage
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("noticeId", String.valueOf(NoticeId.SYSTEM_MESSAGE));
        param.addQuery("pageIndex", String.valueOf(pageIndex));
        param.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetUserNoticeByNoticeIdSubcode.Success:
                        SystemMessagePageBean pageBean = new Gson().fromJson(returnModel.getBodyMessage()
                                , SystemMessagePageBean.class);
                        iPresenter.m2pGetListDataSuccess(pageBean);
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
                iPresenter.m2pNetFail();
                if (pageIndex == 1) iPresenter.m2pGetDataFail();
            }

            @Override
            public void onCompleted() {
                iPresenter.m2pGetDataComplete();
            }
        });
    }
}
