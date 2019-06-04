package com.huihu.module_circle.newcirclerecommend.newcirclerecommendinterface;

import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;
import com.huihu.module_circle.newcirclerecommend.entity.NewCircleRecommendInfo;

import java.util.List;

public interface INewCircleRecommendView {

//    void p2vReturnMoreNewCircleRecommendList(List<NewCircleRecommendInfo.PageDatasBean> datas);
    void p2vReturnMoreNewCircleRecommendList(NewCircleRecommendInfo info);

//    void p2vReturnNewCircleRecommendList(List<NewCircleRecommendInfo.PageDatasBean> datas);
    void p2vReturnNewCircleRecommendList(NewCircleRecommendInfo info);

    void p2vShowNoData();

    void p2vNetFail();

    void p2vNetComplete();

}
