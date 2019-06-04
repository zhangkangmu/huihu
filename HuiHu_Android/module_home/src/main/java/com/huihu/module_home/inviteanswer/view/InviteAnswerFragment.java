package com.huihu.module_home.inviteanswer.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.inviteanswer.inviteanswerinterface.IInviteAnswerPresenter;
import com.huihu.module_home.inviteanswer.inviteanswerinterface.IInviteAnswerView;
import com.huihu.module_home.inviteanswer.presenter.ImpInviteAnswerPresenter;
import com.huihu.module_home.inviteanswerlist.view.InviteAnswerListFragment;
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

public class InviteAnswerFragment extends BaseFragment implements IInviteAnswerView {
    private final IInviteAnswerPresenter iInviteAnswerPresenter = new ImpInviteAnswerPresenter(this);

    public static final int TYPE_RECOMMEND = 0;//推荐关注
    public static final int TYPE_ATTENTION = 1;//已关注

    Unbinder unbinder;
    @BindView(R2.id.indicator_content)
    MagicIndicator mIndicatorContent;
    @BindView(R2.id.vp_content)
    ViewPager mVpContent;

    public static InviteAnswerFragment newInstance(long questionId) {
        InviteAnswerFragment fragment = new InviteAnswerFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("questionId", questionId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_invite_answer, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        initIndicator();
        initVp();
    }

    private void initVp() {
        SupportFragment[] mFragments = new SupportFragment[2];
        Bundle bundle = getArguments();
        if (null != bundle) {
            long questionId = bundle.getLong("questionId");
            mFragments = new SupportFragment[]{InviteAnswerListFragment.newInstance(TYPE_RECOMMEND, questionId), InviteAnswerListFragment.newInstance(TYPE_ATTENTION, questionId)};
        }
        CommonViewPagerAdapter adapter = new CommonViewPagerAdapter(getChildFragmentManager(), mFragments);
        mVpContent.setAdapter(adapter);

        mVpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicatorContent.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mIndicatorContent.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mIndicatorContent.onPageScrollStateChanged(state);
            }
        });
    }

    private void initIndicator() {
        IndicatorParamModel paramModel = new IndicatorParamModel();
        paramModel.setTextStyle(TextStyle.level_second);
        paramModel.setTitleStrings(_mActivity.getResources().getStringArray(R.array.module_home_invite_answer));
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        commonNavigator.setSkimOver(true);
        HuihuNavigationAdapter navigationAdapter = new HuihuNavigationAdapter(mVpContent, paramModel);
        commonNavigator.setAdapter(navigationAdapter);
        mIndicatorContent.setNavigator(commonNavigator);
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
