package com.huihu.module_home.questiondraft.questiondraftinterface;

import com.huihu.module_home.questiondraft.model.GetDraftModel;

import java.util.List;

public interface IQuestionDraftView {

    void p2vShowToast(String message);

    void p2vShowRefreshData(List<GetDraftModel.PageDatasBean> datas);

    List<GetDraftModel.PageDatasBean> getData();

    void p2vShowEmptyView();

    void p2vSetNoMoreData();

    void p2vFinishRefresh();

    void p2vShowNoNetWork();

    void p2vFinishLoadMore();

    void p2vAddData(List<GetDraftModel.PageDatasBean> datas);
}
