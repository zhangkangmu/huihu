package com.huihu.module_mine.mine.presenter;

import com.huihu.module_mine.mine.entity.UserInfo;
import com.huihu.module_mine.mine.mineinterface.IMineModel;
import com.huihu.module_mine.mine.mineinterface.IMinePresenter;
import com.huihu.module_mine.mine.mineinterface.IMineView;
import com.huihu.module_mine.mine.model.ImpMineModel;

public class ImpMinePresenter implements IMinePresenter {
    private final IMineModel iMineModel = new ImpMineModel(this);
    private final IMineView iMineView;

    public ImpMinePresenter(IMineView iMineView) {
        this.iMineView = iMineView;
    }

    @Override
    public void v2pGetUserInfo(long otherId) {
        iMineModel.p2mGetUseInfo(otherId);
    }

    @Override
    public void m2pReturnUserInfo(UserInfo userInfo) {
        iMineView.p2vGetUserInfoSuccess(userInfo);
    }

    @Override
    public void m2pNetComplete() {
        iMineView.p2vGetDataEnd();
    }

    @Override
    public void m2pNetFail() {
        iMineView.p2vNetFail();
    }
}
