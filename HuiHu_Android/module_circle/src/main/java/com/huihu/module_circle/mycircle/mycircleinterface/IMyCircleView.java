package com.huihu.module_circle.mycircle.mycircleinterface;

import com.huihu.module_circle.mycircle.entity.MyCircleInfo;

public interface IMyCircleView {
    void p2vReturnMyCircle(MyCircleInfo myCircleInfo,boolean isMore);
    void p2vShowNoData();
    void p2vGetDataEnd();
    void p2vJoinCircleSuccess(int position);
}
