package com.huihu.module_home.hotrank.hotrankinterface;

import com.huihu.module_home.hotrank.entity.HotRankBean;
import com.huihu.module_home.popularIdea.enity.SwitchGrph;

import java.util.List;

public interface IHotRankPresenter {
    void v2pGetHotRank();
    void v2pGetMoreHotRank(HotRankBean.PageDatasBean databean);
    void m2pGetHotRank(List<HotRankBean.PageDatasBean> pageDatas,boolean isMore);
    void m2pNetFail();
    void m2pNetComplete();
    void v2pGetSwitch(int type);
    void m2pGetSwitchGrph(List<SwitchGrph> switchGrphs);
}
