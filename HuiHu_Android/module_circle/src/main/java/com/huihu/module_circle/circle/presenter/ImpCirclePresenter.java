package com.huihu.module_circle.circle.presenter;

import com.huihu.module_circle.circle.circleinterface.ICircleModel;
import com.huihu.module_circle.circle.circleinterface.ICirclePresenter;
import com.huihu.module_circle.circle.circleinterface.ICircleView;
import com.huihu.module_circle.circle.entity.CircleBaseBean;
import com.huihu.module_circle.circle.entity.CircleInfo;
import com.huihu.module_circle.circle.model.ImpCircleModel;

public class ImpCirclePresenter implements ICirclePresenter {
    private final ICircleModel iCircleModel = new ImpCircleModel(this);
    private final ICircleView iCircleView;
    public ImpCirclePresenter(ICircleView iCircleView) {
        this.iCircleView = iCircleView;
    }

    @Override
    public void v2pGetCircle() {
        iCircleModel.p2mGetCircle();
    }

    @Override
    public void m2pReturnCircleInfo(CircleInfo circleInfo) {
        if (circleInfo!=null) {
            iCircleView.p2vReturnCircleInfo(circleInfo);
        }else {
            iCircleView.p2vShowNoData();
        }
    }

    @Override
    public void m2pNetFail() {
        iCircleView.p2vNetFail();
    }

    @Override
    public void m2pNetComplete() {
        iCircleView.p2vGetDataEnd();
    }



    @Override
    public void m2pJoinCircleSuccess(int position,int type) {
        iCircleView.p2vJoinCircleSuccess(position,type);
    }

    @Override
    public void v2pJoinCircle(long circleId, int state, int position,int type) {
        iCircleModel.p2mJoinCircle(circleId,state,position,type);
    }
}
