package com.huihu.module_circle.discuss.discussinterface;

import com.huihu.module_circle.discuss.entity.RecemendDiscussInfo;

public interface IDiscussPresenter {
    void v2pGetRecemendDiscuss(long index);
    void m2pNetFail();
    void m2pNetComplete();
    void m2pReturnRecemendDiscuss(RecemendDiscussInfo recemendDiscussInfo,boolean isMore);
    void v2pDeleteRecommendDiscuss(RecemendDiscussInfo.PageDatasBean pageDatasBean, int position);
    void m2pDeleteIdeaSuccessful(int position);
}
