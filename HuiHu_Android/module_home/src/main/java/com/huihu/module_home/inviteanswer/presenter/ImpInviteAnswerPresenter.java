package com.huihu.module_home.inviteanswer.presenter;

import com.huihu.module_home.inviteanswer.inviteanswerinterface.IInviteAnswerModel;
import com.huihu.module_home.inviteanswer.inviteanswerinterface.IInviteAnswerPresenter;
import com.huihu.module_home.inviteanswer.inviteanswerinterface.IInviteAnswerView;
import com.huihu.module_home.inviteanswer.model.ImpInviteAnswerModel;

public class ImpInviteAnswerPresenter implements IInviteAnswerPresenter {
    private final IInviteAnswerModel iInviteAnswerModel = new ImpInviteAnswerModel(this);
    private final IInviteAnswerView iInviteAnswerView;

    public ImpInviteAnswerPresenter(IInviteAnswerView iInviteAnswerView) {
        this.iInviteAnswerView = iInviteAnswerView;
    }
}
