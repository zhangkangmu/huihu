package com.huihu.module_circle.circlelist.presenter;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.circlelist.circlelistinterface.ICircleListModel;
import com.huihu.module_circle.circlelist.circlelistinterface.ICircleListPresenter;
import com.huihu.module_circle.circlelist.circlelistinterface.ICircleListView;
import com.huihu.module_circle.circlelist.entity.CircleListInfo;
import com.huihu.module_circle.circlelist.model.ImpCircleListModel;

public class ImpCircleListPresenter implements ICircleListPresenter {
    private final ICircleListModel iCircleListModel = new ImpCircleListModel(this);
    private final ICircleListView iCircleListView;

    public ImpCircleListPresenter(ICircleListView iCircleListView) {
        this.iCircleListView = iCircleListView;
    }

    @Override
    public void v2pRequestCircleMessage(long circleId, long currentUid) {
        iCircleListModel.p2mRequestCircleMessage(circleId,currentUid);
    }

    @Override
    public void m2pReturnCircleList(CircleListInfo info) {
        iCircleListView.p2vReturnCircleList(info);
    }

    @Override
    public void v2pJoinCircle(CircleListInfo mInfo,boolean isAbort) {
        iCircleListModel.p2mJoinCircle(mInfo,isAbort);
    }

    @Override
    public void p2mReturnJoinSucces(CircleListInfo mInfo) {
        iCircleListView.v2pReturnJoinSucces(mInfo);
    }
}
