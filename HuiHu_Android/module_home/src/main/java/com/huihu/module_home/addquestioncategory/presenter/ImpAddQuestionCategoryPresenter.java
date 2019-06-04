package com.huihu.module_home.addquestioncategory.presenter;

import com.huihu.module_home.addquestioncategory.addquestioncategoryinterface.IAddQuestionCategoryModel;
import com.huihu.module_home.addquestioncategory.addquestioncategoryinterface.IAddQuestionCategoryPresenter;
import com.huihu.module_home.addquestioncategory.addquestioncategoryinterface.IAddQuestionCategoryView;
import com.huihu.module_home.addquestioncategory.entitiy.CategoryModel;
import com.huihu.module_home.addquestioncategory.entitiy.PostCategoryByTitleModel;
import com.huihu.module_home.addquestioncategory.entitiy.PostQuestionModel;
import com.huihu.module_home.addquestioncategory.model.ImpAddQuestionCategoryModel;

import java.util.List;

public class ImpAddQuestionCategoryPresenter implements IAddQuestionCategoryPresenter {
    private final IAddQuestionCategoryModel iAddQuestionCategoryModel = new ImpAddQuestionCategoryModel(this);
    private final IAddQuestionCategoryView iAddQuestionCategoryView;

    public ImpAddQuestionCategoryPresenter(IAddQuestionCategoryView iAddQuestionCategoryView) {
        this.iAddQuestionCategoryView = iAddQuestionCategoryView;
    }

    @Override
    public void v2pPostCategory(PostCategoryByTitleModel model) {
        iAddQuestionCategoryModel.p2mPostCategoryByTitle(model);
    }

    @Override
    public void m2pGetCategorySuccess(List<CategoryModel> list) {
        iAddQuestionCategoryView.p2vShowCategoryList(list);
    }

    @Override
    public void v2pRelease(PostQuestionModel model) {
        iAddQuestionCategoryModel.p2mPostQuestion(model);
    }

    @Override
    public void m2pPostQuestionSuccess() {
        iAddQuestionCategoryView.p2vDismissDialog();
        iAddQuestionCategoryView.p2vShowToast("发布成功");
        iAddQuestionCategoryView.p2vFinish();
    }

    @Override
    public void m2pGetCategoryFail(String msg) {
        iAddQuestionCategoryView.p2vDismissDialog();
        iAddQuestionCategoryView.p2vShowToast(msg);
    }

    @Override
    public void m2pPostQuestionFail(String msg) {
        iAddQuestionCategoryView.p2vDismissDialog();
        iAddQuestionCategoryView.p2vShowToast(msg);
    }

    @Override
    public void v2pCheckCount(List<CategoryModel> list, CategoryModel model) {
        if (list.size() < 5) {
            iAddQuestionCategoryView.p2vAddCategory(model);
        } else {
            iAddQuestionCategoryView.p2vShowToast("最多添加五个分类");
        }
    }
}
