package com.huihu.module_mine.mine.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.base.BaseBackFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.uilib.statelayout.StateLayout;
import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.answered.view.fragment.AnsweredFragment;
import com.huihu.module_mine.attitude.view.fragment.AttitudeFragment;
import com.huihu.module_mine.authentication.view.AuthenticationFragment;
import com.huihu.module_mine.comment.view.fragment.CommentFragment;
import com.huihu.module_mine.community.view.fragment.CommunityFragment;
import com.huihu.module_mine.favorites.view.FavoritesFragment;
import com.huihu.module_mine.followsandfollowed.view.fragment.FollowsAndFollowedFragment;
import com.huihu.module_mine.mine.entity.UserInfo;
import com.huihu.module_mine.mine.mineinterface.IMinePresenter;
import com.huihu.module_mine.mine.mineinterface.IMineView;
import com.huihu.module_mine.mine.presenter.ImpMinePresenter;
import com.huihu.module_mine.myattention.view.MyAttentionFragment;
import com.huihu.module_mine.setting.view.SettingFragment;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.adapter.CommonViewPagerAdapter;
import com.huihu.uilib.adapter.HuihuNavigationAdapter;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.evenbusBean.NotificationRefresh;
import com.huihu.uilib.model.IndicatorParamModel;
import com.huihu.uilib.model.TextStyle;
import com.huihu.uilib.util.CountUtil;
import com.huihu.uilib.util.DensityUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * “我的”模块首页
 */

@SimpleRouterClassRegister(key = RouterReDefine.MINE_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class MineFragment extends BaseBackFragment implements IMineView, AppBarLayout.OnOffsetChangedListener {
    private final IMinePresenter iMinePresenter = new ImpMinePresenter(this);
    @BindView(R2.id.viewpager)
    ViewPager mViewpager;
    Unbinder unbinder;
    @BindView(R2.id.tabs)
    MagicIndicator mTabs;
    @BindView(R2.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R2.id.iv_setting)
    ImageView mIvSetting;
    @BindView(R2.id.constrainLayout_top)
    ConstraintLayout constrainLayout_top;
    @BindView(R2.id.toolBar_bg)
    View toolbar_bg;
    @BindView(R2.id.user_logo)
    RoundImageView user_logo;
    @BindView(R2.id.user_name_small)
    TextView user_name_small;
    @BindView(R2.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R2.id.roundImg_user)
    RoundImageView roundImg_user;
    @BindView(R2.id.tv_fxchat_key)
    TextView tv_fxchat_key;
    @BindView(R2.id.tv_trade_certification)
    TextView tv_trade_certification;
    @BindView(R2.id.tv_signature)
    TextView tv_signature;
    @BindView(R2.id.tv_praise_value)
    TextView tv_praise_value;
    @BindView(R2.id.tv_fans_value)
    TextView tv_fans_value;
    @BindView(R2.id.tv_attention_value)
    TextView tv_attention_value;
    @BindView(R2.id.relativeLayout_mine)
    RelativeLayout relativeLayout_mine;

    // AppBarLayout滚动上一次的verticalOffset
    private int mLastVerticalOffset = 0;
    private StateLayout stateLayout;
    private SupportFragment[] mSupportFragments = new SupportFragment[4];



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.module_mine_fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStateLayout();
        initAppBarLayout();
        initIndicator();
        initViewPager();
        initListener();//初始化监听
        EventBus.getDefault().register(this);
        iMinePresenter.v2pGetUserInfo( SPUtils.getInstance().getCurrentUid());
    }

    private void initStateLayout() {
        StateLayout.Builder builder = new StateLayout.Builder(mViewpager);
        builder.setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stateLayout.showLoadingData();
            }
        });
        stateLayout = builder.create();
    }
    private void initAppBarLayout() {
        mAppBar.addOnOffsetChangedListener(this);
        toolbar_bg.setAlpha(0);
        mIvSetting.setAlpha(1);
        user_logo.setVisibility(View.GONE);
        user_name_small.setVisibility(View.GONE);
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

    private void initViewPager() {
        mSupportFragments[0] = AnsweredFragment.newInstance(SPUtils.getInstance().getCurrentUid());
        mSupportFragments[1] = CommunityFragment.newInstance(SPUtils.getInstance().getCurrentUid());
        mSupportFragments[2] = CommentFragment.newInstance(SPUtils.getInstance().getCurrentUid());
        mSupportFragments[3] = AttitudeFragment.newInstance(SPUtils.getInstance().getCurrentUid());
        CommonViewPagerAdapter adapter = new CommonViewPagerAdapter(getChildFragmentManager(), mSupportFragments);
        mViewpager.setAdapter(adapter);
    }

    private void initIndicator() {
        IndicatorParamModel paramModel = new IndicatorParamModel();
        paramModel.setTextStyle(TextStyle.level_second);
        paramModel.setTitleStrings(_mActivity.getResources().getStringArray(R.array.module_mine_indicator));
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        HuihuNavigationAdapter navigationAdapter = new HuihuNavigationAdapter(mViewpager, paramModel);
        commonNavigator.setAdapter(navigationAdapter);
        mTabs.setNavigator(commonNavigator);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshUI(NotificationRefresh messageEvent){
        iMinePresenter.v2pGetUserInfo(SPUtils.getInstance().getCurrentUid());
    }
    @Override
    public void onOffsetChanged(AppBarLayout layout, int verticalOffset) {
        if (mLastVerticalOffset != verticalOffset) {
            float appBarHeight = mAppBar.getHeight() -mTabs.getHeight() - DensityUtil.dip2px(getActivity(),44);
            mLastVerticalOffset = verticalOffset;
            float scale = -verticalOffset / appBarHeight;
            toolbar_bg.setAlpha(scale);
            user_logo.setVisibility(View.VISIBLE);
            user_name_small.setVisibility(View.VISIBLE);
            user_logo.setAlpha(scale);
            if (scale==1){
                mIvSetting.setBackground(getResources().getDrawable(R.drawable.module_mine_icon_setting_56_def));
            }else {
                mIvSetting.setBackground(getResources().getDrawable(R.drawable.module_mine_icon_set_64));
            }
            user_name_small.setAlpha(scale);

        }
    }

    @OnClick({R2.id.iv_setting,R2.id.tv_add_attention,R2.id.tv_fxchat_guide,R2.id.constrainLayout_sign,R2.id.tv_favorites,R2.id.cl_attention,R2.id.cl_fans})
    public void onViewClicked(View view) {
        int i=view.getId();
        if (i == R.id.iv_setting) {
            ((SupportFragment) getParentFragment()).start(SettingFragment.newInstance());
        } else if (i == R.id.tv_add_attention) {
            ((SupportFragment) getParentFragment()).start(MyAttentionFragment.newInstance());
        }else if (i == R.id.tv_favorites) {
            ((SupportFragment) getParentFragment()).start(FavoritesFragment.newInstance());
        }else if(i==R.id.tv_fxchat_guide){
            startAPP("com.fxchat");
        }else if (i==R.id.constrainLayout_sign){
            ((SupportFragment)getParentFragment()).start(AuthenticationFragment.newInstance(SPUtils.getInstance().getCurrentUid()));
        }else if (i==R.id.cl_attention){
            ((SupportFragment)getParentFragment()).start(FollowsAndFollowedFragment.newInstance(SPUtils.getInstance().getCurrentUid(),1,"我关注的人"));
        }else if(i==R.id.cl_fans){
            ((SupportFragment)getParentFragment()).start(FollowsAndFollowedFragment.newInstance(SPUtils.getInstance().getCurrentUid(),2,"我的粉丝"));
        }
    }
    public void startAPP(String appPackageName){
        try{
            Intent intent =getActivity().getPackageManager().getLaunchIntentForPackage(appPackageName);
            startActivity(intent);
        }catch(Exception e){
            ToastUtil.show("没有安装");
        }
    }


    @Override
    public void p2vGetUserInfoSuccess(UserInfo userInfo) {
        setUserInfo(userInfo);
    }
    @Override
    public void p2vGetUserInfoFailed() {

    }

    @Override
    public void p2vNetFail() {
        stateLayout.showNoNetwork();
    }

    @Override
    public void p2vGetDataEnd() {

    }

    //设置用户信息
    private void setUserInfo(UserInfo userInfo) {
        tv_user_name.setText(userInfo.getNickName());
        user_name_small.setText(userInfo.getNickName());
        tv_fxchat_key.setText("汇聊号："+userInfo.getFxCode());
        List<UserInfo.UserAuthShowModelListBean> list = userInfo.getUserAuthShowModelList();
        //四种认证类型
        if(list.size()!=0){

        }
        for (int i = 0; i <list.size() ; i++) {
//            Log.i("ccc",list.get(i).getAuthBewrite());
        }
        if (!("").equals(userInfo.getSignature()))
        tv_signature.setText(userInfo.getSignature());
        tv_praise_value.setText(CountUtil.toHuihuCount(userInfo.getAgreeNum()));
        tv_fans_value.setText(CountUtil.toHuihuCount(userInfo.getFansNum()));
        tv_attention_value.setText(CountUtil.toHuihuCount(userInfo.getFollowNum()));
       ImgTools.showImageView(getActivity(),userInfo.getUserHeadImage(),user_logo);
        Glide.with(getActivity()).load(userInfo.getUserHeadImage()).into(roundImg_user);
    }
}
