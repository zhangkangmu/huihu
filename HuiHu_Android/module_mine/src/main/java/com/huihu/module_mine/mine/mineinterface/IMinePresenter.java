package com.huihu.module_mine.mine.mineinterface;

import com.huihu.module_mine.mine.entity.UserInfo;

public interface IMinePresenter {
    void v2pGetUserInfo(long otherId);
    void m2pReturnUserInfo(UserInfo userInfo);
    void m2pNetComplete();
    void m2pNetFail();
}
