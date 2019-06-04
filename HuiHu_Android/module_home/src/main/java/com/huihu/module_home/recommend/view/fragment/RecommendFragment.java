package com.huihu.module_home.recommend.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.popularIdea.view.adapter.PopularAdapter;
import com.huihu.module_home.home.view.ui.HuihuLoopingViewPager;
import com.huihu.module_home.recommend.recommendinterface.IRecommendView;
import com.huihu.uilib.customize.refresh.HuihuSmartRefreshFooter;
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

/**
 * @author ouyangjianfeng
 * @time 2019/2/21  13:50
 * @desc 推荐fragment
 */
public class RecommendFragment extends BaseFragment implements IRecommendView {
    private static int TOTAL_COUNT = 3;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R2.id.viewpager)
    HuihuLoopingViewPager mViewPager;
    private StateLayout stateLayout;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
//    @BindView(R2.id.indicator_line)
//    HuihuBannerIndicator mIndicatorLine;

    public static RecommendFragment newInstance() {
        Bundle args = new Bundle();
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_recommend, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stateLayout = new StateLayout.Builder(mRecyclerView).create();
        initBanner();
        initSmartRefreshListener();
    }
    private void initSmartRefreshListener() {
        MaterialHeader materialHeader = new MaterialHeader(_mActivity);
        materialHeader.setColorSchemeResources(R.color.module_home_blue);
        refreshLayout.setRefreshHeader(materialHeader);
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setRefreshFooter(new HuihuSmartRefreshFooter(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }
    private List<Integer> recommendList=new ArrayList<>();
    @SuppressLint("ResourceType")
    private void initBanner() {
//        PopularBanerAdapter popularBanerAdapter = new PopularBanerAdapter();
//        recommendList.clear();
//        recommendList.add(R.mipmap.banner_1);
//        recommendList.add(R.mipmap.banner_1);
//        recommendList.add(R.mipmap.banner_1);
//        recommendList.add(R.mipmap.banner_1);
//        recommendList.add(R.mipmap.banner_1);
//       // popularBanerAdapter.setResIds(getActivity(),recommendList);
//        mViewPager.setAdapter(popularBanerAdapter);
//        mViewPager.setOffscreenPageLimit(3);
//        mViewPager.setPageMargin(10);
//        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
//            float scale = 0.9f;
//            @Override
//            public void transformPage(View page, float position) {
//                if (position >= 0 && position <= 1) {
//                    page.setScaleY(scale + (1 - scale) * (1 - position));
//                } else if (position > -1 && position < 0) {
//                    page.setScaleY(1 + (1 - scale) * position);
//                } else { page.setScaleY(scale); }
//            } });
//        mViewPager.autoLoop(true);
//        mIndicatorLine.setViewPager(mViewPager, recommendList.size());
    }
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        PopularAdapter adapter = new PopularAdapter();
        //adapter.setmPopularDataBeans(getActivity(),popularDataBeanList);
        mRecyclerView.setAdapter(adapter);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onBackPressedSupport() {
        //返回false，点击事件交给父fragment解决
        return false;
    }

    @Override
    public void p2vGetListSuccess() {
        stateLayout.showEmptyData();

    }

    @Override
    public void p2vGetListFailed() {

    }
}
