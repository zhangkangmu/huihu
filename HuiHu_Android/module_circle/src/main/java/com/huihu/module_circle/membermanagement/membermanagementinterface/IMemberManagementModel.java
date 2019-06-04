package com.huihu.module_circle.membermanagement.membermanagementinterface;

import com.huihu.module_circle.membermanagement.entity.MemberManagementInfo;

public interface IMemberManagementModel {

    void p2mRequestMemberList(long circleId, long lastTime, int memberType, int pageSize, int uid, boolean isMore);

    void p2mPutGiveFollows(MemberManagementInfo.PageDatasBean.UserInfoBean userInfo);

    void p2mRequestForbidMember(long circleId,MemberManagementInfo.PageDatasBean bean,String forbidDay);

}
