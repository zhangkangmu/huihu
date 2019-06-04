package com.huihu.module_mine.classificationattention.model;


import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_mine.classificationattention.classificationattentioninterface.IClassificationAttentionModel;
import com.huihu.module_mine.classificationattention.classificationattentioninterface.IClassificationAttentionPresenter;
import com.huihu.module_mine.classificationattention.entity.ClassificationAttentionInfo;
import com.huihu.module_mine.classificationattention.entity.GetClassificationListSubcode;

import java.util.List;

public class ImpClassificationAttentionModel implements IClassificationAttentionModel {
    private final IClassificationAttentionPresenter iClassificationAttentionPresenter;

    public ImpClassificationAttentionModel(IClassificationAttentionPresenter iClassificationAttentionPresenter) {
        this.iClassificationAttentionPresenter = iClassificationAttentionPresenter;
    }

    @Override
    public void p2mGetClassicationAttentionList(int followId, int pageIndex, int pageSize, int uid, final boolean isMore) {
        getAttentionCircleList(followId,pageIndex,pageSize,uid,new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetClassificationListSubcode.success:
                        ClassificationAttentionInfo circleAttentionInfo = new Gson().fromJson(returnModel.getBodyMessage(), ClassificationAttentionInfo.class);
                        List<ClassificationAttentionInfo.PageDatasBean> pageDatas = circleAttentionInfo.getPageDatas();
                        iClassificationAttentionPresenter.m2pGetClassicationAttentionList(pageDatas,isMore);
                        break;
                    case GetClassificationListSubcode.unLogin:
                        iClassificationAttentionPresenter.m2pClassificationAttention();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iClassificationAttentionPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iClassificationAttentionPresenter.m2pNetComplete();
            }
        });
    }

    private void getAttentionCircleList(int followId, int pageIndex, int pageSize ,int uid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Follow.getFollowCategorys, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("followId", "" + followId);
        httpRequestParam.addQuery("pageIndex", "" + pageIndex);
        httpRequestParam.addQuery("pageSize", ""+pageSize);
        httpRequestParam.addQuery("uid", ""+uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);

    }
}
