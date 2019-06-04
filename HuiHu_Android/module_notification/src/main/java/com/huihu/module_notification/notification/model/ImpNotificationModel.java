package com.huihu.module_notification.notification.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_notification.notification.entity.DeleteSessionAnswerSubcode;
import com.huihu.module_notification.notification.entity.GetMessageSessionListSubcode;
import com.huihu.module_notification.notification.entity.MessageSession;
import com.huihu.module_notification.notification.entity.NoticeSessionBean;
import com.huihu.module_notification.notification.entity.PutAllReadSubcode;
import com.huihu.module_notification.notification.notificationinterface.INotificationModel;
import com.huihu.module_notification.notification.notificationinterface.INotificationPresenter;

public class ImpNotificationModel implements INotificationModel {
    private final INotificationPresenter iNotificationPresenter;

    public ImpNotificationModel(INotificationPresenter iNotificationPresenter) {
        this.iNotificationPresenter = iNotificationPresenter;
    }

    @Override
    public void p2mGetMessageSessionListNet() {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.GetMessageList
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("uid", SPUtils.getInstance().getCurrentUid() + "");
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetMessageSessionListSubcode.Success:
                        NoticeSessionBean bean = new Gson().fromJson(returnModel.getBodyMessage(),
                                NoticeSessionBean.class);
                        iNotificationPresenter.m2pGetListDataSuccess(bean);
                        break;
                    case GetMessageSessionListSubcode.ParameterError:
                        break;
                    case GetMessageSessionListSubcode.BusinessError:
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iNotificationPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPutAllReadNet() {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.PutAllNoticeRead
                , NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutAllReadSubcode.Success:
                        iNotificationPresenter.m2pAllReadSuccess();
                        break;
                    case PutAllReadSubcode.BusinessError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iNotificationPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mDeleteSessionNet(final MessageSession session) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.DeleteSessionAnswer
                , NetworkTransmissionDefine.HttpMethod.DELETE);
        param.addQuery("ideaId", session.getAboutId());
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case DeleteSessionAnswerSubcode.Success:
                        iNotificationPresenter.m2pDeleteSessionSuccess(session);
                        break;
                    case DeleteSessionAnswerSubcode.ParameterError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iNotificationPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
