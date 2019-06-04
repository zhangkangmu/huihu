package com.huihu.module_home.inviteanswer.model;

import com.huihu.module_home.inviteanswer.inviteanswerinterface.IInviteAnswerModel;
import com.huihu.module_home.inviteanswer.inviteanswerinterface.IInviteAnswerPresenter;

public class ImpInviteAnswerModel implements IInviteAnswerModel {
    private final IInviteAnswerPresenter iInviteAnswerPresenter;

    public ImpInviteAnswerModel(IInviteAnswerPresenter iInviteAnswerPresenter) {
        this.iInviteAnswerPresenter = iInviteAnswerPresenter;
    }
}
