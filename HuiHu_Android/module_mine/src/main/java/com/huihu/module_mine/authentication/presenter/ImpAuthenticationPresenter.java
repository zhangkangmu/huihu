package com.huihu.module_mine.authentication.presenter;

import com.huihu.module_mine.authentication.authenticationinterface.IAuthenticationModel;
import com.huihu.module_mine.authentication.authenticationinterface.IAuthenticationPresenter;
import com.huihu.module_mine.authentication.authenticationinterface.IAuthenticationView;
import com.huihu.module_mine.authentication.entity.Authentication;
import com.huihu.module_mine.authentication.model.ImpAuthenticationModel;

import java.util.List;

public class ImpAuthenticationPresenter implements IAuthenticationPresenter {
    private final IAuthenticationModel iAuthenticationModel = new ImpAuthenticationModel(this);
    private final IAuthenticationView iAuthenticationView;

    public ImpAuthenticationPresenter(IAuthenticationView iAuthenticationView) {
        this.iAuthenticationView = iAuthenticationView;
    }

    @Override
    public void v2pGetUserAuthList(long fid) {
        iAuthenticationModel.v2pGetUserAuthList(fid);
    }

    @Override
    public void m2pReturnUserAuthList(List<Authentication.UserAuthShowModelListBean> mList) {
        iAuthenticationView.p2vReturnUserAuthList(mList);
    }
}
