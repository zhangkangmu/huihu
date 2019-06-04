package com.huihu.module_mine.authentication.authenticationinterface;

import com.huihu.module_mine.authentication.entity.Authentication;

import java.util.List;

public interface IAuthenticationPresenter {
    void v2pGetUserAuthList(long fid);
    void m2pReturnUserAuthList(List<Authentication.UserAuthShowModelListBean> mList);
}
