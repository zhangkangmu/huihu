package com.huihu.module_mine.favorites.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.favorites.favoritesinterface.IFavoritesPresenter;
import com.huihu.module_mine.favorites.favoritesinterface.IFavoritesView;
import com.huihu.module_mine.favorites.presenter.ImpFavoritesPresenter;
import com.huihu.module_mine.favoritesanswered.view.fragment.FavoritesAnsweredFragment;
import com.huihu.module_mine.favoritesdisuss.view.fragment.FavoritesDisussFragment;
import com.huihu.uilib.adapter.CommonViewPagerAdapter;
import com.huihu.uilib.adapter.HuihuNavigationAdapter;
import com.huihu.uilib.model.IndicatorParamModel;
import com.huihu.uilib.model.TextStyle;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class FavoritesFragment extends BaseFragment implements IFavoritesView,AppBarLayout.OnOffsetChangedListener{
    private final IFavoritesPresenter iFavoritesPresenter = new ImpFavoritesPresenter(this);
    private Unbinder unbinder;
    @BindView(R2.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.viewpager)
    ViewPager mViewpager;
    @BindView(R2.id.tabs)
    MagicIndicator mTabs;


    private SupportFragment[] mSupportFragments = new SupportFragment[2];

    // AppBarLayout滚动上一次的verticalOffset
    private int mLastVerticalOffset = 0;

    public static FavoritesFragment newInstance() {
        Bundle args = new Bundle();
        FavoritesFragment fragment = new FavoritesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.module_mine_fragment_my_attention,container,false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAppBarLayout();
        initTitle();
        initIndicator();
        initViewPager();
        initListener();
    }

    private void initListener() {
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTabs.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mTabs.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mTabs.onPageScrollStateChanged(state);
            }
        });
    }

    private void initIndicator() {
        mViewpager.setOffscreenPageLimit(2);
        IndicatorParamModel indicatorParamModel = new IndicatorParamModel();
        indicatorParamModel.setTextStyle(TextStyle.level_second);
        indicatorParamModel.setTitleStrings(getResources().getStringArray(R.array.module_mine_favorites_indicator));
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        HuihuNavigationAdapter huihuNavigationAdapter = new HuihuNavigationAdapter(mViewpager, indicatorParamModel);
        commonNavigator.setAdapter(huihuNavigationAdapter);
        mTabs.setNavigator(commonNavigator);
    }

    private void initViewPager() {
        mSupportFragments[0] = FavoritesAnsweredFragment.newInstance();
        mSupportFragments[1] = FavoritesDisussFragment.newInstance();
        CommonViewPagerAdapter commonViewPagerAdapter = new CommonViewPagerAdapter(getChildFragmentManager(), mSupportFragments);
        mViewpager.setAdapter(commonViewPagerAdapter);

    }

    private void initTitle() {
        mTvTitle.setText("收藏");
    }

    private void initAppBarLayout() {
        mAppBar.addOnOffsetChangedListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mLastVerticalOffset != i) {
        }
    }

    @OnClick({R2.id.iv_back})
    public void onViewClicked(View view){
        int id = view.getId();
        if (id==R.id.iv_back){
            pop();
        }
    }
}
