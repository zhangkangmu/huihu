package com.huihu.module_home.questiondraft.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.questiondraft.model.GetDraftModel;
import com.huihu.module_home.questiondraft.presenter.ImpQuestionDraftPresenter;
import com.huihu.module_home.questiondraft.questiondraftinterface.IQuestionDraftPresenter;
import com.huihu.module_home.questiondraft.questiondraftinterface.IQuestionDraftView;
import com.huihu.module_home.questiondraft.view.adapter.QuestionDraftAdapter;
import com.huihu.uilib.statelayout.StateLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class QuestionDraftFragment extends BaseFragment implements IQuestionDraftView {
    private final IQuestionDraftPresenter iQuestionDraftPresenter = new ImpQuestionDraftPresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refresh)
    SmartRefreshLayout mRefresh;

    private StateLayout mStateLayout;

    private final static int PAGESIZE = 20;
    private QuestionDraftAdapter mAdapter;

    public static QuestionDraftFragment newInstance(int type) {
        QuestionDraftFragment fragment = new QuestionDraftFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_draft_list, container, false);
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
            int type = bundle.getInt("type");
            iQuestionDraftPresenter.v2pGetDraftList(0, PAGESIZE, type, SPUtils.getInstance().getCurrentUid());
        }
    }

    private void initView() {
        initRefresh();
        initRecyclerView();
        initStateLayout();
    }

    private void initStateLayout() {
        mStateLayout = new StateLayout.Builder(mRefresh).setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStateLayout.showLoadingData();
                initData();
            }
        }).create();
        mStateLayout.showLoadingData();
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new QuestionDraftAdapter(_mActivity);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initRefresh() {
        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onBackPressedSupport() {
        ((SupportFragment) getParentFragment()).pop();
        return true;
    }

    @Override
    public void p2vShowToast(String message) {
        ToastUtil.show(message);
    }

    @Override
    public void p2vShowRefreshData(List<GetDraftModel.PageDatasBean> datas) {
        mAdapter.setData(datas);
        mAdapter.notifyDataSetChanged();
        mStateLayout.showContent();
    }

    @Override
    public List<GetDraftModel.PageDatasBean> getData() {
        return mAdapter.getData();
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
    public void p2vFinishRefresh() {
        mRefresh.finishRefresh();
    }

    @Override
    public void p2vFinishLoadMore() {
        mRefresh.finishLoadMore();
    }

    @Override
    public void p2vAddData(List<GetDraftModel.PageDatasBean> datas) {
        mAdapter.addData(datas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void p2vShowNoNetWork() {
        mStateLayout.showNoNetwork();
    }
}
