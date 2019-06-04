package com.huihu.module_home.writeanswer.writeanswerinterface;

import com.huihu.module_home.question.entity.PostDraftModel;
import com.huihu.module_home.writeanswer.entity.PostAnswerModel;

public interface IWriteAnswerModel {

    void p2mPostAnswer(PostAnswerModel model);

    void p2mPostDraft(PostDraftModel model);
}
