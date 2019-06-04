package com.huihu.module_mine.editnewphoneverication.presenter;

import com.huihu.commonlib.utils.Constant;
import com.huihu.module_mine.editnewphoneverication.editnewphonevericationinterface.IEditNewPhoneVericationModel;
import com.huihu.module_mine.editnewphoneverication.editnewphonevericationinterface.IEditNewPhoneVericationPresenter;
import com.huihu.module_mine.editnewphoneverication.editnewphonevericationinterface.IEditNewPhoneVericationView;
import com.huihu.module_mine.editnewphoneverication.model.ImpEditNewPhoneVericationModel;
import com.huihu.module_mine.loginbyphone.entity.LoginReturnModel;
import com.huihu.module_mine.verificationcode.entity.UserSmsLoginModel;

public class ImpEditNewPhoneVericationPresenter implements IEditNewPhoneVericationPresenter {
    private final IEditNewPhoneVericationModel iEditNewPhoneVericationModel = new ImpEditNewPhoneVericationModel(this);
    private final IEditNewPhoneVericationView iEditNewPhoneVericationView;

    public ImpEditNewPhoneVericationPresenter(IEditNewPhoneVericationView iEditNewPhoneVericationView) {
        this.iEditNewPhoneVericationView = iEditNewPhoneVericationView;
    }


    @Override
    public void v2pCheckVerification(int countryCode, String text, int sendType, String phone) {
        iEditNewPhoneVericationView.p2vShowLoadingDialog();
        iEditNewPhoneVericationModel.p2mPostValidateCode(text,countryCode,sendType, phone);
    }

    @Override
    public void m2pPostValidateCodeError(String msg) {
        iEditNewPhoneVericationView.p2vDismissLoadingDialog();
        iEditNewPhoneVericationView.p2vShowToast(msg);
    }

    @Override
    public void m2pPostValidateCodeSuccess(String code, int countryCode, int sendType, String phone) {
        if(sendType == Constant.TYPE_CHANGE_PHONE){
            iEditNewPhoneVericationModel.p2mPutUpdatePhone(phone);
        } else if (sendType == Constant.TYPE_CHANGE_PASSWORD){
            iEditNewPhoneVericationView.p2vStartChangePassword();
        }
    }


    @Override
    public void m2pPutUpdatePhoneSuccess() {
        iEditNewPhoneVericationView.p2vPutUpdatePhoneSuccess();
    }
}
