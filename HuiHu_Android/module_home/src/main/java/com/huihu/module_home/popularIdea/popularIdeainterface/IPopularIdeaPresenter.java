package com.huihu.module_home.popularIdea.popularIdeainterface;

import android.view.View;

import com.huihu.module_home.popularIdea.enity.PopularIdeaData;
import com.huihu.module_home.popularIdea.enity.SwitchGrph;

import java.util.List;

public interface IPopularIdeaPresenter {
    void v2pGetListFirst();
    void v2pLoadMoreList(PopularIdeaData.PageDatasBean endBean);
    void v2pGetSwitch(int type);
    void m2pGetList(List<PopularIdeaData.PageDatasBean> mlist,boolean isMore);
    void m2pGetSwitchGrph(List<SwitchGrph> switchGrphs);
    void m2pNetFail();
    void m2pNetComplete();
    void v2pDeleteIdea(long ideaId,int position);
    void m2pDeleteIdeaSuccessful(int position);
    void m2pCancelFollowSuccessful(int position);
    void v2pCancelFollow(long ideaId,int position);
    void v2pPutGiveFollows( PopularIdeaData.PageDatasBean.UserInfoBean bean);
    void m2pPutGiveFollows();
    void m2pPutGiveFollowsError(String subCode);
    void v2pJudgyPopularItemClick(View view, int viewType, PopularIdeaData.PageDatasBean bean,int position);
}
