package com.huihu.module_mine.mine.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.mine.entity.GetUserDetailSubCode;
import com.huihu.module_mine.mine.entity.UserInfo;
import com.huihu.module_mine.mine.mineinterface.IMineModel;
import com.huihu.module_mine.mine.mineinterface.IMinePresenter;

public class ImpMineModel implements IMineModel {
    private final IMinePresenter iMinePresenter;

    public ImpMineModel(IMinePresenter iMinePresenter) {
        this.iMinePresenter = iMinePresenter;
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
                        iMinePresenter.m2pReturnUserInfo(userInfo);
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
                iMinePresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iMinePresenter.m2pNetComplete();
            }
        });
    }
    private void getUserInfo(long optId, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.GetUserHomePageDetails, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("optUid", String.valueOf(optId));
        httpRequestParam.addQuery("userId", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);

    }
}
