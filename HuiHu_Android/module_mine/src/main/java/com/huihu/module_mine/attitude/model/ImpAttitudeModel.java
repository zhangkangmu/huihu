package com.huihu.module_mine.attitude.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.attitude.attitudeinterface.IAttitudeModel;
import com.huihu.module_mine.attitude.attitudeinterface.IAttitudePresenter;
import com.huihu.module_mine.attitude.entity.AttitudeInfo;
import com.huihu.module_mine.attitude.entity.GetAttitudeInfoSubCode;
import com.huihu.module_mine.comment.entity.GetCommentInfoSubCode;

public class ImpAttitudeModel implements IAttitudeModel {
    private final IAttitudePresenter iAttitudePresenter;
    private final int PAGE_SIZE=20;

    public ImpAttitudeModel(IAttitudePresenter iAttitudePresenter) {
        this.iAttitudePresenter = iAttitudePresenter;
    }

    @Override
    public void p2mGetAttitudeList(long createTime,long fid, final boolean isMore) {
            getAttitudeList(createTime,fid, new HuihuCallBack() {
                @Override
                public void onSuccess(ReturnModel returnModel) {
                    switch (returnModel.getSubCode()){
                        case GetAttitudeInfoSubCode
                                .Success:
                            AttitudeInfo attitudeInfo = new Gson().fromJson(returnModel.getBodyMessage(), AttitudeInfo.class);
                            iAttitudePresenter.m2pReturnAttitudeList(attitudeInfo.getPageDatas(),isMore);
                            break;
                        case GetAttitudeInfoSubCode.ParameterError:
                        case GetAttitudeInfoSubCode.BusinessError:
                        case GetAttitudeInfoSubCode.NoUser:
                        case GetAttitudeInfoSubCode.UnLogin:
                            default:
                                break;

                    }

                }

                @Override
                public void onError(int errCode, String strErrMsg) {
                    iAttitudePresenter.m2pNetFail();
                }

                @Override
                public void onCompleted() {
                    iAttitudePresenter.m2pNetComplete();
                }
            });
    }
    private void getAttitudeList(long time,long fid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.GetViewpointListByUid, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("createTime", "" +time);
        httpRequestParam.addQuery("loginUid", "" + SPUtils.getInstance().getCurrentUid());
        httpRequestParam.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        httpRequestParam.addQuery("uid",""+fid);
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);

    }
}
