package com.huihu.module_mine.editsignature.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.editsignature.editsignatureinterface.IEditSignatureModel;
import com.huihu.module_mine.editsignature.editsignatureinterface.IEditSignaturePresenter;

public class ImpEditSignatureModel implements IEditSignatureModel {
    private static final String TAG = "ImpEditSignatureModel";
    private final IEditSignaturePresenter iEditSignaturePresenter;

    private String mUid = SPUtils.getInstance().getCurrentUid()+ "";

    public ImpEditSignatureModel(IEditSignaturePresenter iEditSignaturePresenter) {
        this.iEditSignaturePresenter = iEditSignaturePresenter;
    }

    public void p2mPutUpdateUserSignature(String signature) {
        mUid = SPUtils.getInstance().getCurrentUid() + "";
        putUpdateUserSignature(signature, mUid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iEditSignaturePresenter.m2pPutUpdateUserSignatureSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                Log.d(TAG, "p2mPutUpdateUserSignature error: " + errCode);
                Log.d(TAG, strErrMsg);
                iEditSignaturePresenter.m2pPutUpdateUserSignatureError();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void putUpdateUserSignature(String signature, String uid, HuihuCallBack callBack) {
        Log.d(TAG, "putUpdateUserSignature");
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.User.putUpdateUserSignature, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("signature", signature + "");
        httpRequestParam.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(httpRequestParam, callBack);
    }
}
