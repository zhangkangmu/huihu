package com.huihu.module_circle.newcirclediscuss.newcirclediscussinterface;

import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;

import java.util.List;

public interface INewCircleDiscussPresenter {

    void v2pRequestCircleDiscuss(long circleId , long lastTime, int pageSize, long uid ,boolean isMore);

    void m2pReturnCircleDiscussList(List<NewCircleDiscussInfo.PageDatasBean> datas, boolean isMore);

    void m2pNetFail();

    void m2pNetComplete();

    void v2pRequestMoreCircleDiscuss(long circleId , long lastTime, int pageSize, long uid ,boolean isMore);

    void v2pPutGiveFollows(NewCircleDiscussInfo.PageDatasBean.UserInfoBean userInfo);

    void m2pReturnSuccesAttention();

    void m2pPutGiveFollowsError(String subCode);

}
