package com.huihu.module_notification.reply.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_notification.reply.entity.AnswerReadState;
import com.huihu.module_notification.reply.entity.GetAttentionReplyListSubcode;
import com.huihu.module_notification.reply.entity.GetMineReplyListSubcode;
import com.huihu.module_notification.reply.entity.PutAnswerReadStateSubcode;
import com.huihu.module_notification.reply.entity.ReplyBean;
import com.huihu.module_notification.reply.entity.ReplyPageBean;
import com.huihu.module_notification.reply.replyinterface.IReplyModel;
import com.huihu.module_notification.reply.replyinterface.IReplyPresenter;
import com.huihu.uilib.def.NetDataBoolean;

import java.util.List;

public class ImpReplyModel implements IReplyModel {
    private static final int PAGE_SIZE = 20;
    private final IReplyPresenter iReplyPresenter;

    public ImpReplyModel(IReplyPresenter iReplyPresenter) {
        this.iReplyPresenter = iReplyPresenter;
    }

    @Override
    public void m2pGetAttentionQuestionReplyList(long time, final boolean isMore) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.GetAttentionReplyList
                , NetworkTransmissionDefine.HttpMethod.GET);
        if (time > 0L) param.addQuery("lastTime", String.valueOf(time));
        param.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetAttentionReplyListSubcode.Success:
                        ReplyPageBean bean = new Gson().fromJson(returnModel.getBodyMessage()
                                , ReplyPageBean.class);
                        iReplyPresenter.m2pGetDataSuccessAttention(bean.getPageDatas(), isMore);
                        break;
                    case GetAttentionReplyListSubcode.ParameterError:
                        break;
                    case GetAttentionReplyListSubcode.BusinessError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iReplyPresenter.m2pNetFail();
                if (!isMore) iReplyPresenter.m2pGetDataFailAttention();
            }

            @Override
            public void onCompleted() {
                iReplyPresenter.m2pGetDataComplete();
            }
        });
    }

    @Override
    public void m2pGetMineQuestionReplyList(long time, final boolean isMore) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.GetMineReplyList
                , NetworkTransmissionDefine.HttpMethod.GET);
        if (time > 0L) param.addQuery("lastTime", String.valueOf(time));
        param.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetMineReplyListSubcode.Success:
                        ReplyPageBean bean = new Gson().fromJson(returnModel.getBodyMessage()
                                , ReplyPageBean.class);
                        iReplyPresenter.m2pGetDataSuccessMine(bean.getPageDatas(), isMore);
                        break;
                    case GetMineReplyListSubcode.ParameterError:
                        break;
                    case GetMineReplyListSubcode.BusinessError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iReplyPresenter.m2pNetFail();
                iReplyPresenter.m2pGetDataFailMine();
            }

            @Override
            public void onCompleted() {
                iReplyPresenter.m2pGetDataComplete();
            }
        });
    }

    @Override
    public void m2pPutAnswerReadState(final int type, final ReplyBean bean) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.PutAnswerReadState
                , NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("answerId", String.valueOf(bean.getIdeaId()));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutAnswerReadStateSubcode.Success:
                        bean.setMessageStatus(NetDataBoolean.NET_TRUE);
                        AnswerReadState state = new Gson().fromJson(returnModel.getBodyMessage()
                                , AnswerReadState.class);
                        iReplyPresenter.m2pPutReadStateSuccess(type, bean, state);
                        break;
                    case PutAnswerReadStateSubcode.ParameterError:
                        break;
                    case PutAnswerReadStateSubcode.BusinessError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iReplyPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
