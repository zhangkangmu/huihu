package com.huihu.module_mine.classificationattention.classificationattentioninterface;

import com.huihu.module_mine.classificationattention.entity.ClassificationAttentionInfo;

import java.util.List;

public interface IClassificationAttentionPresenter {
    void v2pGetClassicationAttentionList();
    void m2pGetClassicationAttentionList(List<ClassificationAttentionInfo.PageDatasBean> pageDatas,boolean isMore);
    void m2pClassificationAttention();
    void m2pNetFail();

    void v2pGetMoreClassificationList(ClassificationAttentionInfo.PageDatasBean bean);

    void m2pNetComplete();
}
