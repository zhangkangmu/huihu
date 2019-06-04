package com.huihu.module_home.writeanswer.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_home.question.entity.PostDraftModel;
import com.huihu.module_home.question.entity.PostDraftSubcode;
import com.huihu.module_home.writeanswer.entity.PostAnswerModel;
import com.huihu.module_home.writeanswer.entity.PostAnswerSubcode;
import com.huihu.module_home.writeanswer.writeanswerinterface.IWriteAnswerModel;
import com.huihu.module_home.writeanswer.writeanswerinterface.IWriteAnswerPresenter;

public class ImpWriteAnswerModel implements IWriteAnswerModel {
    private final IWriteAnswerPresenter iWriteAnswerPresenter;

    public ImpWriteAnswerModel(IWriteAnswerPresenter iWriteAnswerPresenter) {
        this.iWriteAnswerPresenter = iWriteAnswerPresenter;
    }

    @Override
    public void p2mPostAnswer(PostAnswerModel model) {
        postAnswer(model, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case PostAnswerSubcode.success:
                        iWriteAnswerPresenter.m2pPostAnswerSuccess(model.getMessage());
                        break;
                    case PostAnswerSubcode.paramError:
                    case PostAnswerSubcode.businessException:
                    case PostAnswerSubcode.banned:
                    case PostAnswerSubcode.obtained:
                    case PostAnswerSubcode.hasAnswered:
                        iWriteAnswerPresenter.m2pPostAnswerFail(model.getMessage());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iWriteAnswerPresenter.m2pPostAnswerFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPostDraft(PostDraftModel model) {
        postDraft(model, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case PostDraftSubcode.success:
                        iWriteAnswerPresenter.m2pPostDiscussSuccess(model.getMessage());
                        break;
                    case PostDraftSubcode.paramError:
                    case PostDraftSubcode.businessException:
                        iWriteAnswerPresenter.m2pPostDiscussFail(model.getMessage());
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iWriteAnswerPresenter.m2pPostDiscussFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    private void postAnswer(PostAnswerModel model, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Ideas.postAnswer, NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(model));
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    private void postDraft(PostDraftModel model, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Draft.postDraft, NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(model));
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
