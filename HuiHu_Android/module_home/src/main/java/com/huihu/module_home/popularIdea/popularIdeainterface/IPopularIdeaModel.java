package com.huihu.module_home.popularIdea.popularIdeainterface;

import com.huihu.module_home.popularIdea.enity.PopularIdeaData;

public interface IPopularIdeaModel {
 void p2mGetPopularIdeaList(long time,boolean isMore);
 void p2mGetSwitchGrph(int type);
 void p2mDeleteIdea(long ideaId,int position);
 void p2mCancelFollow(long ideaId,int position);
 void p2mPutGiveFollows(PopularIdeaData.PageDatasBean.UserInfoBean bean);
}
