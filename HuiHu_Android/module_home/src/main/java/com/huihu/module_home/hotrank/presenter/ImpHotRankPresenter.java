package com.huihu.module_home.hotrank.presenter;

import com.huihu.module_home.hotrank.entity.HotRankBean;
import com.huihu.module_home.hotrank.hotrankinterface.IHotRankModel;
import com.huihu.module_home.hotrank.hotrankinterface.IHotRankPresenter;
import com.huihu.module_home.hotrank.hotrankinterface.IHotRankView;
import com.huihu.module_home.hotrank.model.ImpHotRankModel;
import com.huihu.module_home.popularIdea.enity.SwitchGrph;

import java.util.List;

public class ImpHotRankPresenter implements IHotRankPresenter {
    private final IHotRankModel iHotRankModel = new ImpHotRankModel(this);
    private final IHotRankView iHotRankView;

    public ImpHotRankPresenter(IHotRankView iHotRankView) {
        this.iHotRankView = iHotRankView;
    }

    @Override
    public void v2pGetHotRank() {
        iHotRankModel.p2mGetHotRank(0,0,false);
    }

    @Override
    public void v2pGetMoreHotRank(HotRankBean.PageDatasBean databean) {
        iHotRankModel.p2mGetHotRank(databean.getBrowse(),databean.getHoldTime(),true);
    }

    @Override
    public void m2pGetHotRank(List<HotRankBean.PageDatasBean> pageDatas,boolean isMore) {
        if (pageDatas!=null&&pageDatas.size()>0) {
            if (isMore){
                iHotRankView.p2vGetMoreHotRankSuccess(pageDatas);
            } else {
                iHotRankView.p2vGetHotRankSuccess(pageDatas);
            }
        }else {
            if (!isMore){
                iHotRankView.p2vShowNoData();
            }
        }
    }

    @Override
    public void m2pNetFail() {
            iHotRankView.p2vGetHotRankFailed();
    }

    @Override
    public void m2pNetComplete() {
        iHotRankView.p2vGetDataEnd();
    }

    @Override
    public void v2pGetSwitch(int type) {
        iHotRankModel.p2mGetSwitch(type);
    }

    @Override
    public void m2pGetSwitchGrph(List<SwitchGrph> switchGrphs) {
        iHotRankView.p2vGetSwitchGrph(switchGrphs);
    }
}
