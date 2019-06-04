package com.huihu.module_mine.others.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.others.entity.GetUserDetailSubCode;
import com.huihu.module_mine.others.entity.UserInfo;
import com.huihu.module_mine.others.othersinterface.IOthersModel;
import com.huihu.module_mine.others.othersinterface.IOthersPresenter;

public class ImpOthersModel implements IOthersModel {
    private final IOthersPresenter iOthersPresenter;

    public ImpOthersModel(IOthersPresenter iOthersPresenter) {
        this.iOthersPresenter = iOthersPresenter;
    }

    @Override
    public void p2mGetUseInfo(long otherid) {
        getUserInfo(otherid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetUserDetailSubCode
                            .Success:
                        UserInfo userInfo = new Gson().fromJson(returnModel.getBodyMessage(), UserInfo.class);
                        iOthersPresenter.m2pReturnUserInfo(userInfo);
                        break;
                    case GetUserDetailSubCode.BusinessError:
                    case GetUserDetailSubCode.ParameterError:
                    case GetUserDetailSubCode.ThirdError:
                    case GetUserDetailSubCode.UnLogin:
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
    private void getUserInfo(long optId, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.GetUserHomePageDetails, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("optUid",String.valueOf(SPUtils.getInstance().getCurrentUid()) );
        httpRequestParam.addQuery("userId", String.valueOf(optId));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);

    }

}
