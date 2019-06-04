package com.huihu.module_circle.discuss.discussinterface;

import com.huihu.module_circle.discuss.entity.RecemendDiscussInfo;

public interface IDiscussModel {
    void p2mGetRecemendDiscuss(long index,boolean isMore);
    void p2mDeleteRecommendDiscuss(RecemendDiscussInfo.PageDatasBean pageDatasBean, int position);
}
