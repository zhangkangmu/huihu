package com.huihu.module_home.draftmanager.presenter;

import com.huihu.module_home.draftmanager.draftmanagerinterface.IDraftManagerModel;
import com.huihu.module_home.draftmanager.draftmanagerinterface.IDraftManagerPresenter;
import com.huihu.module_home.draftmanager.draftmanagerinterface.IDraftManagerView;
import com.huihu.module_home.draftmanager.model.ImpDraftManagerModel;

public class ImpDraftManagerPresenter implements IDraftManagerPresenter {
    private final IDraftManagerModel iDraftManagerModel = new ImpDraftManagerModel(this);
    private final IDraftManagerView iDraftManagerView;

    public ImpDraftManagerPresenter(IDraftManagerView iDraftManagerView) {
        this.iDraftManagerView = iDraftManagerView;
    }
}
