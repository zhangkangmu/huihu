package com.huihu.module_home.questiondraft.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_home.questiondraft.entity.GetDraftListSubcode;
import com.huihu.module_home.questiondraft.questiondraftinterface.IQuestionDraftModel;
import com.huihu.module_home.questiondraft.questiondraftinterface.IQuestionDraftPresenter;

public class ImpQuestionDraftModel implements IQuestionDraftModel {
    private final IQuestionDraftPresenter iQuestionDraftPresenter;

    public ImpQuestionDraftModel(IQuestionDraftPresenter iQuestionDraftPresenter) {
        this.iQuestionDraftPresenter = iQuestionDraftPresenter;
    }

    @Override
    public void p2mGetDraftList(int stamp, int pagesize, int question, int uid) {
        getDraftList(stamp, pagesize, question, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case GetDraftListSubcode.success:
                        GetDraftModel draftModel = new Gson().fromJson(model.getBodyMessage(), GetDraftModel.class);
                        iQuestionDraftPresenter.m2pGetDraftListSuccess(draftModel);
                        break;
                    case GetDraftListSubcode.paramError:
                    case GetDraftListSubcode.businessException:
                        iQuestionDraftPresenter.m2pGetDraftListFail(model.getMessage());
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iQuestionDraftPresenter.m2pGetDraftListFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    private void getDraftList(int stamp, int pagesize, int question, int uid, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Draft.getDraftList, NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("lastTime", stamp + "");
        param.addQuery("pageSize", pagesize + "");
        param.addQuery("type", question + "");
        param.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
