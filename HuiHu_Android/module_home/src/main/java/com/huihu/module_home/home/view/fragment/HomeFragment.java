package com.huihu.module_home.home.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseBackFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.commonlib.utils.Constant;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.home.homeinterface.IHomePresenter;
import com.huihu.module_home.home.homeinterface.IHomeView;
import com.huihu.module_home.home.presenter.ImpHomePresenter;
import com.huihu.module_home.hotrank.view.fragment.HotRankFragment;
import com.huihu.module_home.popularIdea.view.fragment.PopularFragment;
import com.huihu.module_home.question.entity.TypeWrite;
import com.huihu.module_home.question.view.WriteQuestionFragment;
import com.huihu.module_home.recommend.view.fragment.RecommendFragment;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.adapter.CommonViewPagerAdapter;
import com.huihu.uilib.adapter.HuihuNavigationAdapter;
import com.huihu.uilib.checklogin.annotation.CheckLogin;
import com.huihu.uilib.model.IndicatorParamModel;
import com.huihu.uilib.model.TextStyle;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

@SimpleRouterClassRegister(key = RouterReDefine.HOME_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class HomeFragment extends BaseBackFragment implements IHomeView {
    private final IHomePresenter iHomePresenter = new ImpHomePresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.view_pager)
    ViewPager mViewpager;
    @BindView(R2.id.v_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R2.id.iv_icon_right)
    ImageView mIvIconRight;
    @BindView(R2.id.tv_right)
    TextView mTvRight;


    private Fragment[] mFragments = new Fragment[]{PopularFragment.newInstance(), HotRankFragment.newInstance(), RecommendFragment.newInstance()};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.module_home_fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewStyle();
        initIndicator();//初始化导航
        initViewPager();//初始化viewpager
        initListener();//初始化监听
    }

    private void initViewStyle() {
        mTvRight.setText(R.string.module_home_search_question);
        mIvIconRight.setImageResource(R.drawable.module_home_icon_answer_blue);
        TextViewUtils.setTextFakeBold(mTvRight);
    }

    private void initListener() {
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mMagicIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                mMagicIndicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                mMagicIndicator.onPageScrollStateChanged(state);
            }
        });
    }

    private void initViewPager() {
        CommonViewPagerAdapter adapter = new CommonViewPagerAdapter(getChildFragmentManager(), mFragments);
        mViewpager.setOffscreenPageLimit(3);
        mViewpager.setAdapter(adapter);
    }

    private void initIndicator() {
        IndicatorParamModel paramModel = new IndicatorParamModel();
        paramModel.setTextStyle(TextStyle.level_first);
        paramModel.setTitleStrings(_mActivity.getResources().getStringArray(R.array.module_home_indicator));
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        commonNavigator.setSkimOver(true);//开启跨页切换是否略过效果
        HuihuNavigationAdapter navigationAdapter = new HuihuNavigationAdapter(mViewpager, paramModel);
        commonNavigator.setAdapter(navigationAdapter);
        mMagicIndicator.setNavigator(commonNavigator);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R2.id.cl_right)
    public void onQuestion() {
        startQuestion();
    }

    @CheckLogin
    private void startQuestion() {
        ((SupportFragment) getParentFragment()).start(WriteQuestionFragment.newInstance(TypeWrite.question, Constant.DEFAULT_LONG));
    }

    @OnClick(R2.id.cl_search)
    public void onViewClicked() {
        ToastUtil.show("跳转搜索界面");
    }
}
