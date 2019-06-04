package com.huihu.module_notification.replyinvitation.model;

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
import com.huihu.module_notification.reply.entity.AnswerReadState;
import com.huihu.module_notification.replyinvitation.entity.PutAnswerInviteReadSubcode;
import com.huihu.module_notification.replyinvitation.entity.ReplyInvitationPageBean;
import com.huihu.module_notification.replyinvitation.replyinvitationinterface.IReplyInvitationModel;
import com.huihu.module_notification.replyinvitation.replyinvitationinterface.IReplyInvitationPresenter;
import com.huihu.uilib.def.NetDataBoolean;

public class ImpReplyInvitationModel implements IReplyInvitationModel {

    private static final int PAGE_SIZE = 20;

    private final IReplyInvitationPresenter iReplyInvitationPresenter;

    public ImpReplyInvitationModel(IReplyInvitationPresenter iReplyInvitationPresenter) {
        this.iReplyInvitationPresenter = iReplyInvitationPresenter;
    }

    @Override
    public void p2mGetReplyInvitationListNet(final int pageIndex) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.GetUserNoticeByNoticeIdOfPage
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("noticeId", String.valueOf(NoticeId.REPLY_INVITE));
        param.addQuery("pageIndex", String.valueOf(pageIndex));
        param.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        param.addQuery("uid", SPUtils.getInstance().getCurrentUid() + "");
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetUserNoticeByNoticeIdSubcode.Success:
                        ReplyInvitationPageBean pageBean = new Gson().fromJson(returnModel.getBodyMessage()
                                , ReplyInvitationPageBean.class);
                        iReplyInvitationPresenter.m2pGetListDataSuccess(pageBean);
                        break;
                    case GetUserNoticeByNoticeIdSubcode.ParameterError:
                        break;
                    case GetUserNoticeByNoticeIdSubcode.BusinessError:
                        break;
                }


            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iReplyInvitationPresenter.m2pNetFail();
                if (pageIndex == 1) iReplyInvitationPresenter.m2pGetDataFail();
            }

            @Override
            public void onCompleted() {
                iReplyInvitationPresenter.m2pGetDataComplete();
            }
        });
    }

    @Override
    public void p2mPutInviteRead(final ReplyInvitationPageBean.ReplyInvitationBean bean) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.PutAnswerInviteReadState
                , NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("questionId", bean.getAboutId());
        param.addQuery("uid",String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutAnswerInviteReadSubcode.Success:
                        AnswerReadState state = new Gson().fromJson(returnModel.getBodyMessage(), AnswerReadState.class);
                        bean.setMessageStatus(NetDataBoolean.NET_TRUE);
                        iReplyInvitationPresenter.m2pReadSuccess(bean, state);
                        break;
                    case PutAnswerInviteReadSubcode.ParameterError:
                        break;
                    case PutAnswerInviteReadSubcode.BusinessError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iReplyInvitationPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
