package com.huihu.module_circle.circlemember.circlememberinterface;

import com.huihu.module_circle.circlemember.entity.MemberManagementInfo;

import java.util.List;

public interface ICircleMemberView {

    void p2vReturnMemberList(List<MemberManagementInfo.PageDatasBean> datas);

    void p2vNetFail();

    void p2vNetComplete();

    void p2vShowNoData();

    void p2vReturnMoreMemberList(List<MemberManagementInfo.PageDatasBean> datas);

    void p2vReturnSuccesAttention();

    void p2vPutGiveFollowsError(String subCode);
}
