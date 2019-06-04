package com.huihu.module_mine.followsandfollowed.followsandfollowedinterface;

import com.huihu.module_mine.followsandfollowed.entity.FollowsBean;

public interface IFollowsAndFollowedModel {
    void p2mGetFollowAndFansList(long userId, int type, int followId);
    void p2mControlFollow(FollowsBean.PageDatasBean bean);
}
