package com.huihu.module_mine.verificationcode.presenter;

import android.os.Build;

import com.huihu.commonlib.base.BaseApplication;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.loginbyphone.entity.LoginReturnModel;
import com.huihu.module_mine.verificationcode.entity.UserSmsLoginModel;
import com.huihu.module_mine.verificationcode.model.ImpVerificationCodeModel;
import com.huihu.module_mine.verificationcode.verificationcodeinterface.IVerificationCodeModel;
import com.huihu.module_mine.verificationcode.verificationcodeinterface.IVerificationCodePresenter;
import com.huihu.module_mine.verificationcode.verificationcodeinterface.IVerificationCodeView;
import com.huihu.uilib.evenbusBean.NotificationRefresh;
import com.wangjing.module_push.Push;

import org.greenrobot.eventbus.EventBus;

public class ImpVerificationCodePresenter implements IVerificationCodePresenter {
    private final IVerificationCodeModel iVerificationCodeModel = new ImpVerificationCodeModel(this);
    private final IVerificationCodeView iVerificationCodeView;

    public ImpVerificationCodePresenter(IVerificationCodeView iVerificationCodeView) {
        this.iVerificationCodeView = iVerificationCodeView;
    }

    @Override
    public void v2pCheckVerification(int countryCode, String text, int sendType, String phone) {
        iVerificationCodeView.p2vShowLoadingDialog();
        if (sendType == 2) {//修改密码
            iVerificationCodeModel.p2mPostValidateCode(text, countryCode, sendType, phone);
        } else if (sendType == 9) {//登录
            UserSmsLoginModel userSmsLoginModel = new UserSmsLoginModel();
            userSmsLoginModel.setClientType(9);
            userSmsLoginModel.setCountryCode(countryCode + "");
            userSmsLoginModel.setDeviceId(SPUtils.getInstance().getDeviceId());
            userSmsLoginModel.setDeviceModel(Build.MODEL);
            userSmsLoginModel.setLoginIP("192.168.1.1");
            userSmsLoginModel.setEquipmentType(2);
            userSmsLoginModel.setPhoneNumber(phone);
            userSmsLoginModel.setVcode(text);
            iVerificationCodeModel.p2mPostLoginUserBySms(userSmsLoginModel);
        }
    }

    @Override
    public void m2pStartReset() {
        iVerificationCodeView.p2vDismissLoadingDialog();
        iVerificationCodeView.p2vStartReset();
    }

    @Override
    public void m2pVerificationFailed(String msg) {
        iVerificationCodeView.p2vDismissLoadingDialog();
        iVerificationCodeView.p2vClearInput();
        iVerificationCodeView.p2vShowToast(msg);
    }

    @Override
    public void m2pLoginBySmsSuccess(LoginReturnModel loginReturnModel) {
        iVerificationCodeView.p2vDismissLoadingDialog();
        iVerificationCodeView.p2vFinishLogin();//关掉登录页面
        iVerificationCodeModel.p2mSaveUid(loginReturnModel.getUid());
        EventBus.getDefault().post(new NotificationRefresh());
        Push.initUser(BaseApplication.getApplication());
    }
}
