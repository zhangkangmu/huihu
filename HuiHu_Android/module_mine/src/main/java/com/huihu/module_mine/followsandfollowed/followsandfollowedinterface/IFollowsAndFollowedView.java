package com.huihu.module_mine.followsandfollowed.followsandfollowedinterface;

import com.huihu.module_mine.followsandfollowed.entity.FollowsBean;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public interface IFollowsAndFollowedView {
    void p2vReturnMoreFollowAndFansList(List<FollowsBean.PageDatasBean> pageDatas);
    void p2vReturnFollowAndFansList(List<FollowsBean.PageDatasBean> pageDatas);
    void p2vHandleItemClickResult(SupportFragment fragment);
}
