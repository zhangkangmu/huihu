package com.huihu.module_mine.community.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.answered.entity.AnsweredInfo;
import com.huihu.module_mine.answered.view.adapter.AnswerListAdapter;
import com.huihu.module_mine.community.communityinterface.ICommunityPresenter;
import com.huihu.module_mine.community.communityinterface.ICommunityView;
import com.huihu.module_mine.community.entity.CommunityInfo;
import com.huihu.module_mine.community.presenter.ImpCommunityPresenter;
import com.huihu.module_mine.community.view.adapter.CommunityListAdapter;
import com.huihu.module_mine.community.view.adapterInterface.CommunityItemClickListener;
import com.huihu.uilib.customize.HHShareDialog;
import com.huihu.uilib.customize.refresh.HuihuSmartRefreshFooter;
import com.huihu.uilib.statelayout.StateLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class CommunityFragment extends BaseFragment implements ICommunityView, CommunityItemClickListener {
    private final ICommunityPresenter iCommunityPresenter = new ImpCommunityPresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.recycler_view_answer)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private StateLayout stateLayout;
    private CommunityListAdapter adapter;
    private long fid;
    public static CommunityFragment newInstance(long fid) {
        Bundle bundle = new Bundle();
        bundle.putLong("fid", fid);
        CommunityFragment fragment = new CommunityFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_community, container, false);
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
        stateLayout = new StateLayout.Builder(mRecyclerView).create();
    }
    private void initSmartLoadMoreListener() {
        refreshLayout.setRefreshFooter(new HuihuSmartRefreshFooter(getActivity()));
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                int end = adapter.getmList().size()-1;
                iCommunityPresenter.v2pGetMoreCommunityPresenter(adapter.getmList().get(end),fid);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iCommunityPresenter.v2pGetCommunityList(fid);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CommunityListAdapter();
        adapter.setOnCommunityItemClickedListener(this);
        adapter.setContext(getActivity());
        mRecyclerView.setAdapter(adapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void p2vGetCommunityListSuccess(List<CommunityInfo.PageDatasBean> mlist) {
        adapter.setDatas(mlist);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);
    }

    @Override
    public void p2vGetMoreCommunityListSuccess(List<CommunityInfo.PageDatasBean> mlist) {
        int oldCount = adapter.getItemCount();
        adapter.getmList().addAll(mlist);
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
        ((SupportFragment) getParentFragment()).pop();
        return false;
    }

    @Override
    public void onCommunityItemClick(View view, int position, int ViewType) {
        if (view.getId()==R.id.iv_popular_bottom_three){
            HHShareDialog shareDialog = new HHShareDialog(_mActivity);
            shareDialog.showDialog();
        }else{
            ToastUtil.show(""+position);
        }
    }
}
