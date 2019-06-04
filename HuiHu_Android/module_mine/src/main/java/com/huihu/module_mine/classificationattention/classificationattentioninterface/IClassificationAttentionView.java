package com.huihu.module_mine.classificationattention.classificationattentioninterface;

import com.huihu.module_mine.classificationattention.entity.ClassificationAttentionInfo;

import java.util.List;

public interface IClassificationAttentionView {
    void p2vGetClassicationAttentionList(List<ClassificationAttentionInfo.PageDatasBean> pageDatas);

    void p2vClassificationAttention();

    void p2vNetFail();

    void p2vShowNoData();

    void p2vReturnMoreClassicationList(List<ClassificationAttentionInfo.PageDatasBean> pageDatas);

    void p2vNetComplete();
}
