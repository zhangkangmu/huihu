package com.huihu.module_mine.editname.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.editname.editnameinterface.IEditNameModel;
import com.huihu.module_mine.editname.editnameinterface.IEditNamePresenter;

public class ImpEditNameModel implements IEditNameModel {
    private static final String TAG = "ImpEditNameModel";
    private final IEditNamePresenter iEditNamePresenter;

    private String mUid;

    public ImpEditNameModel(IEditNamePresenter iEditNamePresenter) {
        this.iEditNamePresenter = iEditNamePresenter;
    }

    public void p2mGetNickIsUsed(final String name) {
        getNickIsUsed(name, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                //                {
                //                    "code": 0,
                //                        "subCode": 12030701,
                //                        "message": "此昵称已被使用",
                //                        "bodyMessage": "true"
                //                }
                if (new Gson().fromJson(returnModel.getBodyMessage(), boolean.class)) {
                    iEditNamePresenter.m2pNickHasUsed();
                } else {
                    p2mPutUpdateOtherDetail(name, -1, null);
                }

            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                Log.d(TAG, "p2mGetNickIsUsed error: " + errCode);
                Log.d(TAG, strErrMsg);
                iEditNamePresenter.m2pPutUpdateNameError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void getNickIsUsed(String name, HuihuCallBack callBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.User.getNickIsUsed, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("nickName", name);
        HuihuHttpUtils.httpRequest(httpRequestParam, callBack);
    }


    public void p2mPutUpdateOtherDetail(String name, int sex, String industry) {
        mUid = SPUtils.getInstance().getCurrentUid() + "";
        putUpdateOtherDetail(name, sex, industry, mUid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, "p2mPutUpdateOtherDetaile seccess");
                iEditNamePresenter.m2pPutUpdateNameSucess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                Log.d(TAG, "p2mPutUpdateOtherDetaile error: " + errCode);
                Log.d(TAG, strErrMsg);
                iEditNamePresenter.m2pPutUpdateNameError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });

    }

    // update name, sex, industry
    public void putUpdateOtherDetail(String nickName, int sex, String occupationValue, String uid, HuihuCallBack callBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.User.putUpdateOtherDetail, NetworkTransmissionDefine.HttpMethod.PUT);
        if (nickName != null) {
            httpRequestParam.addQuery("nickName", nickName + "");
        }
        if (sex == 0 || sex == 1) {
            httpRequestParam.addQuery("sex", sex + "");
        }
        if (occupationValue != null) {
            httpRequestParam.addQuery("occupationValue", occupationValue + "");
        }
        httpRequestParam.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(httpRequestParam, callBack);
    }
}
