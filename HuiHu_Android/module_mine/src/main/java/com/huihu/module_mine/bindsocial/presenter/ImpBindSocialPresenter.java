package com.huihu.module_mine.bindsocial.presenter;

import com.huihu.module_mine.bindsocial.bindsocialinterface.IBindSocialModel;
import com.huihu.module_mine.bindsocial.bindsocialinterface.IBindSocialPresenter;
import com.huihu.module_mine.bindsocial.bindsocialinterface.IBindSocialView;
import com.huihu.module_mine.bindsocial.model.ImpBindSocialModel;

public class ImpBindSocialPresenter implements IBindSocialPresenter {
    private final IBindSocialModel iBindSocialModel = new ImpBindSocialModel(this);
    private final IBindSocialView iBindSocialView;

    public ImpBindSocialPresenter(IBindSocialView iBindSocialView) {
        this.iBindSocialView = iBindSocialView;
    }
}
