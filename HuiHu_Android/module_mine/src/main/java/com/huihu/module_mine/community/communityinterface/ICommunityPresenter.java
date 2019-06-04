package com.huihu.module_mine.community.communityinterface;

import com.huihu.module_mine.community.entity.CommunityInfo;

import java.util.List;

public interface ICommunityPresenter {
    void v2pGetCommunityList(long fid);
    void v2pGetMoreCommunityPresenter(CommunityInfo.PageDatasBean bean,long fid);
    void m2pReturnCommunityList(List<CommunityInfo.PageDatasBean> mlist,boolean isMore);
    void m2pNetFail();
    void m2pNetComplete();
}
