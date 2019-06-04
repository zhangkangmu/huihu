package com.huihu.module_mine.attentioncircle.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_mine.attentioncircle.attentioncircleinterface.IAttentionCircleModel;
import com.huihu.module_mine.attentioncircle.attentioncircleinterface.IAttentionCirclePresenter;
import com.huihu.module_mine.attentioncircle.entity.CircleAttentionInfo;
import com.huihu.module_mine.attentioncircle.entity.GetAttentionCircleListSubcode;

import java.util.List;

public class ImpAttentionCircleModel implements IAttentionCircleModel {
    private final IAttentionCirclePresenter iAttentionCirclePresenter;

    public ImpAttentionCircleModel(IAttentionCirclePresenter iAttentionCirclePresenter) {
        this.iAttentionCirclePresenter = iAttentionCirclePresenter;
    }

    @Override
    public void p2mGetAttentionCircleList(int followId, int pageIndex, int pageSize , int uid , final boolean isMore) {
        getAttentionCircleList(followId,pageIndex, pageSize ,uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()) {
                    //请求成功
                    case GetAttentionCircleListSubcode.success:
                        CircleAttentionInfo circleAttentionInfo = new Gson().fromJson(returnModel.getBodyMessage(), CircleAttentionInfo.class);
                        List<CircleAttentionInfo.PageDatasBean> pageDatas = circleAttentionInfo.getPageDatas();
                        iAttentionCirclePresenter.m2pGetAttentionCircle(pageDatas,isMore);
                        break;
                    //用户未登录
                    case GetAttentionCircleListSubcode.unLogin:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iAttentionCirclePresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iAttentionCirclePresenter.m2pNetComplete();
            }
        });
    }

    private void getAttentionCircleList(int followId, int pageIndex, int pageSize ,int uid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Follow.getFollowCircles, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("followId", "" + followId);
        httpRequestParam.addQuery("pageIndex", "" + pageIndex);
        httpRequestParam.addQuery("pageSize", ""+pageSize);
        httpRequestParam.addQuery("uid", ""+uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

//    @Override
//    public void p2mGetCircleList(int followId, int pageIndex, int pageSize, int uid, final boolean isMore) {
//        getMoreAttentionCircleList(followId,pageIndex,pageSize,uid,new HuihuCallBack(){
//
//            @Override
//            public void onSuccess(ReturnModel returnModel) {
//                Log.d("zyh","onSuccess-more");
//                CircleAttentionInfo info = new Gson().fromJson(returnModel.getBodyMessage(), CircleAttentionInfo.class);
//                List<CircleAttentionInfo.PageDatasBean> datas = info.getPageDatas();
//                iAttentionCirclePresenter.m2pReturnCircleList(datas, isMore);
//            }
//
//            @Override
//            public void onError(int errCode, String strErrMsg) {
//                iAttentionCirclePresenter.m2pNetFail();
//            }
//
//            @Override
//            public void onCompleted() {
//                iAttentionCirclePresenter.m2pNetComplete();
//            }
//        });
//    }

//    private void getMoreAttentionCircleList(int followId, int pageIndex, int pageSize, int uid, HuihuCallBack mCallBack) {
//        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Follow.getFollowCircles, NetworkTransmissionDefine.HttpMethod.GET);
//        httpRequestParam.addQuery("followId", "" + followId);
//        httpRequestParam.addQuery("pageIndex", "" + pageIndex);
//        httpRequestParam.addQuery("pageSize", ""+pageSize);
//        httpRequestParam.addQuery("uid", ""+uid);
//        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
//    }

}
