package com.huihu.module_notification.newreply.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_notification.newreply.entity.GetNewReplySubcode;
import com.huihu.module_notification.newreply.entity.NewReplyPageBean;
import com.huihu.module_notification.newreply.interfaces.NewReplyMVP;


public class ImpNewReplyModel implements NewReplyMVP.IModel {
    private static final int PAGE_SIZE = 20;
    private final NewReplyMVP.IPresenter iPresenter;

    public ImpNewReplyModel(NewReplyMVP.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    @Override
    public void p2vmGetNewReplyListNet(int messageStatus, final int pageIndex, long questionId) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Ideas.GetNewReply
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("messageStatus", String.valueOf(messageStatus));
        param.addQuery("pageIndex", String.valueOf(pageIndex));
        param.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        param.addQuery("questionId", String.valueOf(questionId));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetNewReplySubcode.Success:
                        NewReplyPageBean pageBean = new Gson().fromJson(returnModel.getBodyMessage(), NewReplyPageBean.class);
                        iPresenter.m2pGetDataSuccess(pageBean, pageIndex);
                        break;
                    case GetNewReplySubcode.ParameterError:
                        break;
                    case GetNewReplySubcode.BusinessError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iPresenter.m2lNetFail();
                if (pageIndex == 1) iPresenter.m2pGetDataFail();
            }

            @Override
            public void onCompleted() {
                iPresenter.m2pGetDataComplete();
            }
        });
    }
}
