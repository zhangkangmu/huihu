package com.huihu.module_mine.attentioncircle.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.attentioncircle.adapter.CircleAdapter;
import com.huihu.module_mine.attentioncircle.attentioncircleinterface.IAttentionCirclePresenter;
import com.huihu.module_mine.attentioncircle.attentioncircleinterface.IAttentionCircleView;
import com.huihu.module_mine.attentioncircle.entity.CircleAttentionInfo;
import com.huihu.module_mine.attentioncircle.presenter.ImpAttentionCirclePresenter;
import com.huihu.module_mine.attentioncircle.view.adapterInterface.OnAttentionItemClickListen;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.customize.LoadingDialog;
import com.huihu.commonlib.MaterialFooter;
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

public class CircleFragment extends BaseFragment implements IAttentionCircleView,OnAttentionItemClickListen{
    private final IAttentionCirclePresenter iAttentionCirclePresenter=new ImpAttentionCirclePresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.recycler_view_answer)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private CircleAdapter adapter;
    private StateLayout stateLayout;
    private List<CircleAttentionInfo.PageDatasBean> pageDatas=new ArrayList<>();
    private LoadingDialog loadingDialog;

    public static CircleFragment newInstance() {
        Bundle args = new Bundle();
        CircleFragment fragment = new CircleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_answered, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
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
                iAttentionCirclePresenter.v2pGetAttentionCircleList();
            }
        });
        stateLayout =builder.create() ;
//        stateLayout = new StateLayout.Builder(mRecyclerView).create();
    }

    private void initSmartLoadMoreListener() {
        MaterialHeader materialHeader = new MaterialHeader(_mActivity);
        materialHeader.setColorSchemeResources(R.color.module_mine_blue);
        refreshLayout.setRefreshHeader(materialHeader);
        refreshLayout.setFooterMaxDragRate(1);
        //箭头浮在上方刷新
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                iAttentionCirclePresenter.v2pGetAttentionCircleList();
            }
        });
        refreshLayout.setRefreshFooter(new MaterialFooter(getActivity()));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int end = adapter.getmList().size() - 1;
                iAttentionCirclePresenter.v2pGetMoreCircleList(adapter.getmList().get(end));
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CircleAdapter(getContext());
        adapter.setOnAttentionItemClickListen(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iAttentionCirclePresenter.v2pGetAttentionCircleList();
    }

    @Override
    public boolean onBackPressedSupport() {
        return false;
    }

    @Override
    public void p2vGetAttentionCircleListSucces(List<CircleAttentionInfo.PageDatasBean> pageDatas) {
        adapter.setAttentionCircleList(pageDatas);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);
        this.pageDatas=pageDatas;
    }

    @Override
    public void p2vNetFail() {
        stateLayout.showNoNetwork();
        if (refreshLayout!=null) {
            refreshLayout.setEnableLoadMore(false);
            refreshLayout.setEnableRefresh(false);
            ToastUtil.show("没有网络咯~~~");
        }
    }

    @Override
    public void p2vShowNoData() {
        stateLayout.showEmptyData();
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void p2vReturnMoreCircleListSuccess(List<CircleAttentionInfo.PageDatasBean> datas) {
        //加载更多
        int oldCount = adapter.getItemCount();
        adapter.getmList().addAll(datas);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount,newCount);
    }

    @Override
    public void p2vNetComplete() {
        refreshLayout.finishLoadMore();
        if (loadingDialog!=null){
        loadingDialog.dismissDialog();
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void attentiOnItemClick(View v, int position, boolean isAttention) {
        CircleAttentionInfo.PageDatasBean bean = pageDatas.get(position);
        Bundle bundle = new Bundle();
        bundle.putLong("circleId",bean.getCircleId());
        bundle.putLong("uid", SPUtils.getInstance().getCurrentUid());
        SupportFragment fragment= (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.CIRCLELIST_FRAGMENT,bundle);
        ((SupportFragment) getParentFragment()).start(fragment);
    }
}
