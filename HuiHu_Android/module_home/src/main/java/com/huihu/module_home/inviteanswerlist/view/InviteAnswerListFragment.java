package com.huihu.module_home.inviteanswerlist.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.Constant;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.inviteanswer.entity.RecommendUserModel;
import com.huihu.module_home.inviteanswer.view.InviteAnswerFragment;
import com.huihu.module_home.inviteanswerlist.inviteanswerlistinterface.IInviteAnswerListPresenter;
import com.huihu.module_home.inviteanswerlist.inviteanswerlistinterface.IInviteAnswerListView;
import com.huihu.module_home.inviteanswerlist.presenter.ImpInviteAnswerListPresenter;
import com.huihu.module_home.inviteanswerlist.view.adapter.InviteAnswerAdapter;
import com.huihu.uilib.statelayout.StateLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class InviteAnswerListFragment extends BaseFragment implements IInviteAnswerListView {
    private final IInviteAnswerListPresenter iInviteAnswerListPresenter = new ImpInviteAnswerListPresenter(this);

    Unbinder unbinder;
    @BindView(R2.id.rv_invite)
    RecyclerView mRvInvite;
    @BindView(R2.id.refresh)
    SmartRefreshLayout mRefresh;

    private InviteAnswerAdapter mAdapter;
    private StateLayout mStateLayout;

    private int mIndex = 1;
    private int mType;

    public static InviteAnswerListFragment newInstance(int type, long questionId) {
        InviteAnswerListFragment fragment = new InviteAnswerListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putLong("questionId", questionId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_invite, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            mType = bundle.getInt("type");
            long questionId = bundle.getLong("questionId");
            switch (mType) {
                case InviteAnswerFragment.TYPE_RECOMMEND:
                    iInviteAnswerListPresenter.v2pGetRecentLogingUser(mIndex, Constant.PAGE_SIZE, SPUtils.getInstance().getCurrentUid());
                    break;
                case InviteAnswerFragment.TYPE_ATTENTION:
//                    iInviteAnswerListPresenter.v2pGetInvitationList();
                    break;
            }
        }
    }

    private void initView() {
        initStateLayout();
        initRefresh();
        initRecyclerView();
    }

    private void initStateLayout() {
        mStateLayout = new StateLayout.Builder(mRefresh).create();
        mStateLayout.showLoadingData();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        mRvInvite.setLayoutManager(layoutManager);
        mAdapter = new InviteAnswerAdapter(_mActivity,iInviteAnswerListPresenter);
        mRvInvite.setAdapter(mAdapter);
    }

    private void initRefresh() {
        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mIndex++;
                switch (mType) {
                    case InviteAnswerFragment.TYPE_RECOMMEND://推荐关注
                        iInviteAnswerListPresenter.v2pGetRecentLogingUser(mIndex, Constant.PAGE_SIZE, SPUtils.getInstance().getCurrentUid());
                        break;
                    case InviteAnswerFragment.TYPE_ATTENTION://我的关注

                        break;
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mIndex = 1;
                switch (mType) {
                    case InviteAnswerFragment.TYPE_RECOMMEND://推荐关注
                        iInviteAnswerListPresenter.v2pGetRecentLogingUser(mIndex, Constant.PAGE_SIZE, SPUtils.getInstance().getCurrentUid());
                        break;
                    case InviteAnswerFragment.TYPE_ATTENTION://我的关注

                        break;

                }
            }
        });
    }

    @Override
    public boolean onBackPressedSupport() {
        ((SupportFragment) getParentFragment()).pop();
        return true;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void p2vShowEmptyView() {
        mStateLayout.showEmptyData();
    }

    @Override
    public void p2vSetNoMoreData() {
        mRefresh.setEnableLoadMore(false);
        mAdapter.setNoMoreDataItem(true);
    }

    @Override
    public void p2vShowRefreshData(List<RecommendUserModel> model) {
        mAdapter.setData(model);
        mAdapter.notifyDataSetChanged();
        mStateLayout.showContent();
    }

    @Override
    public void p2vAddData(List<RecommendUserModel> model) {
        mAdapter.addData(model);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void p2vFinishRefresh() {
        mRefresh.finishRefresh();
    }

    @Override
    public void p2vFinishLoadMore() {
        mRefresh.finishLoadMore();
    }

    @Override
    public void p2vShowToast(String message) {
        ToastUtil.show(message);
    }
}
