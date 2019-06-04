package com.huihu.module_home.hotrank.view.fragment;

import android.os.Bundle;
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
import com.huihu.module_home.answerdetail.view.fragment.AnswerDetailFragment;
import com.huihu.module_home.home.view.ui.HuihuBannerIndicator;
import com.huihu.module_home.home.view.ui.HuihuLoopingViewPager;
import com.huihu.module_home.hotrank.entity.HotRankBean;
import com.huihu.module_home.hotrank.hotrankinterface.IHotRankPresenter;
import com.huihu.module_home.hotrank.hotrankinterface.IHotRankView;
import com.huihu.module_home.hotrank.presenter.ImpHotRankPresenter;
import com.huihu.module_home.hotrank.view.adapter.HotRankAdapter;
import com.huihu.module_home.hotrank.view.adapterInface.OnHotRankItemClickListener;
import com.huihu.module_home.popularIdea.enity.ItemType;
import com.huihu.commonlib.MaterialFooter;
import com.huihu.module_home.popularIdea.enity.SwitchGrph;
import com.huihu.module_home.popularIdea.view.adapter.PopularBanerAdapter;
import com.huihu.module_home.questionandanswerlist.view.QuestionAndAnswerListFragment;
import com.huihu.uilib.statelayout.StateLayout;
import com.huihu.uilib.util.DensityUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author ouyangjianfeng
 * @time 2019/2/21  13:49
 * @desc 热榜fragment
 */
public class HotRankFragment extends BaseFragment implements IHotRankView, OnHotRankItemClickListener {

    Unbinder unbinder;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R2.id.viewpager)
    HuihuLoopingViewPager mViewPager;
    @BindView(R2.id.indicator_line)
    HuihuBannerIndicator mIndicatorLine;
    PopularBanerAdapter mPopularBanerAdapter;
    private HotRankAdapter adapter;
    private StateLayout stateLayout;

    public static HotRankFragment newInstance() {
        Bundle args = new Bundle();
        HotRankFragment fragment = new HotRankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_hot_rank, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private final IHotRankPresenter impHotRankPresenter = new ImpHotRankPresenter(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        impHotRankPresenter.v2pGetHotRank();
        impHotRankPresenter.v2pGetSwitch(1);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanner();
        initRecycleView();
        initSmartRefreshListener();
        initStateLayout();

    }

    private void initStateLayout() {
        StateLayout.Builder builder = new StateLayout.Builder(mRecyclerView);
        builder.setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                impHotRankPresenter.v2pGetHotRank();
                stateLayout.showLoadingData();
            }
        });
        stateLayout = builder.create();
    }

    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new HotRankAdapter();
        adapter.setContext(getActivity());
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    private void initSmartRefreshListener() {
        MaterialHeader materialHeader = new MaterialHeader(_mActivity);
        materialHeader.setColorSchemeResources(R.color.module_home_blue);
        refreshLayout.setRefreshHeader(materialHeader);
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setRefreshFooter(new MaterialFooter(_mActivity));
        refreshLayout.setDragRate(1);
        //refresh最大拖动距离，和footer等距，不可越界拖动
        refreshLayout.setFooterMaxDragRate(1);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                impHotRankPresenter.v2pGetHotRank();
                impHotRankPresenter.v2pGetSwitch(1);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                int end = adapter.getmList().size() - 1;
                impHotRankPresenter.v2pGetMoreHotRank(adapter.getmList().get(end));
            }
        });
    }
    private void initBanner() {
        mPopularBanerAdapter = new PopularBanerAdapter();
        mPopularBanerAdapter.setResIds(getActivity());
        mViewPager.setAdapter(mPopularBanerAdapter);
        mViewPager.setPageMargin(DensityUtil.dip2px(_mActivity, 10));
        mViewPager.autoLoop(true);
    }

    @Override
    public boolean onBackPressedSupport() {
        //返回false，点击事件交给父fragment解决
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void p2vGetHotRankSuccess(List<HotRankBean.PageDatasBean> pageDatas) {
        adapter.setmList(pageDatas);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);
    }

    @Override
    public void p2vShowNoData() {
        stateLayout.showEmptyData();
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void p2vGetDataEnd() {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
    }

    @Override
    public void p2vGetMoreHotRankSuccess(List<HotRankBean.PageDatasBean> pageDatas) {
        int oldCount = adapter.getItemCount();
        adapter.getmList().addAll(pageDatas);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vGetHotRankFailed() {
        stateLayout.showNoNetwork();
    }

    @Override
    public void p2vGetSwitchGrph(List<SwitchGrph> switchGrphs) {
        if (switchGrphs != null && switchGrphs.size() > 0) {
            mIndicatorLine.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.VISIBLE);
            mPopularBanerAdapter.setData(switchGrphs);
            mIndicatorLine.setType(HuihuBannerIndicator.IndicatorType.CIRCLE_LINE);
            mIndicatorLine.setViewPager(mViewPager, switchGrphs.size(), false);
        } else {
            mIndicatorLine.setVisibility(View.GONE);
            mViewPager.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(View view, int position, int ViewType) {
        HotRankBean.PageDatasBean datasBean = adapter.getmList().get(position);
        switch (ViewType) {
            case ItemType.question:
                ((SupportFragment) getParentFragment().getParentFragment()).start(QuestionAndAnswerListFragment.newInstance(datasBean.getIdeaId()));
                break;
            case ItemType.answer:
                ((SupportFragment) getParentFragment().getParentFragment()).start(AnswerDetailFragment.newInstance(datasBean.getIdeaId(), SPUtils.getInstance().getCurrentUid()));
                break;
            default:
                ToastUtil.show("还没处理的类型我懵逼了");
                break;
        }
    }
}

