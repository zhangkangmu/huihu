package com.huihu.module_home.inviteanswerlist.presenter;

import com.huihu.module_home.inviteanswer.entity.RecommendUserModel;
import com.huihu.module_home.inviteanswerlist.inviteanswerlistinterface.IInviteAnswerListModel;
import com.huihu.module_home.inviteanswerlist.inviteanswerlistinterface.IInviteAnswerListPresenter;
import com.huihu.module_home.inviteanswerlist.inviteanswerlistinterface.IInviteAnswerListView;
import com.huihu.module_home.inviteanswerlist.model.ImpInviteAnswerListModel;

import java.util.List;

public class ImpInviteAnswerListPresenter implements IInviteAnswerListPresenter {
    private final IInviteAnswerListModel iInviteAnswerListModel = new ImpInviteAnswerListModel(this);
    private final IInviteAnswerListView iInviteAnswerListView;
    private int mPageIndex;

    public ImpInviteAnswerListPresenter(IInviteAnswerListView iInviteAnswerListView) {
        this.iInviteAnswerListView = iInviteAnswerListView;
    }

    @Override
    public void v2pGetRecentLogingUser(int pageIndex, int pageSize, int uid) {
        mPageIndex = pageIndex;
        iInviteAnswerListModel.p2mGetRecentLogingUser(pageIndex, pageSize, uid);
    }

    @Override
    public void m2pGetRecentLogingUserSuccess(List<RecommendUserModel> model) {
        if (mPageIndex == 1) {//下拉刷新
            if (model == null || model.isEmpty()) {//请求返回的数据为空
                iInviteAnswerListView.p2vShowEmptyView();
            } else {
                if (model.size() < 20) {
                    iInviteAnswerListView.p2vSetNoMoreData();
                }
                iInviteAnswerListView.p2vShowRefreshData(model);
            }
        } else {//加载更多
            if (model == null || model.isEmpty()) {
                iInviteAnswerListView.p2vSetNoMoreData();
            } else {
                if (model.size() < 20) {
                    iInviteAnswerListView.p2vSetNoMoreData();
                }
                iInviteAnswerListView.p2vAddData(model);
            }
        }
        iInviteAnswerListView.p2vFinishRefresh();
        iInviteAnswerListView.p2vFinishLoadMore();
    }

    @Override
    public void m2petRecentLogingUserFail(String message) {
        iInviteAnswerListView.p2vShowToast(message);
        iInviteAnswerListView.p2vFinishRefresh();
        iInviteAnswerListView.p2vFinishLoadMore();
    }
}
