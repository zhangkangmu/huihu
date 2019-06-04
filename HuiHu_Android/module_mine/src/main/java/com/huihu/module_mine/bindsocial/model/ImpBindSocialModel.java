package com.huihu.module_mine.bindsocial.model;

import com.huihu.module_mine.bindsocial.bindsocialinterface.IBindSocialModel;
import com.huihu.module_mine.bindsocial.bindsocialinterface.IBindSocialPresenter;

public class ImpBindSocialModel implements IBindSocialModel {
    private final IBindSocialPresenter iBindSocialPresenter;

    public ImpBindSocialModel(IBindSocialPresenter iBindSocialPresenter) {
        this.iBindSocialPresenter = iBindSocialPresenter;
    }
}
