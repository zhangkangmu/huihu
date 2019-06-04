package com.huihu.module_home.addquestioncategory.addquestioncategoryinterface;

import com.huihu.module_home.addquestioncategory.entitiy.CategoryModel;

import java.util.List;

public interface IAddQuestionCategoryView {

    void p2vShowCategoryList(List<CategoryModel> list);

    void p2vShowToast(String message);

    void p2vDismissDialog();

    void p2vFinish();

    void p2vAddCategory(CategoryModel categoryModel);
}
