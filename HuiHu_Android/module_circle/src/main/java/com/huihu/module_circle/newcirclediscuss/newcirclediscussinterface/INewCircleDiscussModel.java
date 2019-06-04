package com.huihu.module_circle.newcirclediscuss.newcirclediscussinterface;

import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;

public interface INewCircleDiscussModel {

    void m2pRequestCircleDiscuss(long circleId , long lastTime, int pageSize, long uid ,boolean isMore);

//    void p2mPutGiveFollows(long aboutId , int followType , int state, int uid );

    void p2mPutGiveFollows(NewCircleDiscussInfo.PageDatasBean.UserInfoBean userInfo);

}
