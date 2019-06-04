package com.huihu.module_circle.newcircleintroduction.newcircleintroductioninterface;

import com.huihu.module_circle.newcircleintroduction.entity.CircleListInfo;

public interface INewCircleIntroductionPresenter {

    void v2pRequestIntroduction(long circleId, long uid);

    void m2pRturnIntroduction(CircleListInfo info);

}
