package com.huihu.module_mine.attentionquestion.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_mine.attentionquestion.attentionquestioninterface.IAttentionQuestionModel;
import com.huihu.module_mine.attentionquestion.attentionquestioninterface.IAttentionQuestionPresenter;
import com.huihu.module_mine.attentionquestion.entity.AttentionQuestionInfo;
import com.huihu.module_mine.attentionquestion.entity.GetAttentionQuestionListSubcode;

import java.util.List;

public class ImpAttentionQuestionModel implements IAttentionQuestionModel {
    private final IAttentionQuestionPresenter iAttentionQuestionPresenter;

    public ImpAttentionQuestionModel(IAttentionQuestionPresenter iAttentionQuestionPresenter) {
        this.iAttentionQuestionPresenter = iAttentionQuestionPresenter;
    }

    @Override
    public void p2mGetAttentionQuestionList(int followId, int pageIndex, int pageSize, int uid, final boolean isMore) {
        getAttentionCircleList(followId, pageIndex, pageSize, uid, new HuihuCallBack() {

            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()) {
                    case GetAttentionQuestionListSubcode.success:
                        AttentionQuestionInfo attentionQuestionInfo = new Gson().fromJson(returnModel.getBodyMessage(), AttentionQuestionInfo.class);
                        List<AttentionQuestionInfo.PageDatasBean> pageDatas = attentionQuestionInfo.getPageDatas();
                        iAttentionQuestionPresenter.m2pReturnAttentionQuestionList(pageDatas,isMore);
                        break;
                    case GetAttentionQuestionListSubcode.unLogin:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iAttentionQuestionPresenter.m2pNetFailed();
            }

            @Override
            public void onCompleted() {
                iAttentionQuestionPresenter.m2pNetComplete();
            }
        });
    }

    private void getAttentionCircleList(int followId, int pageIndex, int pageSize, int uid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Follow.getFollowIdeas, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("followId", "" + followId);
        httpRequestParam.addQuery("pageIndex", "" + pageIndex);
        httpRequestParam.addQuery("pageSize", "" + pageSize);
        httpRequestParam.addQuery("uid", "" + uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
}
