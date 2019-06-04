package com.huihu.module_circle.invitejoincircle.presenter;

import com.huihu.module_circle.invitejoincircle.invitejoincircleinterface.IInviteJoinCircleModel;
import com.huihu.module_circle.invitejoincircle.invitejoincircleinterface.IInviteJoinCirclePresenter;
import com.huihu.module_circle.invitejoincircle.invitejoincircleinterface.IInviteJoinCircleView;
import com.huihu.module_circle.invitejoincircle.model.ImpInviteJoinCircleModel;

public class ImpInviteJoinCirclePresenter implements IInviteJoinCirclePresenter {
    private final IInviteJoinCircleModel iInviteJoinCircleModel = new ImpInviteJoinCircleModel(this);
    private final IInviteJoinCircleView iInviteJoinCircleView;

    public ImpInviteJoinCirclePresenter(IInviteJoinCircleView iInviteJoinCircleView) {
        this.iInviteJoinCircleView = iInviteJoinCircleView;
    }
}
