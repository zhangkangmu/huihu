package com.huihu.module_circle.mycircle.mycircleinterface;

public interface IMyCircleModel {
    void p2mGetMyCircle(long lastTime,boolean isMore);
    void p2mJoinCircle(long circleId, int state, int position);
}
