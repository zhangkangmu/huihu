package com.huihu.module_mine.editsignature.presenter;

import com.huihu.module_mine.editsignature.editsignatureinterface.IEditSignatureModel;
import com.huihu.module_mine.editsignature.editsignatureinterface.IEditSignaturePresenter;
import com.huihu.module_mine.editsignature.editsignatureinterface.IEditSignatureView;
import com.huihu.module_mine.editsignature.model.ImpEditSignatureModel;

public class ImpEditSignaturePresenter implements IEditSignaturePresenter {
    private final IEditSignatureModel iEditSignatureModel = new ImpEditSignatureModel(this);
    private final IEditSignatureView iEditSignatureView;

    public ImpEditSignaturePresenter(IEditSignatureView iEditSignatureView) {
        this.iEditSignatureView = iEditSignatureView;
    }

    public void v2pPutUpdateUserSignature(String signature){
        iEditSignatureModel.p2mPutUpdateUserSignature(signature);
    }

    public void m2pPutUpdateUserSignatureSuccess(){
        iEditSignatureView.p2vPutUpdateUserSignatureSuccess();
    }

    public void m2pPutUpdateUserSignatureError(){
        iEditSignatureView.p2vPutUpdateUserSignatureError();
    }
}
