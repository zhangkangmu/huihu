package com.huihu.module_mine.attentioncircle.attentioncircleinterface;

import com.huihu.module_mine.attentioncircle.entity.CircleAttentionInfo;

import java.util.List;

public interface IAttentionCircleView {
void p2vGetAttentionCircleListSucces(List<CircleAttentionInfo.PageDatasBean> pageDatas);
void p2vNetFail();
void p2vShowNoData();

    void p2vReturnMoreCircleListSuccess(List<CircleAttentionInfo.PageDatasBean> datas);

    void p2vNetComplete();
    
}
