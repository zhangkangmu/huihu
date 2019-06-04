package com.huihu.module_mine.followsandfollowed.followsandfollowedinterface;

import android.view.View;

import com.huihu.module_mine.followsandfollowed.entity.FollowsBean;

import java.util.List;

public interface IFollowsAndFollowedPresenter {
    void v2pGetFollowAndFansList(long userId,int type,int followId);
    void m2pReturnFollowAndFansList(List<FollowsBean.PageDatasBean> pageDatas, int followId);
    void v2pJudgyItemClick(View view, FollowsBean.PageDatasBean bean, int position, int type);
}
