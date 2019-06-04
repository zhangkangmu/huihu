package com.huihu.module_mine.attentioncircle.attentioncircleinterface;

import com.huihu.module_mine.attentioncircle.entity.CircleAttentionInfo;

import java.util.List;

public interface IAttentionCirclePresenter {
void v2pGetAttentionCircleList();
void m2pGetAttentionCircle(List<CircleAttentionInfo.PageDatasBean> pageDatas,boolean isMore);
void m2pNetFail();

    void m2pNetComplete();

    void v2pGetMoreCircleList(CircleAttentionInfo.PageDatasBean bean);

//    void m2pReturnCircleList(List<CircleAttentionInfo.PageDatasBean> datas, boolean isMore);

}
