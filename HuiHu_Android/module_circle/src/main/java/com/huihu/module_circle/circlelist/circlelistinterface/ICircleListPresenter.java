package com.huihu.module_circle.circlelist.circlelistinterface;

import com.huihu.module_circle.circlelist.entity.CircleListInfo;

public interface ICircleListPresenter {

    void v2pRequestCircleMessage(long circleId, long currentUid);

    void m2pReturnCircleList(CircleListInfo info);

    void v2pJoinCircle(CircleListInfo mInfo,boolean isAbort);

    void p2mReturnJoinSucces(CircleListInfo mInfo);

}
