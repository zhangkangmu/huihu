package com.huihu.module_circle.circlelist.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.circlelist.circlelistinterface.ICircleListModel;
import com.huihu.module_circle.circlelist.circlelistinterface.ICircleListPresenter;
import com.huihu.module_circle.circlelist.entity.CircleListInfo;
import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;

public class ImpCircleListModel implements ICircleListModel {
    private final ICircleListPresenter iCircleListPresenter;

    public ImpCircleListModel(ICircleListPresenter iCircleListPresenter) {
        this.iCircleListPresenter = iCircleListPresenter;
    }

    @Override
    public void p2mRequestCircleMessage(long circleId, long uid) {
        getCircleMessage(circleId, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                CircleListInfo info = new Gson().fromJson(returnModel.getBodyMessage(), CircleListInfo.class);
                iCircleListPresenter.m2pReturnCircleList(info);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
            }

            @Override
            public void onCompleted() {
            }
        });
    }

    @Override
    public void p2mJoinCircle(final CircleListInfo mInfo, final boolean isAbort) {
        joinCircle(mInfo,isAbort,new HuihuCallBack(){
            int type=mInfo.getMemberType();
            @Override
            public void onSuccess(ReturnModel returnModel) {
                if (mInfo.getMemberType() == 0) {
                    mInfo.setMemberType(1);
                }else if (isAbort){
                    mInfo.setMemberType(0);
                }
                iCircleListPresenter.p2mReturnJoinSucces(mInfo);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                Log.d("zyh",strErrMsg);
            }

            @Override
            public void onCompleted() {
            }
        });
    }

    //加入圈子
    private void joinCircle(CircleListInfo mInfo,boolean isAbort, HuihuCallBack huihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.putCircleMember, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("circleId", "" + mInfo.getCircleId());
        if (mInfo.getMemberType()==0 && !isAbort){
            httpRequestParam.addQuery("state", "1");
        }else if (isAbort){
            httpRequestParam.addQuery("state", "0");
        }
        httpRequestParam.addQuery("uid",""+SPUtils.getInstance().getCurrentUid());
        HuihuHttpUtils.httpRequest(httpRequestParam, huihuCallBack);
    }

    //获取圈子信息
    private void getCircleMessage(long circleId, long uid, HuihuCallBack huihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.getCircleInfo, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("circleId", "" + circleId);
        httpRequestParam.addQuery("uid", "" + uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, huihuCallBack);
    }
}
