package com.huihu.module_mine.others.othersinterface;

import com.huihu.module_mine.editprofile.entity.User;
import com.huihu.module_mine.others.entity.UserInfo;

public interface IOthersView {
    void p2vGetUserInfoSuccess(UserInfo userInfo);
    void p2vGetUserInfoFailed();
}
