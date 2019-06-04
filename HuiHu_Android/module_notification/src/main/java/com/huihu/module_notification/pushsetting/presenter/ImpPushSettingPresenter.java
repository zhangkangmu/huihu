package com.huihu.module_notification.pushsetting.presenter;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.pushsetting.entity.PostPusSettingBean;
import com.huihu.module_notification.pushsetting.entity.PushSettingBean;
import com.huihu.module_notification.pushsetting.pushsettinginterface.IPushSettingModel;
import com.huihu.module_notification.pushsetting.pushsettinginterface.IPushSettingPresenter;
import com.huihu.module_notification.pushsetting.pushsettinginterface.IPushSettingView;
import com.huihu.module_notification.pushsetting.model.ImpPushSettingModel;
import com.huihu.uilib.util.CheckLoginUtil;

import java.util.ArrayList;
import java.util.List;

public class ImpPushSettingPresenter implements IPushSettingPresenter {
    private final IPushSettingModel iPushSettingModel = new ImpPushSettingModel(this);
    private final IPushSettingView iPushSettingView;

    public ImpPushSettingPresenter(IPushSettingView iPushSettingView) {
        this.iPushSettingView = iPushSettingView;
    }

    @Override
    public void m2pGetPushSettingSuccess(List<PushSettingBean> beans) {
        if (beans != null && beans.size() > 0) {
            iPushSettingView.p2vShowPushSetting(beans);
        } else {
            //            TODO
        }
    }

    @Override
    public void m2pNetFail() {
        ToastUtil.show(R.string.uilib_http_request_fail);
    }

    @Override
    public void m2pUpdatePushSettingFail(PushSettingBean bean) {
        iPushSettingView.p2vChangeSettingFail(bean);
    }

    @Override
    public void v2pGetPushSetting() {
        if (CheckLoginUtil.checkLogin()) {
            iPushSettingModel.p2mGetUserPushSettingNet();
        } else {
            iPushSettingModel.p2mGetPushSettingDb();
        }
    }

    @Override
    public void v2pUpdatePushSetting(PushSettingBean bean) {
        if (CheckLoginUtil.checkLogin()) {
            List<PostPusSettingBean.ListBean> list = new ArrayList<>();
            list.add(new PostPusSettingBean.ListBean(bean.getNoticeId(), bean.getStatus()));
            iPushSettingModel.p2mUpdatePushSettingNet(new PostPusSettingBean(SPUtils.getInstance().getCurrentUid(), list), bean);
        } else {
            iPushSettingModel.p2mUpdatePushSettingDb(bean);
        }
    }
}
