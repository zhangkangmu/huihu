package com.huihu.module_circle.circlelist.circlelistinterface;

import com.huihu.module_circle.circlelist.entity.CircleListInfo;

public interface ICircleListModel {

    void p2mRequestCircleMessage(long circleId, long currentUid);

    void p2mJoinCircle(CircleListInfo mInfo,boolean isAbort);

}
