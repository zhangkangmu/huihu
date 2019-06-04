package com.huihu.module_mine.loginbyphone.presenter;

import android.text.TextUtils;

import com.huihu.commonlib.utils.ValidateTool;
import com.huihu.module_mine.loginbyphone.logininterface.ILoginModel;
import com.huihu.module_mine.loginbyphone.logininterface.ILoginPresenter;
import com.huihu.module_mine.loginbyphone.logininterface.ILoginView;
import com.huihu.module_mine.loginbyphone.model.ImpLoginModel;

public class ImpLoginPresenter implements ILoginPresenter {
    private final ILoginModel iLoginModel = new ImpLoginModel(this);
    private final ILoginView iLoginView;

    public ImpLoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    /**
     * 检查输入框是否有输入
     *
     * @param phone 用户输入的手机号码
     */
    @Override
    public void v2pCheckPhoneEmpty(String phone) {
        if (!TextUtils.isEmpty(phone)) {
            iLoginView.p2vSetNextClickAble();
        } else {
            iLoginView.p2vSetNextUnClickAble();
        }
    }

    /**
     * 检查手机号码是否有效
     *
     * @param countryCode 国家编码
     * @param phone       手机号码
     * @param sendType    客户端类型
     */
    @Override
    public void v2pCheckPhoneValid(boolean isConfirm, int countryCode, String phone, int sendType) {
        if (!isConfirm) {
            iLoginView.p2vShowToast("请勾选协议");
        } else {
            if (ValidateTool.getInstance().checkMobile(phone)) {
                iLoginView.p2vShowLoadDialog();
                iLoginModel.p2mSendLoginSms(countryCode, phone, sendType);
            } else {
                iLoginView.p2vShowToast("请输入正确的手机号");
            }
        }
    }

    @Override
    public void m2pSendLoginSmsSuccess() {
        iLoginView.p2vShowToast("发送成功");
        iLoginView.p2vDismissDialog();
        iLoginView.p2vStartVerifcation();
    }

    @Override
    public void m2pSendLoginSmsFailed(int code, String msg) {
        iLoginView.p2vDismissDialog();
        iLoginView.p2vShowToast(msg);
    }

    @Override
    public void m2pSubcodeWrong(String message) {
        iLoginView.p2vDismissDialog();
        iLoginView.p2vShowToast(message);
    }
}
