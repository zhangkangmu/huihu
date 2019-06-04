package com.huihu.module_home.inviteanswerlist.inviteanswerlistinterface;

import com.huihu.module_home.inviteanswer.entity.RecommendUserModel;

import java.util.List;

public interface IInviteAnswerListView {

    void p2vShowEmptyView();

    void p2vSetNoMoreData();

    void p2vShowRefreshData(List<RecommendUserModel> model);

    void p2vAddData(List<RecommendUserModel> model);

    void p2vFinishRefresh();

    void p2vFinishLoadMore();

    void p2vShowToast(String message);
}
