package com.huihu.module_mine.others.othersinterface;

import com.huihu.module_mine.others.entity.UserInfo;

public interface IOthersPresenter {
    void v2pGetUserInfo(long otherId);
    void m2pReturnUserInfo(UserInfo userInfo);
}
