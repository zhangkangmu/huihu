package com.huihu.module_notification.pushsetting.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.base.BaseApplication;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.task.SimpleTask;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.pushsetting.dao.PushSettingDao;
import com.huihu.module_notification.pushsetting.entity.GetUserPushSettingSubcode;
import com.huihu.module_notification.pushsetting.entity.PostPusSettingBean;
import com.huihu.module_notification.pushsetting.entity.PostUserNoticesSubcode;
import com.huihu.module_notification.pushsetting.entity.PushSettingBean;
import com.huihu.module_notification.pushsetting.pushsettinginterface.IPushSettingModel;
import com.huihu.module_notification.pushsetting.pushsettinginterface.IPushSettingPresenter;

import java.util.ArrayList;
import java.util.List;

public class ImpPushSettingModel implements IPushSettingModel {


    private final IPushSettingPresenter iPushSettingPresenter;


    public ImpPushSettingModel(IPushSettingPresenter iPushSettingPresenter) {
        this.iPushSettingPresenter = iPushSettingPresenter;
    }

    @Override
    public void p2mGetUserPushSettingNet() {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.GetUserPushSetting
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("uid", SPUtils.getInstance().getCurrentUid() + "");
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                String subcode = returnModel.getSubCode();
                switch (subcode){
                    case GetUserPushSettingSubcode.Success:
                        List<PushSettingBean> beans = new Gson().fromJson(returnModel.getBodyMessage()
                                , new TypeToken<List<PushSettingBean>>() {
                                }.getType());
                        iPushSettingPresenter.m2pGetPushSettingSuccess(beans);
                        break;
                    case GetUserPushSettingSubcode.ParameterError:
                        break;
                    case GetUserPushSettingSubcode.BusinessError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iPushSettingPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mUpdatePushSettingNet(final PostPusSettingBean bean, final PushSettingBean localBean) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.UserNotice.PostUserPushSetting
                , NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(bean));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                String subcode = returnModel.getSubCode();
                switch (subcode){
                    case PostUserNoticesSubcode.Success:
                        break;
                    case PostUserNoticesSubcode.ParameterError:
                    case PostUserNoticesSubcode.BusinessError:
                        iPushSettingPresenter.m2pUpdatePushSettingFail(localBean);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iPushSettingPresenter.m2pNetFail();
                iPushSettingPresenter.m2pUpdatePushSettingFail(localBean);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mGetPushSettingDb() {
        List<PushSettingBean> beans = PushSettingDao.getALL();
        if (beans == null || beans.size() == 0) {
            beans = new ArrayList<>();
            String[] types = BaseApplication.getApplication().getResources().getStringArray(R.array.module_notification_push_setting_type);
            for (int i = 0; i < types.length; i++) {
                PushSettingBean bean = new PushSettingBean(i + 1, types[i], 1);
                beans.add(bean);
            }
            final List<PushSettingBean> beansDb = new ArrayList<>(beans);
            SimpleTask.runInBackground(new Runnable() {
                @Override
                public void run() {
                    PushSettingDao.saveAll(beansDb);
                }
            });
            iPushSettingPresenter.m2pGetPushSettingSuccess(beans);
        } else {
            iPushSettingPresenter.m2pGetPushSettingSuccess(beans);
        }
    }

    @Override
    public void p2mUpdatePushSettingDb(PushSettingBean bean) {
        PushSettingDao.updateStateById(bean);
    }
}
