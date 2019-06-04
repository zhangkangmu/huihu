package com.huihu.module_circle.newcirclerecommend.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;
import com.huihu.module_circle.newcirclerecommend.entity.NewCircleRecommendInfo;
import com.huihu.module_circle.newcirclerecommend.entity.NewCircleRecommendListSubcode;
import com.huihu.module_circle.newcirclerecommend.newcirclerecommendinterface.INewCircleRecommendModel;
import com.huihu.module_circle.newcirclerecommend.newcirclerecommendinterface.INewCircleRecommendPresenter;

import java.util.List;

public class ImpNewCircleRecommendModel implements INewCircleRecommendModel {
    private final INewCircleRecommendPresenter iNewCircleRecommendPresenter;

    public ImpNewCircleRecommendModel(INewCircleRecommendPresenter iNewCircleRecommendPresenter) {
        this.iNewCircleRecommendPresenter = iNewCircleRecommendPresenter;
    }

    @Override
    public void p2mRequestCircleRecommendList(int pageIndex, int pageSize, int uid,final boolean isMore) {
        getCircleRecommendList(pageIndex,pageSize,uid,new HuihuCallBack(){

            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case NewCircleRecommendListSubcode.SUCCESS:
                        NewCircleRecommendInfo info = new Gson().fromJson(returnModel.getBodyMessage(), NewCircleRecommendInfo.class);

//                        List<NewCircleRecommendInfo.PageDatasBean> datas = info.getPageDatas();
                        iNewCircleRecommendPresenter.m2pReturnNewCircleRecommendList(info,isMore);
                        break;
                    case NewCircleRecommendListSubcode.BUSINESSERROR:
                    case NewCircleRecommendListSubcode.PARAMETERERROR:
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                Log.d("zyh",strErrMsg);
                iNewCircleRecommendPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iNewCircleRecommendPresenter.m2pNetComplete();
            }
        });
    }

    private void getCircleRecommendList(int pageIndex, int pageSize, int uid, HuihuCallBack huihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.getRecemendCircle, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("pageIndex", "" + pageIndex);
        httpRequestParam.addQuery("pageSize", "" + pageSize);
        httpRequestParam.addQuery("uid", ""+uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, huihuCallBack);
    }
}
