package com.huihu.module_mine.feedback.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_mine.feedback.entity.PostFeedbackBean;
import com.huihu.module_mine.feedback.entity.PostFeedbackSubcode;
import com.huihu.module_mine.feedback.feedbackinterface.IFeedbackModel;
import com.huihu.module_mine.feedback.feedbackinterface.IFeedbackPresenter;

public class ImpFeedbackModel implements IFeedbackModel {
    private final IFeedbackPresenter iFeedbackPresenter;

    public ImpFeedbackModel(IFeedbackPresenter iFeedbackPresenter) {
        this.iFeedbackPresenter = iFeedbackPresenter;
    }

    @Override
    public void p2mPostFeedback(PostFeedbackBean bean) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.User.PostAddFeedback
                , NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(bean));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PostFeedbackSubcode.Success:
                        iFeedbackPresenter.m2pPostFeedbackSuccess();
                        break;
                    case PostFeedbackSubcode.ParameterError:
                        break;
                    case PostFeedbackSubcode.BusinessError:
                        break;
                    case PostFeedbackSubcode.ThirdError:
                        break;
                    case PostFeedbackSubcode.UserNoLogin:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iFeedbackPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
