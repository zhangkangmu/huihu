package com.huihu.module_circle.invitejoincircle.model;

import com.huihu.module_circle.invitejoincircle.invitejoincircleinterface.IInviteJoinCircleModel;
import com.huihu.module_circle.invitejoincircle.invitejoincircleinterface.IInviteJoinCirclePresenter;

public class ImpInviteJoinCircleModel implements IInviteJoinCircleModel {
    private final IInviteJoinCirclePresenter iInviteJoinCirclePresenter;

    public ImpInviteJoinCircleModel(IInviteJoinCirclePresenter iInviteJoinCirclePresenter) {
        this.iInviteJoinCirclePresenter = iInviteJoinCirclePresenter;
    }
}
