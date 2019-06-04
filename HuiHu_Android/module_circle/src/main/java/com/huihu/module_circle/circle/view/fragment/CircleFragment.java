package com.huihu.module_circle.circle.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseBackFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.circle.circleinterface.ICirclePresenter;
import com.huihu.module_circle.circle.circleinterface.ICircleView;
import com.huihu.module_circle.circle.entity.CircleBaseBean;
import com.huihu.module_circle.circle.entity.CircleInfo;
import com.huihu.module_circle.circle.presenter.ImpCirclePresenter;
import com.huihu.module_circle.circle.view.adapter.CircleBanerAdapter;
import com.huihu.module_circle.circle.view.adpterinterface.OnCircleBannerClickListener;
import com.huihu.module_circle.circle.view.ui.HuihuBannerIndicator;
import com.huihu.module_circle.circle.view.ui.HuihuLoopingViewPager;
import com.huihu.module_circle.circlelist.view.CircleListFragment;
import com.huihu.module_circle.createcircle.view.CreateCircleFragment;
import com.huihu.module_circle.discuss.view.fragment.DiscussFragment;
import com.huihu.module_circle.mycircle.view.fragment.MyCircleFragment;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.adapter.CommonViewPagerAdapter;
import com.huihu.uilib.adapter.HuihuNavigationAdapter;
import com.huihu.uilib.model.IndicatorParamModel;
import com.huihu.uilib.model.TextStyle;
import com.huihu.uilib.statelayout.StateLayout;
import com.huihu.uilib.util.DensityUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

@SimpleRouterClassRegister(key = RouterReDefine.CIRCLE_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class CircleFragment extends BaseBackFragment implements ICircleView, OnCircleBannerClickListener {
    private final ICirclePresenter iCirclePresenter = new ImpCirclePresenter(this);

    Unbinder unbinder;
    @BindView(R2.id.v_indicator)
    MagicIndicator mVIndicator;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.viewpager)
    HuihuLoopingViewPager mViewPagerBanner;
    @BindView(R2.id.indicator_line)
    HuihuBannerIndicator mIndicatorLine;
    @BindView(R2.id.tv_title)
    TextView tv_title;
    @BindView(R2.id.iv_refreshData)
    ImageView img;
    @BindView(R2.id.tv_circle)
    TextView tv_circle;
    @BindView(R2.id.tv_right)
    TextView mTvRight;
    @BindView(R2.id.iv_icon_right)
    ImageView mIvIconRight;

    private ArrayList<List<? extends CircleBaseBean>> resIds;
    private Fragment[] mFragments = new Fragment[]{new DiscussFragment(), new MyCircleFragment()};
    private StateLayout stateLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initStateLayout();
    }

    private void initStateLayout() {
        StateLayout.Builder builder = new StateLayout.Builder(mViewPager);
        builder.setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // stateLayout.showLoadingData();
            }
        });
        stateLayout = builder.create();
    }

    private void initData() {
        iCirclePresenter.v2pGetCircle();

    }

    private void initView() {
        initToolBar();
        //导航tab
        initIndicator();
        //初始化viewpager
        initViewPager();
        //初始化监听
        initListener();
        //初始化上面的banner
        initBanner();
    }

    private void initToolBar() {
        mIvIconRight.setVisibility(View.GONE);
        mTvRight.setText(R.string.uilib_create_circle);
        TextViewUtils.setTextFakeBold(mTvRight);
    }

    private String[] circleName = {"最新圈子", "热门圈子", "活跃圈子"};

    private void initBanner() {
        TextViewUtils.setTextFakeBold(tv_title);
        TextViewUtils.setTextFakeBold(tv_circle);
        mBanerAdapter = new CircleBanerAdapter();
        mBanerAdapter.setmContext(getActivity());
        mViewPagerBanner.setAdapter(mBanerAdapter);
        mViewPagerBanner.setPageMargin(DensityUtil.dip2px(_mActivity, 10));
        mViewPagerBanner.autoLoop(false);
        mViewPagerBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mViewPagerBanner.setTag(i);
                tv_title.setText(circleName[i]);
                iCirclePresenter.v2pGetCircle();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mBanerAdapter.setOnCircleInfoClickListener(this);
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mVIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mVIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mVIndicator.onPageScrollStateChanged(state);
            }
        });
    }

    private void initViewPager() {
        CommonViewPagerAdapter adapter = new CommonViewPagerAdapter(getChildFragmentManager(), mFragments);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);
    }

    private void initIndicator() {
        IndicatorParamModel paramModel = new IndicatorParamModel();
        paramModel.setTextStyle(TextStyle.level_second);
        paramModel.setTitleStrings(_mActivity.getResources().getStringArray(R.array.module_circle_main_indicator));
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        commonNavigator.setSkimOver(true);//开启跨页切换是否略过效果
        HuihuNavigationAdapter navigationAdapter = new HuihuNavigationAdapter(mViewPager, paramModel);
        commonNavigator.setAdapter(navigationAdapter);
        mVIndicator.setNavigator(commonNavigator);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R2.id.cl_right, R2.id.tv_refresh_data, R2.id.iv_refreshData})
    public void onViewClicked(View view) {
        int viewId = view.getId();
        if (viewId == R.id.cl_right) {
            ((SupportFragment) getParentFragment()).start(new CreateCircleFragment());
        } else {
            iCirclePresenter.v2pGetCircle();
            rotate();
        }
    }

    private CircleBanerAdapter mBanerAdapter;

    @Override
    public void p2vReturnCircleInfo(CircleInfo circleInfo) {
        if (resIds == null) {
            resIds = new ArrayList<>();
        }
        resIds.clear();
        resIds.add(circleInfo.getRecentCircle());
        resIds.add(circleInfo.getActiveCircle());
        resIds.add(circleInfo.getPopularCircle());
        mIndicatorLine.setType(HuihuBannerIndicator.IndicatorType.CIRCLE_LINE);
        mIndicatorLine.setViewPager(mViewPagerBanner, resIds.size(), false);
        mBanerAdapter.setData(resIds);

    }

    @Override
    public void p2vGetDataEnd() {

    }

    @Override
    public void p2vShowNoData() {
      stateLayout.showEmptyData();
    }

    @Override
    public void p2vJoinCircleSuccess(int position, int type) {
        if (changeView.getText().equals("加入")) {
            changeView.setTextColor(getResources().getColor(R.color.gray_two));
            changeView.setText("已关注");
        } else {
            changeView.setTextColor(getResources().getColor(R.color.common_blue));
            changeView.setText("加入");
        }
    }

    @Override
    public void p2vNetFail() {
       stateLayout.showNoNetwork();
    }

    TextView changeView;

    @Override
    public void circleInfoClicked(View view, int position, CircleBaseBean bean, int type) {
        int viewId = view.getId();
            if (viewId == R.id.tv_join) {
                if (bean.getJoin() == 0) {
                    iCirclePresenter.v2pJoinCircle(bean.getCircleId(), 1, position, type);
                } else {
                    //exit
                    iCirclePresenter.v2pJoinCircle(bean.getCircleId(), 0, position, type);
                }
                changeView = (TextView) view;
            } else {
                ((SupportFragment) getParentFragment()).start(CircleListFragment.newInstance(bean.getCircleId(), SPUtils.getInstance().getCurrentUid()));
            }
    }

    public void rotate() {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate);
        img.startAnimation(animation);//開始动画
        mViewPagerBanner.autoLoop(false);
    }
}
