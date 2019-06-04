package com.huihu.module_notification.fans.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.fans.FansConstant;
import com.huihu.module_notification.fans.adapter.FansAdapter;
import com.huihu.module_notification.fans.adapter.FansPagerAdapter;
import com.huihu.module_notification.fans.entity.FollowPageBean;
import com.huihu.module_notification.fans.fansinterface.IFansPresenter;
import com.huihu.module_notification.fans.fansinterface.IFansView;
import com.huihu.module_notification.fans.presenter.ImpFansPresenter;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.util.ReadUtil;
import com.huihu.module_notification.util.RouterUtil;
import com.huihu.uilib.adapter.HuihuNavigationAdapter;
import com.huihu.uilib.customize.refresh.HuihuSmartRefreshFooter;
import com.huihu.uilib.model.IndicatorParamModel;
import com.huihu.uilib.model.TextStyle;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

import static com.huihu.module_notification.fans.FansConstant.FOLLOW_ME;
import static com.huihu.module_notification.fans.FansConstant.MY_FOLLOW;

public class FansFragment extends SupportFragment implements IFansView {

    private final IFansPresenter iFansPresenter = new ImpFansPresenter(this);

    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.indicator_content)
    MagicIndicator indicatorContent;
    @BindView(R2.id.vp_content)
    ViewPager vpContent;
    Unbinder unbinder;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R2.id.tv_title)
    TextView tvTitle;

    private int mType = FOLLOW_ME;
    private FansAdapter mFollowMeAdapter, mMyFollowAdapter;
    private FansPagerAdapter pagerAdapter;

    public static FansFragment newInstance() {
        return new FansFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_notification_fragment_fans, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFollowMeAdapter = new FansAdapter(FOLLOW_ME, iFansPresenter);
        mMyFollowAdapter = new FansAdapter(MY_FOLLOW, iFansPresenter);
        TextViewUtils.setTextFakeBold(tvTitle);
        List<FansAdapter> adapters = new ArrayList<>();
        adapters.add(mFollowMeAdapter);
        adapters.add(mMyFollowAdapter);
        pagerAdapter = new FansPagerAdapter(getContext(), adapters, iFansPresenter);
        vpContent.setAdapter(pagerAdapter);
        initIndicator();
        initListener();
        iFansPresenter.v2pGetFirstData(FOLLOW_ME);
        iFansPresenter.v2pGetFirstData(MY_FOLLOW);
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.global_blue);
        refresh.setRefreshFooter(new HuihuSmartRefreshFooter(getContext()));
        refresh.setRefreshHeader(header);
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mType == FOLLOW_ME) {
                    iFansPresenter.v2pGetMoreData(mType, getLastBean(mFollowMeAdapter));
                } else {
                    iFansPresenter.v2pGetMoreData(mType, getLastBean(mMyFollowAdapter));
                }

            }
        });
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (mType == FOLLOW_ME) {
                    iFansPresenter.v2pGetFirstData(mType);
                } else {
                    iFansPresenter.v2pGetFirstData(mType);
                }
            }
        });
        ReadUtil.readNotice(NoticeId.ATTENTION);
    }

    private void initListener() {
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicatorContent.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                indicatorContent.onPageSelected(position);
                if (position == 0) {
                    mType = FOLLOW_ME;
                } else {
                    mType = MY_FOLLOW;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                indicatorContent.onPageScrollStateChanged(state);
            }
        });
    }

    private void initIndicator() {
        IndicatorParamModel paramModel = new IndicatorParamModel();
        paramModel.setTextStyle(TextStyle.level_second);
        paramModel.setTitleStrings(getContext().getResources().getStringArray(R.array.module_notification_fans_indicator));
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        HuihuNavigationAdapter navigationAdapter = new HuihuNavigationAdapter(vpContent, paramModel);
        commonNavigator.setAdapter(navigationAdapter);
        indicatorContent.setNavigator(commonNavigator);
    }

    @OnClick({R2.id.iv_back})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            pop();
        }
    }

    @Override
    public void p2vShowFirstData(int type, List<FollowPageBean.DataBean> beans) {
        if (type == FOLLOW_ME) {
            showFirsData(mFollowMeAdapter, beans);
            pagerAdapter.getFollowMeStateLayout().showContent();
        } else {
            showFirsData(mMyFollowAdapter, beans);
            pagerAdapter.getMyFollowStateLayout().showContent();
        }

    }

    @Override
    public void p2vShowMoreData(int type, List<FollowPageBean.DataBean> beans) {
        if (type == FOLLOW_ME) {
            showMoreData(mFollowMeAdapter, beans);
            pagerAdapter.getFollowMeStateLayout().showContent();
        } else {
            showMoreData(mMyFollowAdapter, beans);
            pagerAdapter.getMyFollowStateLayout().showContent();
        }
    }

    @Override
    public void p2vShowNoData(int type) {
        if (type == FOLLOW_ME) {
            pagerAdapter.getFollowMeStateLayout().showEmptyData();
        } else {
            pagerAdapter.getMyFollowStateLayout().showEmptyData();
        }
    }

    @Override
    public void p2vShowNoNet(int type) {
        if (type == FansConstant.MY_FOLLOW) {
            pagerAdapter.getMyFollowStateLayout().showNoNetwork();
        } else {
            pagerAdapter.getFollowMeStateLayout().showNoNetwork();
        }
    }

    @Override
    public void p2vGetDataComplete() {
        refresh.finishLoadMore();
        refresh.finishRefresh();
    }

    @Override
    public void p2vChangeFollowViewState(int type, FollowPageBean.DataBean bean) {
        FansAdapter adapter = type == MY_FOLLOW ? mMyFollowAdapter : mFollowMeAdapter;
        int position = adapter.getDataList().indexOf(bean);
        adapter.notifyItemChanged(position);
    }

    @Override
    public void p2vStartOtherPeople(long uid) {
        RouterUtil.startOtherPeople(this, uid);
    }

    private void showFirsData(FansAdapter adapter, List<FollowPageBean.DataBean> beans) {
        adapter.setDataList(beans);
        adapter.notifyDataSetChanged();
    }


    private void showMoreData(FansAdapter adapter, List<FollowPageBean.DataBean> beans) {
        int oldCount = adapter.getItemCount();
        adapter.getDataList().addAll(beans);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount, newCount);
    }

    private FollowPageBean.DataBean getLastBean(FansAdapter adapter) {
        int end = adapter.getItemCount() - 1;
        return adapter.getDataList().get(end);
    }
}
