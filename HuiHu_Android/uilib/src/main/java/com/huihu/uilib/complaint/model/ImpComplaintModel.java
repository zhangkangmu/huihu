package com.huihu.uilib.complaint.model;

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
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.complaint.complaintinterface.IComplaintModel;
import com.huihu.uilib.complaint.complaintinterface.IComplaintPresenter;
import com.huihu.uilib.complaint.entity.ComplaintBean;
import com.huihu.uilib.complaint.entity.ReportTag;
import com.huihu.uilib.util.imageupload.ImageUpLoadCallback;
import com.huihu.uilib.util.imageupload.ImageUpload;

import java.util.List;

public class ImpComplaintModel implements IComplaintModel {
    private static final String TAG = "ImpComplaintModel";
    private final IComplaintPresenter iComplaintPresenter;

    public ImpComplaintModel(IComplaintPresenter iComplaintPresenter) {
        this.iComplaintPresenter = iComplaintPresenter;
    }

    @Override
    public void p2mPostImage(final List<AlbumImageBean> list) {
        ImageUpload.getInstance().uploadPicture(list, new ImageUpLoadCallback() {
            @Override
            public void onSuccess() {
                iComplaintPresenter.m2pPostImageSuccess(list);
            }

            @Override
            public void onFail(String errorMsg) {
                iComplaintPresenter.m2pPostImageFail(errorMsg);
            }
        });
    }


    @Override
    public void p2mPostReport(ComplaintBean bean) {
        long uid = SPUtils.getInstance().getCurrentUid();
        bean.setUid(uid);
        postReport(bean, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iComplaintPresenter.m2pPostReportSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iComplaintPresenter.m2pPostReportError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mGetReportTag() {
        getReportTag(new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, returnModel.getBodyMessage());
                List<ReportTag> tags = new Gson().fromJson(returnModel.getBodyMessage(), new TypeToken<List<ReportTag>>(){}.getType());
                iComplaintPresenter.m2pGetReportTagSuccess(tags);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                Log.d(TAG, strErrMsg);
                iComplaintPresenter.m2pGetReportTagError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }


    //api/getReportTag  获取举报标签
    public void getReportTag(HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Report.GetReportTag, NetworkTransmissionDefine.HttpMethod.GET);
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    //举报
    public void postReport(ComplaintBean bean, HuihuCallBack callBack) {
        Log.d(TAG, new Gson().toJson(bean));
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Report.PostReport, NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(bean));
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
