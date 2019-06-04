package com.huihu.module_circle.createcircle.createcircleinterface;

import com.huihu.module_circle.createcircle.entity.CreateCircleInfo;

public interface ICreateCircleView {
    void p2vReturnCircleInfo(CreateCircleInfo createCircleInfo);

    void p2vReturnCircleInfoError(String subCode);
}
