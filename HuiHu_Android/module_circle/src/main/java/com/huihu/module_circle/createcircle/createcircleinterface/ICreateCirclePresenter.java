package com.huihu.module_circle.createcircle.createcircleinterface;

import com.google.gson.JsonObject;
import com.huihu.module_circle.createcircle.entity.CreateCircleInfo;

public interface ICreateCirclePresenter {
    void v2pCreateCircle(JsonObject jsonObject);
    void m2pReturnCircleInfo(CreateCircleInfo createCircleInfo);
    void m2pNetFail();
    void m2pNetComplete();
    void m2pReturnCircleInfoError(String subCode);
}
