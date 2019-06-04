package com.huihu.module_mine.loginbypassword.presenter;

import android.text.TextUtils;

import com.huihu.commonlib.base.BaseApplication;
import com.huihu.commonlib.utils.Constant;
import com.huihu.commonlib.utils.StringUtil;
import com.huihu.commonlib.utils.ValidateTool;
import com.huihu.module_mine.loginbypassword.entity.UserLoginModel;
import com.huihu.module_mine.loginbypassword.loginbypasswordinterface.ILoginByPasswordModel;
import com.huihu.module_mine.loginbypassword.loginbypasswordinterface.ILoginByPasswordPresenter;
import com.huihu.module_mine.loginbypassword.loginbypasswordinterface.ILoginByPasswordView;
import com.huihu.module_mine.loginbypassword.model.ImpLoginByPasswordModel;
import com.huihu.uilib.evenbusBean.NotificationRefresh;
import com.wangjing.module_push.Push;

import org.greenrobot.eventbus.EventBus;

public class ImpLoginByPasswordPresenter implements ILoginByPasswordPresenter {
    private final ILoginByPasswordModel iLoginByPasswordModel = new ImpLoginByPasswordModel(this);
    private final ILoginByPasswordView iLoginByPasswordView;

    public ImpLoginByPasswordPresenter(ILoginByPasswordView iLoginByPasswordView) {
        this.iLoginByPasswordView = iLoginByPasswordView;
    }

    private boolean mIsPhoneEmpty = true;//手机号输入是否为空
    private boolean mIsPswEmpty = true;//密码输入是否为空

    public static final int TYPE_PHONE = 1;
    public static final int TYPE_PASSWORD = 2;

    @Override
    public void v2pCheckVaild(int inputType, String string) {
        if (TextUtils.isEmpty(string)) {
            if (inputType == TYPE_PHONE) {
                mIsPhoneEmpty = true;
            }
            if (inputType == TYPE_PASSWORD) {
                mIsPswEmpty = true;
            }
        } else {
            if (inputType == TYPE_PHONE) {
                mIsPhoneEmpty = false;
                if (ValidateTool.getInstance().checkMobile(string)) {
                    iLoginByPasswordView.p2vShowPhoneCorrect();
                } else {
                    iLoginByPasswordView.p2vHidePhoneCorrect();
                }
            }
            if (inputType == TYPE_PASSWORD) {
                mIsPswEmpty = false;
            }
        }

        if (!mIsPhoneEmpty && !mIsPswEmpty) {
            iLoginByPasswordView.p2vSetNextVaild();
        } else {
            iLoginByPasswordView.p2vSetNextInvalid();
        }
    }

    /**
     * 请求接口前的有效性检查
     *
     * @param phone    手机号码
     * @param password 密码
     */
    @Override
    public void v2pCheckValidByNext(boolean isConfirm, String phone, String password) {
        if (!isConfirm) {
            iLoginByPasswordView.showToast("请同意用户服务协议");
        } else {
            if (!ValidateTool.getInstance().checkMobile(phone)) {
                iLoginByPasswordView.showToast("手机号/汇聊号输入错误");
            } else {
                iLoginByPasswordView.p2vShowLoadingDialog();
                UserLoginModel model = new UserLoginModel();
                model.setAccount(phone);
                model.setClientIp("192.168.1.1");
                model.setClientType("2");
                model.setDeviceId("123456");
                model.setDeviceModel("654231");
                model.setLoginProduct("9");
                model.setPassword(StringUtil.getMD5(password));
                iLoginByPasswordModel.p2mPostLoginUser(model);
            }
        }
    }

    @Override
    public void v2pForgetPsw(String phone) {
        iLoginByPasswordView.p2vShowLoadingDialog();
        iLoginByPasswordModel.p2mPostSendCode(Constant.TYPE_SEND, 6541, Constant.TYPE_DEVICE, phone);
    }

    @Override
    public void m2pStartVerification() {
        iLoginByPasswordView.p2vDissmissLoadingDialog();
        iLoginByPasswordView.p2vStartVerification();
    }

    @Override
    public void m2pLoginFailed(String msg) {
        iLoginByPasswordView.p2vDissmissLoadingDialog();
        iLoginByPasswordView.p2vShowToast(msg);
    }

    @Override
    public void m2pLoginSuccess(String message) {
        iLoginByPasswordView.p2vDissmissLoadingDialog();
        iLoginByPasswordView.p2vShowToast(message);
        EventBus.getDefault().post(new NotificationRefresh());
        Push.initUser(BaseApplication.getApplication());
    }
}
