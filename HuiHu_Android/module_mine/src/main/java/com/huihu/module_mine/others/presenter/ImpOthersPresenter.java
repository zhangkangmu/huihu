package com.huihu.module_mine.others.presenter;

import com.huihu.module_mine.others.entity.UserInfo;
import com.huihu.module_mine.others.othersinterface.IOthersModel;
import com.huihu.module_mine.others.othersinterface.IOthersPresenter;
import com.huihu.module_mine.others.othersinterface.IOthersView;
import com.huihu.module_mine.others.model.ImpOthersModel;

public class ImpOthersPresenter implements IOthersPresenter {
    private final IOthersModel iOthersModel = new ImpOthersModel(this);
    private final IOthersView iOthersView;

    public ImpOthersPresenter(IOthersView iOthersView) {
        this.iOthersView = iOthersView;
    }

    @Override
    public void v2pGetUserInfo(long otherId) {
        iOthersModel.p2mGetUseInfo(otherId);
    }

    @Override
    public void m2pReturnUserInfo(UserInfo userInfo) {
        iOthersView.p2vGetUserInfoSuccess(userInfo);
    }
}
