package com.huihu.module_home.popularIdea.view.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.home.view.ui.HuihuBannerIndicator;
import com.huihu.module_home.home.view.ui.HuihuLoopingViewPager;
import com.huihu.module_home.popularIdea.enity.PopularIdeaData;
import com.huihu.module_home.popularIdea.enity.PutGiveFollowsSubCode;
import com.huihu.module_home.popularIdea.enity.SwitchGrph;
import com.huihu.module_home.popularIdea.popularIdeainterface.IPopularIdeaPresenter;
import com.huihu.module_home.popularIdea.popularIdeainterface.IPopularIdeaView;
import com.huihu.module_home.popularIdea.presenter.ImpPopularIdeaPresenter;
import com.huihu.module_home.popularIdea.view.adapter.PopularAdapter;
import com.huihu.module_home.popularIdea.view.adapter.PopularBanerAdapter;
import com.huihu.module_home.popularIdea.view.listener.PopularAdapterListener;
import com.huihu.module_home.popularIdea.view.listener.PopularItemClickListener;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.customize.HHShareDialog;
import com.huihu.commonlib.MaterialFooter;
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
 * @time 2019/2/21  16:51
 * @desc 热门fragment
 */
public class PopularFragment extends BaseFragment implements IPopularIdeaView, PopularItemClickListener {
    private static int TOTAL_COUNT = 3;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R2.id.viewpager)
    HuihuLoopingViewPager mViewPager;
    @BindView(R2.id.indicator_line)
    HuihuBannerIndicator mIndicatorLine;
    PopularBanerAdapter mPopularBanerAdapter;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private StateLayout stateLayout;
    private PopularAdapter adapter;

    public static PopularFragment newInstance() {
        Bundle args = new Bundle();
        PopularFragment fragment = new PopularFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_popular, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StateLayout.Builder builder = new StateLayout.Builder(mRecyclerView);
        builder.setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                impPopularIdeaPresenter.v2pGetSwitch(2);
                impPopularIdeaPresenter.v2pGetListFirst();
            }
        });
        stateLayout = builder.create();
        initBanner();
        initRecyclerView();
        initSmartRefreshListener();//初始化下拉刷新监听
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        impPopularIdeaPresenter.v2pGetSwitch(2);
        impPopularIdeaPresenter.v2pGetListFirst();
    }

    private void initSmartRefreshListener() {
        MaterialHeader materialHeader = new MaterialHeader(_mActivity);
        materialHeader.setColorSchemeResources(R.color.module_home_blue);
        refreshLayout.setRefreshHeader(materialHeader);
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setRefreshFooter(new MaterialFooter(_mActivity));
        //显示拖动高度/真实拖动高度
        refreshLayout.setDragRate(1);
        //refresh最大拖动距离，和footer等距，不可越界拖动
        refreshLayout.setFooterMaxDragRate(1);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                impPopularIdeaPresenter.v2pGetSwitch(2);
                impPopularIdeaPresenter.v2pGetListFirst();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                int end = adapter.getmPopularDataBeans().size() - 1;
                impPopularIdeaPresenter.v2pLoadMoreList(adapter.getmPopularDataBeans().get(end));
            }
        });
    }

    private final IPopularIdeaPresenter impPopularIdeaPresenter = new ImpPopularIdeaPresenter(this);

    private void initBanner() {
        mPopularBanerAdapter = new PopularBanerAdapter();
        mPopularBanerAdapter.setResIds(getActivity());
        mViewPager.setAdapter(mPopularBanerAdapter);
        mViewPager.setPageMargin(DensityUtil.dip2px(_mActivity, 10));
        mViewPager.autoLoop(true);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        adapter = new PopularAdapter();
        adapter.setPopularItemClickListener(this);
        adapter.setPopularAdapterCallBack(new PopularAdapterListener() {
            @Override
            public void scrollCallBack() {
                ToastUtil.show("进入更多");
            }
        });
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
    public void p2vGetPopularIdeaList() {

    }

    @Override
    public void p2vGetPopularIdeaListSuccess(List<PopularIdeaData.PageDatasBean> pageDatas) {
        setDatas(pageDatas);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);

    }

    private void setDatas(List<PopularIdeaData.PageDatasBean> pageDatas) {
        adapter.setmPopularDataBeans(getActivity(), pageDatas);
    }

    @Override
    public void p2vShowMorePopularIdeaList(List<PopularIdeaData.PageDatasBean> moreList) {
        int oldCount = adapter.getItemCount();
        adapter.getmPopularDataBeans().addAll(moreList);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount, newCount);
        mRecyclerView.stopScroll();
    }

    @Override
    public void p2vShowNoData() {
        stateLayout.showEmptyData();
        refreshLayout.finishLoadMore();
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void p2vGetDataEnd() {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
    }

    @Override
    public void p2vGetPopularIdeaListFailed() {
        stateLayout.showNoNetwork();
    }

    @Override
    public void p2vGetSwitchGrphSuccess(List<SwitchGrph> switchGrphs) {
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
    public void p2vGetSwitchGrphFailed() {

    }

    @Override
    public void p2vDeleteIdeaSuccessful(int positoin) {
        adapter.getmPopularDataBeans().remove(positoin);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void p2vCanclFollowSuccessful(int position) {
        adapter.getmPopularDataBeans().remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void p2vPutGiveFollows() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void p2vPutGiveFollowsError(String subCode) {
        switch (subCode){
            case PutGiveFollowsSubCode
                    .UnLogin:
                SimpleRouter.getInstance().startActivity(RouterReDefine.LOGIN_ACTIVITY, null);
                break;
        }
    }

    @Override
    public void p2vShowPopuWindow(View view, PopularIdeaData.PageDatasBean bean, int position) {
        showPopuwindow(view,bean,position);
    }

    @Override
    public void p2vShowDialog() {
        HHShareDialog shareDialog = new HHShareDialog(_mActivity);
        shareDialog.showDialog();
    }

    @Override
    public void p2vHandlePopularItemClickResult(SupportFragment fragment, int judyType) {
        if (judyType==2) {
            ((SupportFragment) getParentFragment().getParentFragment()).start(fragment);
        }
    }

    @Override
    public void popularItemClick(View view, int position, int viewType) {
        PopularIdeaData.PageDatasBean bean = adapter.getmPopularDataBeans().get(position);
        impPopularIdeaPresenter.v2pJudgyPopularItemClick(view,viewType,bean,position);
    }
    private void showPopuwindow(View v, final PopularIdeaData.PageDatasBean bean , final int position) {
        // 用于PopupWindow的View
        View contentView=LayoutInflater.from(getContext()).inflate(R.layout.module_home_popuwindow_uninterested, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window=new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        //获取点击View的坐标
        int[] location = new int[2];
        View uninterestion = contentView.findViewById(R.id.popu);
        uninterestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                impPopularIdeaPresenter.v2pCancelFollow(bean.getIdeaId(),position);
                window.dismiss();
            }
        });
        v.getLocationOnScreen(location);
        window.showAtLocation(v, Gravity.NO_GRAVITY,location[0]-DensityUtil.dip2px(getActivity(),140),location[1]);
       // window.showAsDropDown(v,-200,-v.getHeight());
    }
}
