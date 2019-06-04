package com.huihu.module_mine.resetpassword.presenter;

import android.text.TextUtils;

import com.huihu.commonlib.utils.StringUtil;
import com.huihu.commonlib.utils.ValidateTool;
import com.huihu.module_mine.resetpassword.resetpasswordinterface.IResetPasswordModel;
import com.huihu.module_mine.resetpassword.resetpasswordinterface.IResetPasswordPresenter;
import com.huihu.module_mine.resetpassword.resetpasswordinterface.IResetPasswordView;
import com.huihu.module_mine.resetpassword.model.ImpResetPasswordModel;

public class ImpResetPasswordPresenter implements IResetPasswordPresenter {
    private final IResetPasswordModel iResetPasswordModel = new ImpResetPasswordModel(this);
    private final IResetPasswordView iResetPasswordView;

    public ImpResetPasswordPresenter(IResetPasswordView iResetPasswordView) {
        this.iResetPasswordView = iResetPasswordView;
    }

    @Override
    public void v2pResetPsw(String phone, String password, String vcode) {
        boolean checkpwdword = ValidateTool.getInstance().checkpwdword(password);
        if (checkpwdword) {
            iResetPasswordView.p2vShowLoadingDialog();
            iResetPasswordModel.p2mPutUpdatePasswordByCode(phone, StringUtil.getMD5(password), vcode);
        } else {
            iResetPasswordView.p2vShowToast("密码由6-20位数字、字母或常用符号组成，且须包含2种以上");
        }
    }

    @Override
    public void m2pHttpRequestFailed(String msg) {
        iResetPasswordView.p2vDimissLoadingDialog();
        iResetPasswordView.p2vShowToast(msg);
    }

    @Override
    public void m2pUpdatePswSuccess(String message) {
        iResetPasswordView.p2vFinish();
        iResetPasswordView.p2vDimissLoadingDialog();
        iResetPasswordView.p2vShowToast(message);
    }

    @Override
    public void v2pCheckNextState(String password) {
        if (TextUtils.isEmpty(password)) {
            iResetPasswordView.p2vSetNextUnClickAble();
        } else {
            iResetPasswordView.p2vSetNextClickAble();
        }
    }
}
