package com.huihu.module_notification.systemmessage.presenter;

import android.text.TextUtils;

import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.systemmessage.entity.SystemMessagePageBean;
import com.huihu.module_notification.systemmessage.interfaces.SystemMessageMVP;
import com.huihu.module_notification.systemmessage.model.ImpSystemMessageModel;
import com.huihu.module_notification.systemnotification.entity.SystemMessageType;

public class ImpSystemMessagePresenter implements SystemMessageMVP.IPresenter {

    private final SystemMessageMVP.IView iView;
    private final SystemMessageMVP.IModel iModel = new ImpSystemMessageModel(this);
    private boolean hasMore;
    private int pageIndex;

    public ImpSystemMessagePresenter(SystemMessageMVP.IView iView) {
        this.iView = iView;
    }

    @Override
    public void v2pGetFirstListData() {
        pageIndex = 1;
        hasMore = true;
        iModel.p2mGetSystemMessageListNet(pageIndex);
    }

    @Override
    public void v2pGetMoreListData() {
        if (hasMore){
            iModel.p2mGetSystemMessageListNet(pageIndex + 1);
        } else {
            iView.p2vGetDataComplete();
            ToastUtil.show(R.string.module_notification_has_no_more);
        }
    }

    @Override
    public void m2pNetFail() {
        ToastUtil.show(R.string.uilib_http_request_fail);
    }

    @Override
    public void m2pGetListDataSuccess(SystemMessagePageBean pageBean) {
        if (pageBean.getPageDatas() != null && pageBean.getPageDatas().size() > 0){
            pageIndex = pageBean.getPageIndex();
            if (pageBean.getPageIndex() > 1) {
                iView.p2vShowMoreData(pageBean.getPageDatas());
            } else {
                iView.p2VShowFirstData(pageBean.getPageDatas());
            }
        } else {
            if (pageBean.getPageIndex() > 1 ){
                hasMore = false;
                ToastUtil.show(R.string.module_notification_has_no_more);
            } else {
                iView.p2vShowNoData();
            }
        }
    }

    @Override
    public void m2pGetDataComplete() {
        iView.p2vGetDataComplete();
    }

    @Override
    public void m2pGetDataFail() {
        iView.p2vShowGetDataFail();
    }

    @Override
    public void v2pStartOtherFragment(SystemMessagePageBean.SystemMessageBean bean) {
        @SystemMessageType int type = bean.getInfo().getChildType();
        if (TextUtils.isEmpty(bean.getAboutId())) return;
        switch (type) {
            case SystemMessageType.ANSWER:
                iView.p2vStartAnswer(Long.valueOf(bean.getAboutId()));
                break;
            case SystemMessageType.QUESTION:
                iView.p2vStartQuestion(Long.valueOf(bean.getAboutId()));
                break;
            case SystemMessageType.WEB:
                iView.p2vStartWeb(bean.getInfo().getUrl());
                break;
            default:
                break;
        }
    }
}
