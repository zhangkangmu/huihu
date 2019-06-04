package com.huihu.module_circle.mycircle.presenter;

import com.huihu.module_circle.mycircle.entity.MyCircleInfo;
import com.huihu.module_circle.mycircle.mycircleinterface.IMyCircleModel;
import com.huihu.module_circle.mycircle.mycircleinterface.IMyCirclePresenter;
import com.huihu.module_circle.mycircle.mycircleinterface.IMyCircleView;
import com.huihu.module_circle.mycircle.model.ImpMyCircleModel;

public class ImpMyCirclePresenter implements IMyCirclePresenter {
    private final IMyCircleModel iMyCircleModel = new ImpMyCircleModel(this);
    private final IMyCircleView iMyCircleView;

    public ImpMyCirclePresenter(IMyCircleView iMyCircleView) {
        this.iMyCircleView = iMyCircleView;
    }

    @Override
    public void v2pGetMyCircle(long lastTime) {
        if (lastTime!=-1) {
            iMyCircleModel.p2mGetMyCircle(lastTime, true);
        }else {
            iMyCircleModel.p2mGetMyCircle(lastTime, false);
        }
    }

    @Override
    public void m2pNetFail() {

    }

    @Override
    public void m2pNetComplete() {
        iMyCircleView.p2vGetDataEnd();
    }

    @Override
    public void m2pReturnMyCircle(MyCircleInfo myCircleInfo,boolean isMore) {
        iMyCircleView.p2vReturnMyCircle(myCircleInfo,isMore);
    }

    @Override
    public void v2pJoinCircle(long circleId, int state, int position) {
        iMyCircleModel.p2mJoinCircle(circleId,state,position);
    }

    @Override
    public void m2pJoinCircleSuccess(int position) {
        iMyCircleView.p2vJoinCircleSuccess(position);
    }
}
