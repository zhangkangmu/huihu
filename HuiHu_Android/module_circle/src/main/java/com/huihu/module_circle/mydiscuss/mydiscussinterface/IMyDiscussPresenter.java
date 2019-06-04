package com.huihu.module_circle.mydiscuss.mydiscussinterface;

import com.huihu.module_circle.mydiscuss.entity.MyDiscussInfo;

import java.util.List;

public interface IMyDiscussPresenter {

    void v2pRequestMyDiscuss(long circleId, long lastTime, int pageSize, long uid);

    void m2pReturnMyDiscuss(List<MyDiscussInfo.PageDatasBean> datas, boolean isMore);

    void m2pNetFail();

    void m2pNetComplete();

    void v2pRequestMoreMyDiscuss(long circleId, long lastTime, int pageSize, long uid);

}
