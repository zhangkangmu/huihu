package com.huihu.module_mine.editname.editnameinterface;

public interface IEditNamePresenter {
    public void v2pPutUpdateName(String name);
    public void v2pGetNickIsUsed(String name);
    public void m2pNickHasUsed();
    public void m2pPutUpdateNameSucess();
    public void m2pPutUpdateNameError(String error);


}
