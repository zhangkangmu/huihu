package com.huihu.module_home.writeanswer.writeanswerinterface;

import com.huihu.module_home.question.entity.PostDraftModel;
import com.huihu.module_home.writeanswer.entity.PostAnswerModel;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;

public interface IWriteAnswerPresenter {

    void v2pCheckReleaseStatus(String text);

    void v2pPostAnswer(PostAnswerModel model);

    void m2pPostAnswerSuccess(String message);

    void m2pPostAnswerFail(String message);

    void v2pLoadPic(List<AlbumImageBean> list);

    void v2pCheckString(String html);

    void v2pPostDraft(PostDraftModel model);

    void m2pPostDiscussFail(String msg);

    void m2pPostDiscussSuccess(String message);
}
