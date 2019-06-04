package com.huihu.module_circle.mydiscuss.mydiscussinterface;

import com.huihu.module_circle.mydiscuss.entity.MyDiscussInfo;

import java.util.List;

public interface IMyDiscussView {

    void p2vReturnMoreCircleDiscussList(List<MyDiscussInfo.PageDatasBean> datas);

    void p2vReturnCircleDiscussList(List<MyDiscussInfo.PageDatasBean> datas);

    void p2vShowNoData();

    void p2vNetFail();

    void p2vNetComplete();

}
