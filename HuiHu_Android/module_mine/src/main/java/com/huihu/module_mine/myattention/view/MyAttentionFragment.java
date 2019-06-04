package com.huihu.module_mine.myattention.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.attentioncircle.view.CircleFragment;
import com.huihu.module_mine.attentionquestion.view.fragment.AttentionQuestionFragment;
import com.huihu.module_mine.classificationattention.view.ClassificationFragment;
import com.huihu.module_mine.myattention.myattentioninterface.IMyAttentionPresenter;
import com.huihu.module_mine.myattention.myattentioninterface.IMyAttentionView;
import com.huihu.module_mine.myattention.presenter.ImpMyAttentionPresenter;
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

public class MyAttentionFragment extends BaseFragment implements IMyAttentionView, AppBarLayout.OnOffsetChangedListener {
    private final IMyAttentionPresenter iMyAttentionPresenter = new ImpMyAttentionPresenter(this);
    private Unbinder unbinder;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tabs)
    MagicIndicator mTabs;
    @BindView(R2.id.viewpager)
    ViewPager mViewpager;
    @BindView(R2.id.app_bar)
    AppBarLayout mAppBar;

    // AppBarLayout滚动上一次的verticalOffset
    private int mLastVerticalOffset = 0;

    private SupportFragment[] mSupportFragments = new SupportFragment[3];

    public static MyAttentionFragment newInstance(){
        Bundle args = new Bundle();
        MyAttentionFragment fragment = new MyAttentionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.module_mine_fragment_my_attention,container,false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R2.id.iv_back})
    public void onViewClicked(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            pop();
        }
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

    private void initViewPager() {
        mSupportFragments[0] = AttentionQuestionFragment.newInstance();
        mSupportFragments[1] = CircleFragment.newInstance();
        mSupportFragments[2] = ClassificationFragment.newInstance();

        CommonViewPagerAdapter adapter = new CommonViewPagerAdapter(getChildFragmentManager(), mSupportFragments);
        mViewpager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initIndicator() {
        IndicatorParamModel paramModel = new IndicatorParamModel();
        paramModel.setTextStyle(TextStyle.level_second);
        paramModel.setTitleStrings(_mActivity.getResources().getStringArray(R.array.module_mine_myattention_indicator));
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        HuihuNavigationAdapter navigationAdapter = new HuihuNavigationAdapter(mViewpager, paramModel);
        commonNavigator.setAdapter(navigationAdapter);
        mTabs.setNavigator(commonNavigator);
    }

    private void initListener() {
        mViewpager.setOffscreenPageLimit(3);
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

    private void initTitle() {
        mTvTitle.setText("关注");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    private void initAppBarLayout() {
        mAppBar.addOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout layout, int verticalOffset) {
        if (mLastVerticalOffset != verticalOffset) {
        }
    }
}
