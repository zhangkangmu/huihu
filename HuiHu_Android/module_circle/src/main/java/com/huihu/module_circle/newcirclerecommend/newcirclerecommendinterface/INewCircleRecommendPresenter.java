package com.huihu.module_circle.newcirclerecommend.newcirclerecommendinterface;

import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;
import com.huihu.module_circle.newcirclerecommend.entity.NewCircleRecommendInfo;

import java.util.List;

public interface INewCircleRecommendPresenter {

    void v2pRequestCircleRecommendList();

//    void m2pReturnNewCircleRecommendList(List<NewCircleRecommendInfo.PageDatasBean> datas, boolean isMore);
    void m2pReturnNewCircleRecommendList(NewCircleRecommendInfo info, boolean isMore);

    void m2pNetFail();

    void m2pNetComplete();

    void v2pRequestMoreCircleRecommendList(NewCircleRecommendInfo info);

}
