package com.huihu.module_home.addquestioncategory.addquestioncategoryinterface;

import com.huihu.module_home.addquestioncategory.entitiy.PostCategoryByTitleModel;
import com.huihu.module_home.addquestioncategory.entitiy.PostQuestionModel;

public interface IAddQuestionCategoryModel {

    void p2mPostCategoryByTitle(PostCategoryByTitleModel model);

    void p2mPostQuestion(PostQuestionModel model);
}
