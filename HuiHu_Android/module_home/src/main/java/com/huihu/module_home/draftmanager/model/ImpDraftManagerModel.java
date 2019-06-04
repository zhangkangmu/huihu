package com.huihu.module_home.draftmanager.model;

import com.huihu.module_home.draftmanager.draftmanagerinterface.IDraftManagerModel;
import com.huihu.module_home.draftmanager.draftmanagerinterface.IDraftManagerPresenter;

public class ImpDraftManagerModel implements IDraftManagerModel {
    private final IDraftManagerPresenter iDraftManagerPresenter;

    public ImpDraftManagerModel(IDraftManagerPresenter iDraftManagerPresenter) {
        this.iDraftManagerPresenter = iDraftManagerPresenter;
    }
}
