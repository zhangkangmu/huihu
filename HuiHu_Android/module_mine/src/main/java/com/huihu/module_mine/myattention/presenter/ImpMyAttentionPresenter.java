package com.huihu.module_mine.myattention.presenter;

import com.huihu.module_mine.myattention.myattentioninterface.IMyAttentionModel;
import com.huihu.module_mine.myattention.myattentioninterface.IMyAttentionPresenter;
import com.huihu.module_mine.myattention.myattentioninterface.IMyAttentionView;
import com.huihu.module_mine.myattention.model.ImpMyAttentionModel;

public class ImpMyAttentionPresenter implements IMyAttentionPresenter {
    private final IMyAttentionModel iMyAttentionModel = new ImpMyAttentionModel(this);
    private final IMyAttentionView iMyAttentionView;

    public ImpMyAttentionPresenter(IMyAttentionView iMyAttentionView) {
        this.iMyAttentionView = iMyAttentionView;
    }

}
