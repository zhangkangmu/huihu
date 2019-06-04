package com.huihu.module_circle.circlemember.circlememberinterface;

import com.huihu.module_circle.circlemember.entity.MemberManagementInfo;


public interface ICircleMemberModel {
    void p2mRequestMemberList(long circleId, long lastTime, int memberType, int pageSize, int uid, boolean isMore);

    void p2mPutGiveFollows(MemberManagementInfo.PageDatasBean.UserInfoBean userInfo);


}
