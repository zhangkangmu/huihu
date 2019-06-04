package com.huihu.module_mine.editname.presenter;

import com.huihu.module_mine.editname.editnameinterface.IEditNameModel;
import com.huihu.module_mine.editname.editnameinterface.IEditNamePresenter;
import com.huihu.module_mine.editname.editnameinterface.IEditNameView;
import com.huihu.module_mine.editname.model.ImpEditNameModel;

public class ImpEditNamePresenter implements IEditNamePresenter {
    private final IEditNameModel iEditNameModel = new ImpEditNameModel(this);
    private final IEditNameView iEditNameView;

    public ImpEditNamePresenter(IEditNameView iEditNameView) {
        this.iEditNameView = iEditNameView;
    }

    public void v2pPutUpdateName(String name){
        iEditNameModel.p2mGetNickIsUsed(name);
    }

    public void v2pGetNickIsUsed(String name){
        iEditNameModel.p2mGetNickIsUsed(name);
    }

    public void m2pNickHasUsed(){
        iEditNameView.p2vNameHasUsed();
    }
    public void m2pPutUpdateNameSucess(){
        iEditNameView.p2vPutUpdateNameSucess();
    }
    public void m2pPutUpdateNameError(String error){
        iEditNameView.p2vPutUpdateNameError(error);
    }

}
