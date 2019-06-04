package com.huihu.module_mine.mine.mineinterface;

import com.huihu.module_mine.mine.entity.UserInfo;

public interface IMineView {
    void p2vGetUserInfoSuccess(UserInfo userInfo);
    void p2vGetUserInfoFailed();
    void p2vNetFail();

    void p2vGetDataEnd();
}
