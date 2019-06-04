package com.huihu.module_notification.circleinvitation.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_notification.circleinvitation.entity.CircleInvitationPageBean;
import com.huihu.module_notification.circleinvitation.entity.CircleInvitationType;
import com.huihu.module_notification.circleinvitation.entity.PutCircleManager;
import com.huihu.module_notification.circleinvitation.entity.PutCircleMember;
import com.huihu.module_notification.circleinvitation.interfaces.CircleInvitationMVP;
import com.huihu.module_notification.intdef.GetUserNoticeByNoticeIdSubcode;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.uilib.def.NetDataBoolean;

public class ImpCircleInvitationModel implements CircleInvitationMVP.IModel {
    private static final int PAGE_SIZE = 20;

    private final CircleInvitationMVP.IPresenter iPresenter;

    public ImpCircleInvitationModel(CircleInvitationMVP.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void p2mGetCircleInvitationListDataNet(final int pageIndex) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.GetUserNoticeByNoticeIdOfPage
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("noticeId", String.valueOf(NoticeId.CIRCLE_INVITE));
        param.addQuery("pageIndex", String.valueOf(pageIndex));
        param.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetUserNoticeByNoticeIdSubcode.Success:
                        CircleInvitationPageBean pageBean = new Gson().fromJson(returnModel.getBodyMessage()
                                , CircleInvitationPageBean.class);
                        iPresenter.m2pGetDataSuccess(pageBean);
                        break;
                    case GetUserNoticeByNoticeIdSubcode.BusinessError:
                        break;
                    case GetUserNoticeByNoticeIdSubcode.ParameterError:
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

    @Override
    public void p2mPutJoinCircle(final CircleInvitationPageBean.CircleInvitationData data) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Circle.PutCircleMember
                , NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("circleId", String.valueOf(data.getInfo().getCircleId()));
        param.addQuery("state", String.valueOf(NetDataBoolean.NET_TRUE));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutCircleMember.Success:
                        data.getInfo().setChildType(CircleInvitationType.JoinedCircle);
                        iPresenter.v2pJoinCircleSuccess(data);
                        break;
                    case PutCircleMember.BusinessError:
                        break;
                    case PutCircleMember.ParameterError:
                        break;
                    case PutCircleMember.AlreadyJoined:
                        data.getInfo().setChildType(CircleInvitationType.JoinedCircle);
                        iPresenter.v2pJoinCircleSuccess(data);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPutIsAllowCircleManager(@NetDataBoolean final int isAllow, final CircleInvitationPageBean.CircleInvitationData data) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Circle.PutCircleManager
                , NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("circleId", String.valueOf(data.getInfo().getCircleId()));
        param.addQuery("operate", String.valueOf(isAllow));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutCircleManager.Success:
                        data.getInfo().setChildType(isAllow == NetDataBoolean.NET_TRUE
                                ? CircleInvitationType.AllowedManager : CircleInvitationType.RefusedManager);
                        iPresenter.m2pPutAllowManagerSuccess(data);
                        break;
                    case PutCircleManager.BusinessError:
                        break;
                    case PutCircleManager.ParameterError:
                        break;
                    case PutCircleManager.ManagerIsFull:
                        data.getInfo().setChildType(CircleInvitationType.ManagerFull);
                        iPresenter.m2pPutAllowManagerSuccess(data);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
