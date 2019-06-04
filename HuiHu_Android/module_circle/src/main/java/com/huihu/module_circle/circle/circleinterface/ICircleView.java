package com.huihu.module_circle.circle.circleinterface;

import com.huihu.module_circle.circle.entity.CircleBaseBean;
import com.huihu.module_circle.circle.entity.CircleInfo;

public interface ICircleView {
    void p2vReturnCircleInfo(CircleInfo circleInfo);
    void p2vGetDataEnd();
    void p2vShowNoData();
    void p2vJoinCircleSuccess(int position,int type);
    void p2vNetFail();
}
