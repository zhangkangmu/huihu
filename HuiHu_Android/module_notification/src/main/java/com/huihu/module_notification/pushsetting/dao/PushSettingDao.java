package com.huihu.module_notification.pushsetting.dao;

import com.huihu.commonlib.db.DBUtils;
import com.huihu.commonlib.db.LocalDBHelper;
import com.huihu.module_notification.pushsetting.entity.PushSettingBean;

import java.util.List;

/**
 * create by wangjing on 2019/3/19 0019
 * description:
 */
public class PushSettingDao {

    public static void saveAll(List<PushSettingBean> beans){
       new DBUtils.Builder<>(LocalDBHelper.getCommonDBName(), PushSettingBean.class).build().add(beans);
    }

    public static List<PushSettingBean> getALL(){
        return new DBUtils.Builder<>(LocalDBHelper.getCommonDBName(), PushSettingBean.class).build().search();
    }

    public static void updateStateById(PushSettingBean bean){
        new DBUtils.Builder<>(LocalDBHelper.getCommonDBName(), PushSettingBean.class).build().update(bean);
    }
}
