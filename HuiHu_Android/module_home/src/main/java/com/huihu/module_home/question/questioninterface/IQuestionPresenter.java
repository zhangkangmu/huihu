package com.huihu.module_home.question.questioninterface;

import com.huihu.module_home.question.entity.PostDiscussModel;
import com.huihu.module_home.question.entity.PostDraftModel;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;

public interface IQuestionPresenter {

    void v2pLoadPic(List<AlbumImageBean> list);

    void v2pCheckInput(String trim,int type);

    void v2pPostDraft(PostDraftModel model);

    void m2pPostDraftSuccess();

    void m2pPostDraftFail();

    void v2pCheckString(String trim, String html);

    void v2pGoto(int typeWrite);

    void v2pPostDiscuss(PostDiscussModel model);

    void m2pPostDiscussFail(String msg);

    void m2pPostDiscussSuccess(String message);
}
