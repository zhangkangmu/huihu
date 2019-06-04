package com.huihu.module_mine.authentication.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.authentication.authenticationinterface.IAuthenticationModel;
import com.huihu.module_mine.authentication.authenticationinterface.IAuthenticationPresenter;
import com.huihu.module_mine.authentication.entity.Authentication;
import com.huihu.module_mine.authentication.entity.GetAuthenticationInfoSubCode;

import java.util.List;

public class ImpAuthenticationModel implements IAuthenticationModel {
    private final IAuthenticationPresenter iAuthenticationPresenter;

    public ImpAuthenticationModel(IAuthenticationPresenter iAuthenticationPresenter) {
        this.iAuthenticationPresenter = iAuthenticationPresenter;
    }

    @Override
    public void v2pGetUserAuthList(long fid) {
        getUserAuthList(fid,new HuihuCallBack(){
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetAuthenticationInfoSubCode
                            .Success:
                        List<Authentication.UserAuthShowModelListBean> mList = new Gson().fromJson(returnModel.getBodyMessage(), new TypeToken<List<Authentication.UserAuthShowModelListBean>>() {
                        }.getType());
                        iAuthenticationPresenter.m2pReturnUserAuthList(mList);
                        break;
                    case GetAuthenticationInfoSubCode.BusinessError:
                    case GetAuthenticationInfoSubCode.UnLogin:
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

    private void getUserAuthList(long fid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.User.GetUserAuthList, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("userId",String.valueOf(fid));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
}
