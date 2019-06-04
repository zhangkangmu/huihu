package com.huihu.module_mine.community.presenter;

import com.huihu.module_mine.community.communityinterface.ICommunityModel;
import com.huihu.module_mine.community.communityinterface.ICommunityPresenter;
import com.huihu.module_mine.community.communityinterface.ICommunityView;
import com.huihu.module_mine.community.entity.CommunityInfo;
import com.huihu.module_mine.community.model.ImpCommunityModel;

import java.util.List;

public class ImpCommunityPresenter implements ICommunityPresenter {
    private final ICommunityModel iCommunityModel = new ImpCommunityModel(this);
    private final ICommunityView iCommunityView;

    public ImpCommunityPresenter(ICommunityView iCommunityView) {
        this.iCommunityView = iCommunityView;
    }

    @Override
    public void v2pGetCommunityList(long fid) {

        iCommunityModel.p2mGetCommunityList(0,fid,false);
    }

    @Override
    public void v2pGetMoreCommunityPresenter(CommunityInfo.PageDatasBean bean,long fid) {
        iCommunityModel.p2mGetCommunityList(bean.getDiscussTime(),fid,true);
    }

    @Override
    public void m2pReturnCommunityList(List<CommunityInfo.PageDatasBean> mlist,boolean isMore) {
        if (mlist!=null&&mlist.size()>0){
            if (isMore){
                iCommunityView.p2vGetMoreCommunityListSuccess(mlist);
            }else {
                iCommunityView.p2vGetCommunityListSuccess(mlist);
            }
        }else {
            if(!isMore){
                iCommunityView.p2vShowNoData();
            }
        }

    }

    @Override
    public void m2pNetFail() {

    }

    @Override
    public void m2pNetComplete() {
        iCommunityView.p2vGetDataEnd();
    }
}
