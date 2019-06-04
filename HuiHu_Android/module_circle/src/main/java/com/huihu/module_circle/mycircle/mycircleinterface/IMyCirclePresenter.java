package com.huihu.module_circle.mycircle.mycircleinterface;

import com.huihu.module_circle.mycircle.entity.MyCircleInfo;

public interface IMyCirclePresenter {
    void v2pGetMyCircle(long index);
    void m2pNetFail();
    void m2pNetComplete();
    void m2pReturnMyCircle(MyCircleInfo myCircleInfo,boolean isMore);
    void v2pJoinCircle(long circleId, int i, int position);
    void m2pJoinCircleSuccess(int position);
}
