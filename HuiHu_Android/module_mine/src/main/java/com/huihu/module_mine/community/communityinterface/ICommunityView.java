package com.huihu.module_mine.community.communityinterface;

import com.huihu.module_mine.community.entity.CommunityInfo;

import java.util.List;

public interface ICommunityView {
    void p2vGetCommunityListSuccess(List<CommunityInfo.PageDatasBean> mlist);
    void p2vGetMoreCommunityListSuccess(List<CommunityInfo.PageDatasBean> mlist);
    void p2vShowNoData();
    void p2vGetDataEnd();
}
