package com.huihu.module_circle.newcirclediscuss.newcirclediscussinterface;

import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;

import java.util.List;

public interface INewCircleDiscussView {

    void p2vReturnMoreCircleDiscussList(List<NewCircleDiscussInfo.PageDatasBean> datas);

    void p2vReturnCircleDiscussList(List<NewCircleDiscussInfo.PageDatasBean> datas);

    void p2vShowNoData();

    void p2vNetFail();

    void p2vNetComplete();

    void p2vReturnSuccesAttention();

    void p2vPutGiveFollowsError(String subCode);

}
