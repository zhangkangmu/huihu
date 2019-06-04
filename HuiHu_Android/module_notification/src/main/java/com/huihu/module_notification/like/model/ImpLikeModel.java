package com.huihu.module_notification.like.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_notification.like.entity.GetLikeMeListSubcode;
import com.huihu.module_notification.like.entity.LikeMePageBean;
import com.huihu.module_notification.like.likeinterface.ILikeModel;
import com.huihu.module_notification.like.likeinterface.ILikePresenter;

public class ImpLikeModel implements ILikeModel {

    private static final int PAGE_SIZE = 20;

    private final ILikePresenter iLikePresenter;

    public ImpLikeModel(ILikePresenter iLikePresenter) {
        this.iLikePresenter = iLikePresenter;
    }

    @Override
    public void p2mGetLikeMeListNet(long time, final boolean isMore) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.ViewPoint.GetOtherLikeMeList
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("createTime", String.valueOf(time));
        param.addQuery("loginUid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        param.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                String subcode = returnModel.getSubCode();
                switch (subcode){
                    case GetLikeMeListSubcode.Success:
                        LikeMePageBean bean = new Gson().fromJson(returnModel.getBodyMessage(), LikeMePageBean.class);
                        iLikePresenter.m2pGetDataSuccess(bean, isMore);
                        break;
                    case GetLikeMeListSubcode.ParameterError:
                        break;
                    case GetLikeMeListSubcode.BusinessError:
                        break;
                    case GetLikeMeListSubcode.UserNoLogin:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iLikePresenter.m2pNetFail();
                iLikePresenter.m2pGetDataFail();
            }

            @Override
            public void onCompleted() {
                iLikePresenter.m2pGetDataComplete();
            }
        });
    }
}
