package com.huihu.module_mine.followsandfollowed.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.followsandfollowed.entity.FollowsBean;
import com.huihu.module_mine.followsandfollowed.entity.GetFollowsAndFansSubCode;
import com.huihu.module_mine.followsandfollowed.followsandfollowedinterface.IFollowsAndFollowedModel;
import com.huihu.module_mine.followsandfollowed.followsandfollowedinterface.IFollowsAndFollowedPresenter;

import java.util.List;

public class ImpFollowsAndFollowedModel implements IFollowsAndFollowedModel {
    private int PAGE_SIZE=20;
    private final IFollowsAndFollowedPresenter iFollowsAndFollowedPresenter;

    public ImpFollowsAndFollowedModel(IFollowsAndFollowedPresenter iFollowsAndFollowedPresenter) {
        this.iFollowsAndFollowedPresenter = iFollowsAndFollowedPresenter;
    }

    @Override
    public void p2mGetFollowAndFansList(long userId, int type, final int followId) {
        getUserInfo(userId,type,followId,new HuihuCallBack(){
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetFollowsAndFansSubCode
                            .Success:
                        FollowsBean followsBean = new Gson().fromJson(returnModel.getBodyMessage(), FollowsBean.class);
                        List<FollowsBean.PageDatasBean> pageDatas = followsBean.getPageDatas();
                        iFollowsAndFollowedPresenter.m2pReturnFollowAndFansList(pageDatas,followId);
                        break;
                    case GetFollowsAndFansSubCode.BusinessError:
                    case GetFollowsAndFansSubCode.ParameterError:
                    case GetFollowsAndFansSubCode.ThirdLoginError:
                        default:
                            break;

                }
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
    public void p2mControlFollow(FollowsBean.PageDatasBean bean) {

    }


    private void getUserInfo(long userId, int type,int followId, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Follow.GetFansOrFollowList, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("followId",String.valueOf(followId));
        httpRequestParam.addQuery("pageSize",String.valueOf(PAGE_SIZE));
        httpRequestParam.addQuery("type", String.valueOf(type));
        httpRequestParam.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        httpRequestParam.addQuery("userId", String.valueOf(userId));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

    private void putGiveFollows(FollowsBean.PageDatasBean bean,HuihuCallBack mCallBack){
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Follow.PutGiveFollows, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("aboutId", String.valueOf(bean.getUserId()));
        httpRequestParam.addQuery("followType", "1");
        if (bean.getFollowId()==0) {
            httpRequestParam.addQuery("state", "0");
        }else {
            httpRequestParam.addQuery("state","1");
        }
        httpRequestParam.addQuery("uid", ""+SPUtils.getInstance().getCurrentUid());
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
}
