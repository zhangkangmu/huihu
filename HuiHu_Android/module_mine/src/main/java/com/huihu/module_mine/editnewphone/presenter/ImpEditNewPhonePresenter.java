package com.huihu.module_mine.editnewphone.presenter;

import android.text.TextUtils;

import com.huihu.commonlib.utils.ValidateTool;
import com.huihu.module_mine.editnewphone.editnewphoneinterface.IEditNewPhoneModel;
import com.huihu.module_mine.editnewphone.editnewphoneinterface.IEditNewPhonePresenter;
import com.huihu.module_mine.editnewphone.editnewphoneinterface.IEditNewPhoneView;
import com.huihu.module_mine.editnewphone.model.ImpEditNewPhoneModel;

public class ImpEditNewPhonePresenter implements IEditNewPhonePresenter {
    private final IEditNewPhoneModel iEditNewPhoneModel = new ImpEditNewPhoneModel(this);
    private final IEditNewPhoneView iEditNewPhoneView;

    private static final int CLIENT_TYPE = 9;
    private static final int COUNTRY_CODE = 6541;
    private static final int SEND_TYPE = 3;

    public ImpEditNewPhonePresenter(IEditNewPhoneView iEditNewPhoneView) {
        this.iEditNewPhoneView = iEditNewPhoneView;
    }

    /**
     * 检查输入框是否有输入
     *
     * @param phone 用户输入的手机号码
     */
    @Override
    public void v2pCheckPhoneEmpty(String phone) {
        if (!TextUtils.isEmpty(phone)) {
            iEditNewPhoneView.p2vSetNextClickAble();
        } else {
            iEditNewPhoneView.p2vSetNextUnClickAble();
        }
    }

    /**
     * 检查手机号码是否有效
     * @param countryCode 国家编码
     * @param phone       手机号码
     * @param sendType    客户端类型
     */
    @Override
    public void v2pCheckPhoneValid(int countryCode, String phone, int sendType) {
        if (ValidateTool.getInstance().checkMobile(phone)) {
            iEditNewPhoneView.p2vShowLoadDialog();
            iEditNewPhoneModel.p2mPostSendCode(CLIENT_TYPE,countryCode, sendType, phone );
        } else {
            iEditNewPhoneView.p2vShowToast("请输入正确的手机号码");
        }
    }

    @Override
    public void m2pPostSendCodeSuccess() {
        iEditNewPhoneView.p2vDismissDialog();
        iEditNewPhoneView.p2vStartVerifcation();
    }

    @Override
    public void m2pPostSendCodeFailed(int code, String msg) {
        iEditNewPhoneView.p2vDismissDialog();
        iEditNewPhoneView.p2vShowToast(msg);
    }

    @Override
    public void m2pSubcodeWrong(String message) {
        iEditNewPhoneView.p2vDismissDialog();
        iEditNewPhoneView.p2vShowToast(message);
    }
}
