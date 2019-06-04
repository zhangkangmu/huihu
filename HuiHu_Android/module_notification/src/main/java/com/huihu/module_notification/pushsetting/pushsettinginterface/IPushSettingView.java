package com.huihu.module_notification.pushsetting.pushsettinginterface;

import com.huihu.module_notification.pushsetting.entity.PushSettingBean;

import java.util.List;

public interface IPushSettingView {
    void p2vShowPushSetting(List<PushSettingBean> beans);
    void p2vChangeSettingFail(PushSettingBean bean);
}
