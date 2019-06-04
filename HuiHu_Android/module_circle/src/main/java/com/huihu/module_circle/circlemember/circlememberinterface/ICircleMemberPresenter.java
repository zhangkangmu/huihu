package com.huihu.module_circle.circlemember.circlememberinterface;

import com.huihu.module_circle.circlemember.entity.MemberManagementInfo;

import java.util.List;

public interface ICircleMemberPresenter {
    void v2pRequestMemberList(long circleId , long lastTime, int memberType, int pageSize, int uid , boolean isMore);

    void m2pReturnMemberList(List<MemberManagementInfo.PageDatasBean> datas, boolean isMore);

    void m2pNetFail();

    void m2pNetComplete();

    void v2pPutGiveFollows(MemberManagementInfo.PageDatasBean.UserInfoBean userInfo);

    void m2pReturnSuccesAttention();

    void m2pPutGiveFollowsError(String subCode);
}
