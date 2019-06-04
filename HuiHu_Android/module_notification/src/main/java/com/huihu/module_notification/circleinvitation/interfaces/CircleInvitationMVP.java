package com.huihu.module_notification.circleinvitation.interfaces;

import com.huihu.module_notification.circleinvitation.entity.CircleInvitationPageBean;

import java.util.List;

public interface CircleInvitationMVP {
    interface IView {
        void p2vShowFirstData(List<CircleInvitationPageBean.CircleInvitationData> dataList);
        void p2vShowMoreData(List<CircleInvitationPageBean.CircleInvitationData> dataList);
        void p2vShowNoData();
        void p2vGetDataComplete();
        void p2vShowNetError();
        void p2vChangeState(CircleInvitationPageBean.CircleInvitationData data);
        void p2vStartOtherPeople(long uid);
    }
    interface IPresenter {
        void v2pGetFirstData();
        void v2pGetMoreData();
        void v2pDoSomeThings(CircleInvitationPageBean.CircleInvitationData data, boolean isAllow);
        void v2pJoinCircleSuccess(CircleInvitationPageBean.CircleInvitationData data);
        void v2pLookOtherPeople(CircleInvitationPageBean.CircleInvitationData data);
        void m2pGetDataSuccess(CircleInvitationPageBean pageBean);
        void m2pNetFail();
        void m2pGetDataFail();
        void m2pGetDataComplete();
        void m2pPutAllowManagerSuccess(CircleInvitationPageBean.CircleInvitationData data);
    }
    interface IModel {
        void p2mGetCircleInvitationListDataNet(int pageIndex);
        void p2mPutJoinCircle(CircleInvitationPageBean.CircleInvitationData data);
        void p2mPutIsAllowCircleManager(int isAllow, CircleInvitationPageBean.CircleInvitationData data);
    }
}
