package com.huihu.module_home.hotrank.hotrankinterface;

import com.huihu.module_home.hotrank.entity.HotRankBean;
import com.huihu.module_home.popularIdea.enity.SwitchGrph;

import java.util.List;

public interface IHotRankView {
    void p2vGetHotRankSuccess(List<HotRankBean.PageDatasBean> pageDatas);
    void p2vShowNoData();
    void p2vGetDataEnd();
    void p2vGetMoreHotRankSuccess(List<HotRankBean.PageDatasBean> pageDatas);
    void p2vGetHotRankFailed();
    void p2vGetSwitchGrph(List<SwitchGrph> switchGrphs);
}
