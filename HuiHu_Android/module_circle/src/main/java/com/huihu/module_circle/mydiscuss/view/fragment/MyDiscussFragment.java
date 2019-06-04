package com.huihu.module_circle.mydiscuss.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.MaterialFooter;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.discussdetail.view.DiscussDetailFragment;
import com.huihu.module_circle.mydiscuss.entity.MyDiscussInfo;
import com.huihu.module_circle.mydiscuss.mydiscussinterface.IMyDiscussPresenter;
import com.huihu.module_circle.mydiscuss.mydiscussinterface.IMyDiscussView;
import com.huihu.module_circle.mydiscuss.presenter.ImpMyDiscussPresenter;
import com.huihu.module_circle.mydiscuss.view.adapter.MyDiscussAdapter;
import com.huihu.module_circle.mydiscuss.view.adapterInterface.OnClickListen;
import com.huihu.uilib.customize.HHShareDialog;
import com.huihu.uilib.customize.LoadingDialog;
import com.huihu.uilib.statelayout.StateLayout;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class MyDiscussFragment extends BaseFragment implements IMyDiscussView, OnClickListen {
    private final IMyDiscussPresenter iMyDiscussPresenter = new ImpMyDiscussPresenter(this);
    private Unbinder unbinder;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private MyDiscussAdapter adapter;
    private Context mContext;
    private StateLayout stateLayout;
    private List<MyDiscussInfo.PageDatasBean> datas = new ArrayList<>();
    private LoadingDialog loadingDialog;
    private static final String CIRCLEID = "circleId";
    private static final String UID = "uid";
    private Long mCircleId;
    private Long mUid;

    public static MyDiscussFragment newInstance(long circleId, long uid) {
        Bundle args = new Bundle();
        args.putLong(CIRCLEID, circleId);
        args.putLong(UID, uid);
        MyDiscussFragment fragment = new MyDiscussFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_common, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initSmartLoadMoreListener();
        StateLayout.Builder builder = new StateLayout.Builder(mRecyclerView);
        builder.setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new LoadingDialog(getContext());
                loadingDialog.showDialog();
                refreshLayout.setEnableRefresh(true);
                iMyDiscussPresenter.v2pRequestMyDiscuss(mCircleId, 0, 20, mUid);
            }
        });
        stateLayout = builder.create();
    }

    private void initSmartLoadMoreListener() {
        MaterialHeader materialHeader = new MaterialHeader(getContext());
        refreshLayout.setRefreshHeader(materialHeader);
        //箭头浮在上方刷新
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                iMyDiscussPresenter.v2pRequestMyDiscuss(mCircleId, 0, 20, mUid);
            }
        });
        refreshLayout.setRefreshFooter(new MaterialFooter(getActivity()));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int end = adapter.getmList().size() - 1;
                iMyDiscussPresenter.v2pRequestMoreMyDiscuss(mCircleId, adapter.getmList().get(end).getCreateTime(), 20, mUid);
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MyDiscussAdapter(getContext());
        adapter.setClickListen(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCircleId = getArguments().getLong(CIRCLEID, 0);
        mUid = getArguments().getLong(UID, 0);
        iMyDiscussPresenter.v2pRequestMyDiscuss(mCircleId, 0, 20, mUid);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void p2vReturnMoreCircleDiscussList(List<MyDiscussInfo.PageDatasBean> datas) {

    }

    @Override
    public void p2vReturnCircleDiscussList(List<MyDiscussInfo.PageDatasBean> datas) {
        adapter.setCircleDiscussList(datas);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);
        this.datas = datas;
    }

    @Override
    public void p2vShowNoData() {
        stateLayout.showEmptyData();
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void p2vNetFail() {
        stateLayout.showNoNetwork();
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);
        ToastUtil.show("没有网络咯~~~");
    }

    @Override
    public void p2vNetComplete() {
        refreshLayout.finishLoadMore();
        if (loadingDialog != null) {
            loadingDialog.dismissDialog();
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void onClickItem(View view, int position) {
        MyDiscussInfo.PageDatasBean bean = adapter.getmList().get(position);
        if (view.getId() == R.id.iv_share) {
            HHShareDialog shareDialog = new HHShareDialog(_mActivity);
            shareDialog.showDialog();
        } else {
            ((SupportFragment) getParentFragment()).start(DiscussDetailFragment.newInstance(bean.getIdeaId(), 0L));
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        return false;
    }
}
