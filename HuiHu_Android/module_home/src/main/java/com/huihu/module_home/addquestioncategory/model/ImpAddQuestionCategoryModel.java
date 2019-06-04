package com.huihu.module_home.addquestioncategory.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_home.addquestioncategory.addquestioncategoryinterface.IAddQuestionCategoryModel;
import com.huihu.module_home.addquestioncategory.addquestioncategoryinterface.IAddQuestionCategoryPresenter;
import com.huihu.module_home.addquestioncategory.entitiy.CategoryModel;
import com.huihu.module_home.addquestioncategory.entitiy.GetCategoryByTitleSubcode;
import com.huihu.module_home.addquestioncategory.entitiy.PostCategoryByTitleModel;
import com.huihu.module_home.addquestioncategory.entitiy.PostQuestionModel;
import com.huihu.module_home.addquestioncategory.entitiy.PostQuestionSubcode;

import java.util.List;

public class ImpAddQuestionCategoryModel implements IAddQuestionCategoryModel {
    private final IAddQuestionCategoryPresenter iAddQuestionCategoryPresenter;

    private static final int PAGE_SIZE = 20;

    public ImpAddQuestionCategoryModel(IAddQuestionCategoryPresenter iAddQuestionCategoryPresenter) {
        this.iAddQuestionCategoryPresenter = iAddQuestionCategoryPresenter;
    }

    @Override
    public void p2mPostCategoryByTitle(PostCategoryByTitleModel model) {
        postCategoryByTitle(model, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case GetCategoryByTitleSubcode.success:
                        List<CategoryModel> categoryModelList = new Gson().fromJson(model.getBodyMessage(), new TypeToken<List<CategoryModel>>() {
                        }.getType());
                        iAddQuestionCategoryPresenter.m2pGetCategorySuccess(categoryModelList);
                        break;
                    case GetCategoryByTitleSubcode.businessException:
                    case GetCategoryByTitleSubcode.paramError:
                        iAddQuestionCategoryPresenter.m2pGetCategoryFail(model.getMessage());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iAddQuestionCategoryPresenter.m2pGetCategoryFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPostQuestion(PostQuestionModel model) {
        postQuestion(model, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case PostQuestionSubcode.success:
                        iAddQuestionCategoryPresenter.m2pPostQuestionSuccess();
                        break;
                    case PostQuestionSubcode.paramError:
                    case PostQuestionSubcode.businessException:
                    case PostQuestionSubcode.banned:
                    case PostQuestionSubcode.frequent:
                    case PostQuestionSubcode.contentLimit:
                    case PostQuestionSubcode.titleExist:
                        iAddQuestionCategoryPresenter.m2pGetCategoryFail(model.getMessage());
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iAddQuestionCategoryPresenter.m2pPostQuestionFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    private void postQuestion(PostQuestionModel model, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Ideas.postQuestion, NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(model));
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    /**
     * 获取问题分类列表
     */
    private void postCategoryByTitle(PostCategoryByTitleModel model, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Categorys.postCategoryByTitle, NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(model));
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
