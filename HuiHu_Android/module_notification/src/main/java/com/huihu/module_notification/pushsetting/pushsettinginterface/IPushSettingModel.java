package com.huihu.module_notification.pushsetting.pushsettinginterface;

import com.huihu.module_notification.pushsetting.entity.PostPusSettingBean;
import com.huihu.module_notification.pushsetting.entity.PushSettingBean;


public interface IPushSettingModel {
    void p2mGetUserPushSettingNet();
    void p2mUpdatePushSettingNet(PostPusSettingBean bean, PushSettingBean localBean);
    void p2mGetPushSettingDb();
    void p2mUpdatePushSettingDb(PushSettingBean bean);
}
