package com.huihu.module_home.questionandanswerlist.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_home.questionandanswerlist.entity.AnswerModel;
import com.huihu.module_home.questionandanswerlist.entity.GetAnswerInfoParamModel;
import com.huihu.module_home.questionandanswerlist.entity.GetAnswerListSubcode;
import com.huihu.module_home.questionandanswerlist.entity.GetQuestionInfoSubcode;
import com.huihu.module_home.questionandanswerlist.entity.QuestionDetailModel;
import com.huihu.module_home.questionandanswerlist.questionandanswerlistinterface.IQuestionAndAnswerListModel;
import com.huihu.module_home.questionandanswerlist.questionandanswerlistinterface.IQuestionAndAnswerListPresenter;
import com.huihu.uilib.def.FollowType;
import com.huihu.uilib.subcode.PutGiveFollowsSubcode;

public class ImpQuestionAndAnswerListModel implements IQuestionAndAnswerListModel {
    private final IQuestionAndAnswerListPresenter iQuestionAndAnswerListPresenter;


    public ImpQuestionAndAnswerListModel(IQuestionAndAnswerListPresenter iQuestionAndAnswerListPresenter) {
        this.iQuestionAndAnswerListPresenter = iQuestionAndAnswerListPresenter;
    }

    @Override
    public void p2mGetQuestionInfo(long ideaId) {
        getQuestionInfo(ideaId, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case GetQuestionInfoSubcode.success:
                        QuestionDetailModel detailModel = new Gson().fromJson(model.getBodyMessage(), QuestionDetailModel.class);
                        iQuestionAndAnswerListPresenter.m2pGetQuestionInfoSuccess(detailModel);
                        break;
                    case GetQuestionInfoSubcode.paramError:
                    case GetQuestionInfoSubcode.businessException:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {

            }

            @Override
            public void onCompleted() {
                iQuestionAndAnswerListPresenter.m2pRefreshFinish();
            }
        });
    }

    @Override
    public void p2mGetAnswerList(GetAnswerInfoParamModel model) {
        getGetAnswerList(model, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case GetAnswerListSubcode.success:
                        AnswerModel answerModel = new Gson().fromJson(model.getBodyMessage(), AnswerModel.class);
                        iQuestionAndAnswerListPresenter.m2pGetAnswerInfoSuccess(answerModel);
                        break;
                    case GetAnswerListSubcode.businessException:
                    case GetAnswerListSubcode.paramError:
                        iQuestionAndAnswerListPresenter.m2pGetAnswerInfoFail(model.getMessage());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iQuestionAndAnswerListPresenter.m2pGetAnswerInfoFail(strErrMsg);
            }

            @Override
            public void onCompleted() {
                iQuestionAndAnswerListPresenter.m2pRefreshFinish();
            }
        });
    }

    @Override
    public void p2mPutGiveFollows(long id, final int type, int state, int uid) {
        putGiveFollows(id, type, state, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case PutGiveFollowsSubcode.Success:
                        if (type == FollowType.HUMAN) {
                            iQuestionAndAnswerListPresenter.m2pPutGivePersonSuccess();
                        } else if (type == FollowType.CONTENT) {
                            iQuestionAndAnswerListPresenter.m2pPutGiveFollowsSuccess();
                        }
                        break;
                    case PutGiveFollowsSubcode.UserNoLogin:
                    case PutGiveFollowsSubcode.ParameterError:
                    case PutGiveFollowsSubcode.BusinessError:
                    default:
                        iQuestionAndAnswerListPresenter.m2pPutGiveFollowsFail(model.getMessage());
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iQuestionAndAnswerListPresenter.m2pPutGiveFollowsFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPostCollection(long id, int uid) {
//        postCollection(id, uid, new HuihuCallBack() {
//            @Override
//            public void onSuccess(ReturnModel model) {
//                switch (model.getSubCode()) {
//                    case PostCollectionSubcode.success:
//                        iQuestionAndAnswerListPresenter.m2pPostCollectionSuccess(model.getMessage());
//                        break;
//                    case PostCollectionSubcode.paramError:
//                    case PostCollectionSubcode.businessException:
//                        iQuestionAndAnswerListPresenter.m2pPostCollectionFail(model.getMessage());
//                        break;
//                }
//
//            }
//
//            @Override
//            public void onError(int errCode, String strErrMsg) {
//                iQuestionAndAnswerListPresenter.m2pPostCollectionFail(strErrMsg);
//            }
//
//            @Override
//            public void onCompleted() {
//
//            }
//        });
    }

    private void postCollection(long id, int uid, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Collection.postCollection, NetworkTransmissionDefine.HttpMethod.POST);
        param.addQuery("ideaId", id + "");
        param.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    private void putGiveFollows(long id, int type, int state, int uid, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Follow.PutGiveFollows, NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("aboutId", id + "");
        param.addQuery("followType", type + "");
        param.addQuery("state", state + "");
        param.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    private void getGetAnswerList(GetAnswerInfoParamModel model, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Ideas.getAnswerList, NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("orderBy", model.getOrderBy() + "");
        param.addQuery("uid", model.getUid() + "");
        param.addQuery("questionId", model.getQuestionId() + "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    private void getQuestionInfo(long ideaId, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Ideas.getQuestionInfo, NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("ideaId", ideaId + "");
        param.addQuery("uid", SPUtils.getInstance().getCurrentUid() + "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
