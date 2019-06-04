package com.huihu.module_circle.discuss.discussinterface;

import com.huihu.module_circle.discuss.entity.RecemendDiscussInfo;

public interface IDiscussView {
    void p2vReturnRecemendDiscuss(RecemendDiscussInfo recemendDiscussInfo,boolean isMore);
    void p2vDeleteIdeaSuccessful(int position);
    void p2vShowNoData();
    void p2vGetDataEnd();
}
