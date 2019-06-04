package com.huihu.module_circle.createcirclesuccess.createcirclesuccessinterface;

import com.huihu.module_circle.createcirclesuccess.entity.CreatedCircleInfo;

public interface ICreateCircleSuccessPresenter {
    void v2pGetCircleInfo(long circleId);
    void m2pReturnCreatedCircleInfo(CreatedCircleInfo createdCircleInfo);
    void m2pNetComplete();
    void m2pReturnCreatedCircleInfoError(String subCode);
    void m2pNetError();
}
