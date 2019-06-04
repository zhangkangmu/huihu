package com.huihu.module_notification.systemnotification.presenter;

import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.systemnotification.entity.SystemNoticePageBean;
import com.huihu.module_notification.systemnotification.systemnotificationinterface.ISystemNotificationModel;
import com.huihu.module_notification.systemnotification.systemnotificationinterface.ISystemNotificationPresenter;
import com.huihu.module_notification.systemnotification.systemnotificationinterface.ISystemNotificationView;
import com.huihu.module_notification.systemnotification.model.ImpSystemNotificationModel;

public class ImpSystemNotificationPresenter implements ISystemNotificationPresenter {
    private final ISystemNotificationModel iSystemNotificationModel = new ImpSystemNotificationModel(this);
    private final ISystemNotificationView iSystemNotificationView;
    private boolean hasMore;
    private int pageIndex;

    public ImpSystemNotificationPresenter(ISystemNotificationView iSystemNotificationView) {
        this.iSystemNotificationView = iSystemNotificationView;
    }

    @Override
    public void v2pGetFirstData() {
        pageIndex = 1;
        hasMore = true;
        iSystemNotificationModel.p2mGetSystemNoticeListNet(pageIndex);
    }

    @Override
    public void v2pGetMoreData() {
        if (hasMore){
            iSystemNotificationModel.p2mGetSystemNoticeListNet(pageIndex + 1);
        } else {
            iSystemNotificationView.p2vShowGetDataComplete();
            ToastUtil.show(R.string.module_notification_has_no_more);
        }

    }

    @Override
    public void m2pGetDataComplete() {
        iSystemNotificationView.p2vShowGetDataComplete();
    }

    @Override
    public void m2pNetFail() {
        ToastUtil.show(R.string.uilib_http_request_fail);
    }

    @Override
    public void m2pGetDataFail() {
        iSystemNotificationView.p2vShowNetError();
    }

    @Override
    public void m2pGetListDataSuccess(SystemNoticePageBean bean) {
        if (bean.getPageDatas() != null && bean.getPageDatas().size() > 0){
            pageIndex = bean.getPageIndex();
            if (bean.getPageIndex() > 1){
                iSystemNotificationView.p2vShowMoreData(bean.getPageDatas());
            } else {
                iSystemNotificationView.p2vShowFirstData(bean.getPageDatas());
            }
        } else {
            if (bean.getPageIndex() > 1){
                hasMore = false;
                ToastUtil.show(R.string.module_notification_has_no_more);
            } else {
                iSystemNotificationView.p2vShowNoData();
            }
        }
    }
}
