package com.huihu.module_circle.circle.circleinterface;

import com.huihu.module_circle.circle.entity.CircleBaseBean;
import com.huihu.module_circle.circle.entity.CircleInfo;

public interface ICirclePresenter {
    void v2pGetCircle();
    void m2pReturnCircleInfo(CircleInfo circleInfo);
    void m2pNetFail();
    void m2pNetComplete();
    void m2pJoinCircleSuccess(int position,int type);
    void v2pJoinCircle(long circleId, int i, int position,int type);
}
