package com.huihu.module_home.addquestioncategory.addquestioncategoryinterface;

import com.huihu.module_home.addquestioncategory.entitiy.CategoryModel;
import com.huihu.module_home.addquestioncategory.entitiy.PostCategoryByTitleModel;
import com.huihu.module_home.addquestioncategory.entitiy.PostQuestionModel;

import java.util.List;

public interface IAddQuestionCategoryPresenter {

    void v2pPostCategory(PostCategoryByTitleModel model);

    void m2pGetCategorySuccess(List<CategoryModel> list);

    void v2pRelease(PostQuestionModel model);

    void m2pPostQuestionSuccess();

    void m2pGetCategoryFail(String msg);

    void m2pPostQuestionFail(String msg);

    void v2pCheckCount(List<CategoryModel> list, CategoryModel model);
}
