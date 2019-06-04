package com.huihu.module_notification.comment.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_notification.comment.commentinterface.ICommentModel;
import com.huihu.module_notification.comment.commentinterface.ICommentPresenter;
import com.huihu.module_notification.comment.entity.CommentPageBean;
import com.huihu.module_notification.comment.entity.GetOtherCommentMeListSubcode;

public class ImpCommentModel implements ICommentModel {
    private final ICommentPresenter iCommentPresenter;

    private static final int PAGE_SIZE = 20;

    public ImpCommentModel(ICommentPresenter iCommentPresenter) {
        this.iCommentPresenter = iCommentPresenter;
    }

    @Override
    public void p2mGetOtherCommentListNet(long time, final boolean isMore) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Comments.OtherCommentList
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("commentTime", String.valueOf(time));
        param.addQuery("loginUid", SPUtils.getInstance().getCurrentUid() + "");
        param.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetOtherCommentMeListSubcode.Success:
                        CommentPageBean bean = new Gson().fromJson(returnModel.getBodyMessage(), CommentPageBean.class);
                        iCommentPresenter.m2pGetOtherCommentListSuccess(bean, isMore);
                        break;
                    case GetOtherCommentMeListSubcode.ParameterError:
                        break;
                    case GetOtherCommentMeListSubcode.BusinessError:
                        break;
                    case GetOtherCommentMeListSubcode.NoUser:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentPresenter.m2pNetFail();
                iCommentPresenter.m2pGetDataFail();
            }

            @Override
            public void onCompleted() {
                iCommentPresenter.m2pNetComplete();
            }
        });
    }
}
