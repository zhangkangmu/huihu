package com.huihu.module_mine.authentication.authenticationinterface;

import com.huihu.module_mine.authentication.entity.Authentication;

import java.util.List;

public interface IAuthenticationView {
    void p2vReturnUserAuthList(List<Authentication.UserAuthShowModelListBean> mList);
}
