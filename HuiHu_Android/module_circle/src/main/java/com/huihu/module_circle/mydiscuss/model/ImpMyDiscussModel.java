package com.huihu.module_circle.mydiscuss.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_circle.circlelist.entity.CircleListInfo;
import com.huihu.module_circle.mydiscuss.entity.MyDiscussInfo;
import com.huihu.module_circle.mydiscuss.entity.MyDiscussSubcode;
import com.huihu.module_circle.mydiscuss.mydiscussinterface.IMyDiscussModel;
import com.huihu.module_circle.mydiscuss.mydiscussinterface.IMyDiscussPresenter;

import java.util.List;

public class ImpMyDiscussModel implements IMyDiscussModel {
    private final IMyDiscussPresenter iMyDiscussPresenter;

    public ImpMyDiscussModel(IMyDiscussPresenter iMyDiscussPresenter) {
        this.iMyDiscussPresenter = iMyDiscussPresenter;
    }

    @Override
    public void p2mRequestMyDiscuss(long circleId, long lastTime, int pageSize, long uid, final boolean isMore) {
        getMyDiscuss(circleId,lastTime,pageSize,uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()) {
                    case MyDiscussSubcode.success:
                        MyDiscussInfo info = new Gson().fromJson(returnModel.getBodyMessage(), MyDiscussInfo.class);
                        List<MyDiscussInfo.PageDatasBean> datas = info.getPageDatas();
                        iMyDiscussPresenter.m2pReturnMyDiscuss(datas,isMore);
                        break;
                    case MyDiscussSubcode.parameterError:
                        break;
                    case MyDiscussSubcode.businessError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iMyDiscussPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iMyDiscussPresenter.m2pNetComplete();
            }
        });
    }

    private void getMyDiscuss(long circleId, long lastTime, int pageSize, long uid, HuihuCallBack huihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.getMyCircleDiscuss, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("circleId", "" + circleId);
        httpRequestParam.addQuery("lastTime", "" + lastTime);
        httpRequestParam.addQuery("pageSize", "" + pageSize);
        httpRequestParam.addQuery("uid", "" + uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, huihuCallBack);
    }
}
