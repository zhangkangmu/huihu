package com.huihu.module_notification.pushsetting.pushsettinginterface;

import com.huihu.module_notification.pushsetting.entity.PushSettingBean;

import java.util.List;

public interface IPushSettingPresenter {
    void m2pGetPushSettingSuccess(List<PushSettingBean> beans);
    void m2pNetFail();
    void m2pUpdatePushSettingFail(PushSettingBean bean);
    void v2pGetPushSetting();
    void v2pUpdatePushSetting(PushSettingBean bean);
}
