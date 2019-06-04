package com.huihu.module_notification.fans.presenter;

import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.fans.FansConstant;
import com.huihu.module_notification.fans.entity.FollowPageBean;
import com.huihu.module_notification.fans.fansinterface.IFansModel;
import com.huihu.module_notification.fans.fansinterface.IFansPresenter;
import com.huihu.module_notification.fans.fansinterface.IFansView;
import com.huihu.module_notification.fans.model.ImpFansModel;
import com.huihu.uilib.def.NetDataBoolean;

public class ImpFansPresenter implements IFansPresenter {
    private final IFansModel iFansModel = new ImpFansModel(this);
    private final IFansView iFansView;
    private boolean hasMoreFans = true;
    private boolean hasMoreAttention = true;

    public ImpFansPresenter(IFansView iFansView) {
        this.iFansView = iFansView;
    }

    @Override
    public void v2pGetFirstData(int type) {
        if (type == FansConstant.FOLLOW_ME) {
            hasMoreFans = true;
        } else {
            hasMoreAttention = true;
        }
        iFansModel.p2mGetFansOrFollowListNet(0, type, false);
    }

    @Override
    public void v2pGetMoreData(int type, FollowPageBean.DataBean bean) {
        if (type == FansConstant.FOLLOW_ME) {
           if (!hasMoreFans){
               ToastUtil.show(R.string.module_notification_has_no_more);
               iFansView.p2vGetDataComplete();
               return;
           }
        } else {
            if (!hasMoreAttention){
                ToastUtil.show(R.string.module_notification_has_no_more);
                iFansView.p2vGetDataComplete();
                return;
            }
        }
        iFansModel.p2mGetFansOrFollowListNet(bean.getFollowId(), type, true);
    }

    @Override
    public void v2pPutGiveFollows(int type, FollowPageBean.DataBean bean) {
        iFansModel.p2mPutGiveFollows(type, bean);
    }

    @Override
    public void m2pGetFansOrFollowSuccess(int type, boolean isMore, FollowPageBean bean) {
        if (bean.getPageDatas() == null || bean.getPageDatas().size() == 0) {
            if (isMore) {
                if (type == FansConstant.FOLLOW_ME){
                    hasMoreFans = false;
                } else {
                    hasMoreAttention = false;
                }
                ToastUtil.show(R.string.module_notification_has_no_more);
            } else {
                iFansView.p2vShowNoData(type);
            }
        } else {
//            清洗数据，是否关注本地维持
            for (FollowPageBean.DataBean dataBean : bean.getPageDatas()) {
                if (type == FansConstant.MY_FOLLOW) {
                    dataBean.setFollow(true);
                } else {
                    dataBean.setFollow(dataBean.getIsMutual() == NetDataBoolean.NET_TRUE);
                }
            }
            if (isMore) {
                iFansView.p2vShowMoreData(type, bean.getPageDatas());
            } else {
                iFansView.p2vShowFirstData(type, bean.getPageDatas());
            }
        }
    }

    @Override
    public void m2pPutGiveFollowsSuccess(int type, FollowPageBean.DataBean bean) {
        iFansView.p2vChangeFollowViewState(type, bean);
    }

    @Override
    public void m2pNetFail() {
        ToastUtil.show(R.string.uilib_http_request_fail);
    }

    @Override
    public void m2pGetFansFail(int type) {
        iFansView.p2vShowNoNet(type);
    }

    @Override
    public void m2pGetDataComplete() {
        iFansView.p2vGetDataComplete();
    }

    @Override
    public void v2pLookOtherPeople(FollowPageBean.DataBean bean) {
        iFansView.p2vStartOtherPeople(bean.getUserId());
    }
}
