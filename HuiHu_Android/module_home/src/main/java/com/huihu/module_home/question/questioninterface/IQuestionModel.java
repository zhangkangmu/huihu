package com.huihu.module_home.question.questioninterface;

import com.huihu.module_home.question.entity.PostDiscussModel;
import com.huihu.module_home.question.entity.PostDraftModel;

public interface IQuestionModel {

    void p2mPostDraft(PostDraftModel model);

    void p2mSaveToLocal();

    void p2mPostDiscuss(PostDiscussModel model);
}
