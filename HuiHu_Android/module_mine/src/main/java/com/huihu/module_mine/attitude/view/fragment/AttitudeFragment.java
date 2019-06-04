package com.huihu.module_mine.attitude.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.attitude.attitudeinterface.IAttitudePresenter;
import com.huihu.module_mine.attitude.attitudeinterface.IAttitudeView;
import com.huihu.module_mine.attitude.entity.AttitudeInfo;
import com.huihu.module_mine.attitude.presenter.ImpAttitudePresenter;
import com.huihu.module_mine.attitude.view.adapter.AttitudeListAdapter;
import com.huihu.uilib.customize.refresh.HuihuSmartRefreshFooter;
import com.huihu.uilib.statelayout.StateLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class AttitudeFragment extends BaseFragment implements IAttitudeView{
    private final IAttitudePresenter iAttitudePresenter = new ImpAttitudePresenter(this);
    Unbinder unbinder;
    private StateLayout stateLayout;
    @BindView(R2.id.recycler_view_attitude)
    RecyclerView recyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private AttitudeListAdapter adapter;
    private long fid;

    public static AttitudeFragment newInstance(long fid) {
        Bundle bundle = new Bundle();
        AttitudeFragment fragment = new AttitudeFragment();
        bundle.putLong("fid",fid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_attitude, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            fid = bundle.getLong("fid");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initSmartLoadMoreListener();
        stateLayout = new StateLayout.Builder(recyclerView).create();
        iAttitudePresenter.v2pGetAttitudeList(fid);
    }
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AttitudeListAdapter();
        adapter.setContext(getActivity());
        recyclerView.setAdapter(adapter);
    }
    private void initSmartLoadMoreListener() {
        refreshLayout.setRefreshFooter(new HuihuSmartRefreshFooter(getActivity()));
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                int end = adapter.getmList().size()-1;
                iAttitudePresenter.v2pGetMoreAttitudeList(adapter.getmList().get(end),fid);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void p2vReturnAttitudeListSuccess(List<AttitudeInfo.PageDatasBean> msg) {
        adapter.setDatas(msg);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);

    }

    @Override
    public void p2vReturnMoreAttitudeListSuccess(List<AttitudeInfo.PageDatasBean> mList) {
        int oldCount = adapter.getItemCount();
        adapter.getmList().addAll(mList);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vShowNoData() {
        stateLayout.showEmptyData();
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void p2vGetDataEnd() {
        refreshLayout.finishLoadMore();
    }

    @Override
    public boolean onBackPressedSupport() {
        ((SupportFragment)getParentFragment()).pop();
        return false;

    }
}
