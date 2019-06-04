package com.huihu.module_circle.membermanagement.presenter;

import com.huihu.module_circle.membermanagement.entity.MemberManagementInfo;
import com.huihu.module_circle.membermanagement.membermanagementinterface.IMemberManagementModel;
import com.huihu.module_circle.membermanagement.membermanagementinterface.IMemberManagementPresenter;
import com.huihu.module_circle.membermanagement.membermanagementinterface.IMemberManagementView;
import com.huihu.module_circle.membermanagement.model.ImpMemberManagementModel;

import java.util.List;

public class ImpMemberManagementPresenter implements IMemberManagementPresenter {
    private final IMemberManagementModel iMemberManagementModel = new ImpMemberManagementModel(this);
    private final IMemberManagementView iMemberManagementView;

    public ImpMemberManagementPresenter(IMemberManagementView iMemberManagementView) {
        this.iMemberManagementView = iMemberManagementView;
    }

    @Override
    public void v2pRequestMemberList(long circleId, long lastTime, int memberType, int pageSize, int uid, boolean isMore) {
        iMemberManagementModel.p2mRequestMemberList(circleId, lastTime, memberType, pageSize, uid, isMore);
    }

    @Override
    public void m2pReturnMemberList(List<MemberManagementInfo.PageDatasBean> datas,boolean isMore) {
        if (datas!=null && datas.size()>0){
            if (isMore) {
                iMemberManagementView.p2vReturnMoreMemberList(datas);
            }else{
                iMemberManagementView.p2vReturnMemberList(datas);
            }
        }else{
            if (!isMore){
                iMemberManagementView.p2vShowNoData();
            }
        }

    }

    @Override
    public void m2pNetFail() {
        iMemberManagementView.p2vNetFail();
    }

    @Override
    public void m2pNetComplete() {
        iMemberManagementView.p2vNetComplete();
    }

    @Override
    public void v2pPutGiveFollows(MemberManagementInfo.PageDatasBean.UserInfoBean userInfo) {
        iMemberManagementModel.p2mPutGiveFollows(userInfo);
    }

    @Override
    public void m2pReturnSuccesAttention() {
        iMemberManagementView.p2vReturnSuccesAttention();
    }

    @Override
    public void m2pPutGiveFollowsError(String subCode) {
        iMemberManagementView.p2vPutGiveFollowsError(subCode);
    }

    @Override
    public void v2pRequestForbidMember(long circleId,MemberManagementInfo.PageDatasBean bean,String forbidDay) {
        iMemberManagementModel.p2mRequestForbidMember(circleId,bean,forbidDay);
    }

    @Override
    public void m2pReturnForbidMember(MemberManagementInfo.PageDatasBean bean) {
        iMemberManagementView.p2vReturnForbidMember(bean);
    }
}
