package com.huihu.module_notification.circleinvitation.presenter;

import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.circleinvitation.entity.CircleInvitationPageBean;
import com.huihu.module_notification.circleinvitation.entity.CircleInvitationType;
import com.huihu.module_notification.circleinvitation.interfaces.CircleInvitationMVP;
import com.huihu.module_notification.circleinvitation.model.ImpCircleInvitationModel;
import com.huihu.uilib.def.NetDataBoolean;

import java.util.List;

public class ImpCircleInvitationPresenter implements CircleInvitationMVP.IPresenter {

    private final CircleInvitationMVP.IView iView;
    private final CircleInvitationMVP.IModel iModel = new ImpCircleInvitationModel(this);

    private int pageIndex;
    private boolean hasMore = true;

    public ImpCircleInvitationPresenter(CircleInvitationMVP.IView iView) {
        this.iView = iView;
    }

    @Override
    public void v2pGetFirstData() {
        pageIndex = 1;
        hasMore = true;
        iModel.p2mGetCircleInvitationListDataNet(pageIndex);
    }

    @Override
    public void v2pGetMoreData() {
        if (hasMore){
            iModel.p2mGetCircleInvitationListDataNet(pageIndex + 1);
        } else {
            ToastUtil.show(R.string.module_notification_has_no_more);
            iView.p2vGetDataComplete();
        }
    }




    @Override
    public void v2pDoSomeThings(CircleInvitationPageBean.CircleInvitationData data, boolean isAllow) {
        int type = data.getInfo().getChildType();
        if (type == CircleInvitationType.InviteJoinCircle){
            iModel.p2mPutJoinCircle(data);
        } else if (type == CircleInvitationType.InviteManagerCircle){
            if (isAllow){
                allowManager(data);
            } else {
                refuseManger(data);
            }
        }
    }

    @Override
    public void v2pJoinCircleSuccess(CircleInvitationPageBean.CircleInvitationData data) {
        iView.p2vChangeState(data);
    }

    @Override
    public void v2pLookOtherPeople(CircleInvitationPageBean.CircleInvitationData data) {
        iView.p2vStartOtherPeople(data.getInfo().getUserId());
    }

    @Override
    public void m2pGetDataSuccess(CircleInvitationPageBean pageBean) {
        List<CircleInvitationPageBean.CircleInvitationData> datas = pageBean.getPageDatas();
        if (datas != null && datas.size() > 0){
            pageIndex = pageBean.getPageIndex();
            if (pageBean.getPageIndex() > 1){
                iView.p2vShowMoreData(datas);
            } else {
                iView.p2vShowFirstData(datas);
            }
        } else {
            if (pageBean.getPageIndex() > 1){
                hasMore = false;
                ToastUtil.show(R.string.module_notification_has_no_more);
            } else {
                iView.p2vShowNoData();
            }
        }
    }

    @Override
    public void m2pNetFail() {
        ToastUtil.show(R.string.uilib_http_request_fail);
    }

    @Override
    public void m2pGetDataFail() {
        iView.p2vShowNetError();
    }

    @Override
    public void m2pGetDataComplete() {
        iView.p2vGetDataComplete();
    }

    @Override
    public void m2pPutAllowManagerSuccess(CircleInvitationPageBean.CircleInvitationData data) {
        iView.p2vChangeState(data);
    }

    private void allowManager(CircleInvitationPageBean.CircleInvitationData data) {
        iModel.p2mPutIsAllowCircleManager(NetDataBoolean.NET_TRUE, data);
    }

    private void refuseManger(CircleInvitationPageBean.CircleInvitationData data) {
        iModel.p2mPutIsAllowCircleManager(NetDataBoolean.NET_FALSE, data);
    }
}
