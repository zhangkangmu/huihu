package com.huihu.module_mine.myattention.model;

import com.huihu.module_mine.myattention.myattentioninterface.IMyAttentionModel;
import com.huihu.module_mine.myattention.myattentioninterface.IMyAttentionPresenter;

public class ImpMyAttentionModel implements IMyAttentionModel {
    private final IMyAttentionPresenter iMyAttentionPresenter;

    public ImpMyAttentionModel(IMyAttentionPresenter iMyAttentionPresenter) {
        this.iMyAttentionPresenter = iMyAttentionPresenter;
    }
}
