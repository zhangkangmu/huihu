package com.huihu.module_home.draftmanager.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.draftmanager.draftmanagerinterface.IDraftManagerPresenter;
import com.huihu.module_home.draftmanager.draftmanagerinterface.IDraftManagerView;
import com.huihu.module_home.draftmanager.presenter.ImpDraftManagerPresenter;
import com.huihu.module_home.questiondraft.entity.DraftType;
import com.huihu.module_home.questiondraft.view.QuestionDraftFragment;
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

public class DraftManagerFragment extends BaseFragment implements IDraftManagerView {
    private final IDraftManagerPresenter iDraftManagerPresenter = new ImpDraftManagerPresenter(this);
    @BindView(R2.id.indicator)
    MagicIndicator mIndicator;
    @BindView(R2.id.vp_content)
    ViewPager mVpContent;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_draft, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        initIndicator();
        initVp();
    }

    private void initData() {

    }

    private void initVp() {
        SupportFragment[] mFragments = new SupportFragment[]{QuestionDraftFragment.newInstance(DraftType.question), QuestionDraftFragment.newInstance(DraftType.answer)};
        CommonViewPagerAdapter adapter = new CommonViewPagerAdapter(getChildFragmentManager(), mFragments);
        mVpContent.setAdapter(adapter);

        mVpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mIndicator.onPageScrollStateChanged(state);
            }
        });
    }

    private void initIndicator() {
        IndicatorParamModel paramModel = new IndicatorParamModel();
        paramModel.setTextStyle(TextStyle.level_second);
        paramModel.setTitleStrings(_mActivity.getResources().getStringArray(R.array.module_home_draft));
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        commonNavigator.setSkimOver(true);
        HuihuNavigationAdapter navigationAdapter = new HuihuNavigationAdapter(mVpContent, paramModel);
        commonNavigator.setAdapter(navigationAdapter);
        mIndicator.setNavigator(commonNavigator);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R2.id.iv_back)
    public void onBack() {
        pop();
    }
}
