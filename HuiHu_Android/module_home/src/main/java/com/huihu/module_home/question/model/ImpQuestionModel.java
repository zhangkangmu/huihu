package com.huihu.module_home.question.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_home.question.entity.PostDiscussModel;
import com.huihu.module_home.question.entity.PostDiscussSubcode;
import com.huihu.module_home.question.entity.PostDraftModel;
import com.huihu.module_home.question.entity.PostDraftSubcode;
import com.huihu.module_home.question.questioninterface.IQuestionModel;
import com.huihu.module_home.question.questioninterface.IQuestionPresenter;

public class ImpQuestionModel implements IQuestionModel {
    private final IQuestionPresenter iQuestionPresenter;

    public ImpQuestionModel(IQuestionPresenter iQuestionPresenter) {
        this.iQuestionPresenter = iQuestionPresenter;
    }

    @Override
    public void p2mPostDraft(PostDraftModel model) {
        postDraft(model, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case PostDraftSubcode.success:
                        iQuestionPresenter.m2pPostDraftSuccess();
                        break;
                    case PostDraftSubcode.paramError:
                    case PostDraftSubcode.businessException:
                        iQuestionPresenter.m2pPostDraftFail();
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iQuestionPresenter.m2pPostDraftFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mSaveToLocal() {
        //TODO
    }

    @Override
    public void p2mPostDiscuss(PostDiscussModel model) {
        postDiscuss(model, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case PostDiscussSubcode.success:
                        iQuestionPresenter.m2pPostDiscussSuccess(model.getMessage());
                        break;
                    case PostDiscussSubcode.paramError:
                    case PostDiscussSubcode.businessException:
                    case PostDiscussSubcode.banned:
                    case PostDiscussSubcode.frequently:
                    case PostDiscussSubcode.limited:
                        iQuestionPresenter.m2pPostDiscussFail(model.getMessage());
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iQuestionPresenter.m2pPostDiscussFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    private void postDraft(PostDraftModel model, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Draft.postDraft, NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(model));
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    private void postDiscuss(PostDiscussModel model, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Circle.postDiscuss, NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(model));
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
