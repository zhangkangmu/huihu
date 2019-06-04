package com.huihu.module_circle.circlemember.presenter;

import com.huihu.module_circle.circlemember.circlememberinterface.ICircleMemberModel;
import com.huihu.module_circle.circlemember.circlememberinterface.ICircleMemberPresenter;
import com.huihu.module_circle.circlemember.circlememberinterface.ICircleMemberView;
import com.huihu.module_circle.circlemember.entity.MemberManagementInfo;
import com.huihu.module_circle.circlemember.model.ImpCircleMemberModel;

import java.util.List;

public class ImpCircleMemberPresenter implements ICircleMemberPresenter {
    private final ICircleMemberModel iCircleMemberModel = new ImpCircleMemberModel(this);
    private final ICircleMemberView iCircleMemberView;

    public ImpCircleMemberPresenter(ICircleMemberView iCircleMemberView) {
        this.iCircleMemberView = iCircleMemberView;
    }

    @Override
    public void v2pRequestMemberList(long circleId, long lastTime, int memberType, int pageSize, int uid, boolean isMore) {
        iCircleMemberModel.p2mRequestMemberList(circleId, lastTime, memberType, pageSize, uid, isMore);
    }

    @Override
    public void m2pReturnMemberList(List<MemberManagementInfo.PageDatasBean> datas, boolean isMore) {
        if (datas!=null && datas.size()>0){
            if (isMore) {
                iCircleMemberView.p2vReturnMoreMemberList(datas);
            }else{
                iCircleMemberView.p2vReturnMemberList(datas);
            }
        }else{
            if (!isMore){
                iCircleMemberView.p2vShowNoData();
            }
        }
    }

    @Override
    public void m2pNetFail() {
        iCircleMemberView.p2vNetFail();
    }

    @Override
    public void m2pNetComplete() {
        iCircleMemberView.p2vNetComplete();
    }

    @Override
    public void v2pPutGiveFollows(MemberManagementInfo.PageDatasBean.UserInfoBean userInfo) {
        iCircleMemberModel.p2mPutGiveFollows(userInfo);
    }

    @Override
    public void m2pReturnSuccesAttention() {
        iCircleMemberView.p2vReturnSuccesAttention();
    }

    @Override
    public void m2pPutGiveFollowsError(String subCode) {
        iCircleMemberView.p2vPutGiveFollowsError(subCode);
    }
}
