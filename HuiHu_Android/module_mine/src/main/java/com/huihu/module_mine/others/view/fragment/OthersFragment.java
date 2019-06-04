package com.huihu.module_mine.others.view.fragment;

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
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.huihu.commonlib.base.BaseBackFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
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
import com.huihu.module_mine.followsandfollowed.view.fragment.FollowsAndFollowedFragment;
import com.huihu.module_mine.others.entity.UserInfo;
import com.huihu.module_mine.others.othersinterface.IOthersPresenter;
import com.huihu.module_mine.others.othersinterface.IOthersView;
import com.huihu.module_mine.others.presenter.ImpOthersPresenter;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.adapter.CommonViewPagerAdapter;
import com.huihu.uilib.adapter.HuihuNavigationAdapter;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.model.IndicatorParamModel;
import com.huihu.uilib.model.TextStyle;
import com.huihu.uilib.util.CountUtil;
import com.huihu.uilib.util.DensityUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * “我的”模块首页
 */

@SimpleRouterClassRegister(key = RouterReDefine.OTHERS_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class OthersFragment extends BaseBackFragment implements IOthersView, AppBarLayout.OnOffsetChangedListener {
    private final IOthersPresenter iOthersPresenter = new ImpOthersPresenter(this);

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
    @BindView(R2.id.iv_enlarge)
    ImageView iv_enlarge;
    @BindView(R2.id.others_put_follows)
    TextView others_put_follows;
    @BindView(R2.id.cons_put_follows)
    ConstraintLayout cons_put_follows;

    // AppBarLayout滚动上一次的verticalOffset
    private int mLastVerticalOffset = 0;
    private long uid;

    private SupportFragment[] mSupportFragments = new SupportFragment[4];
    public static OthersFragment newInstance(long uid) {
        OthersFragment fragment = new OthersFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("uid", uid);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.module_mine_fragment_others, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

        private void initData() {
            Bundle bundle = getArguments();
            if (null != bundle) {
                uid = bundle.getLong("uid");
            }
        }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAppBarLayout();
        initIndicator();
        initViewPager();
        initListener();//初始化监听
        initData();
        iOthersPresenter.v2pGetUserInfo(uid);
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
        mSupportFragments[0] = AnsweredFragment.newInstance(uid);
        mSupportFragments[1] = CommunityFragment.newInstance(uid);
        mSupportFragments[2] = CommentFragment.newInstance(uid);
        mSupportFragments[3] = AttitudeFragment.newInstance(uid);
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
            user_name_small.setAlpha(scale);

        }
    }

    @OnClick({R2.id.iv_setting,R2.id.tv_fxchat_guide,R2.id.constrainLayout_sign,R2.id.cons_put_follows,R2.id.cl_attention,R2.id.cl_fans,R2.id.iv_back})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.iv_setting) {
            start(OthersForMoreFragment.newInstance(userInfo.getUid()));
        } else if(i==R.id.tv_fxchat_guide){
            startAPP("com.fxchat");
        }else if (i==R.id.constrainLayout_sign){
           start(AuthenticationFragment.newInstance(SPUtils.getInstance().getCurrentUid()));
        }else if(i==R.id.cons_put_follows){
            ToastUtil.show("关注");
            others_put_follows.setCompoundDrawablesWithIntrinsicBounds(getActivity().getDrawable(R.drawable.module_mine_icon_follow_2_40_blue_def),null,null,null);
            others_put_follows.setText("已关注");
            others_put_follows.setTextColor(getActivity().getResources().getColor(R.color.global_blue));
            cons_put_follows.setBackground(getActivity().getResources().getDrawable(R.drawable.module_mine_shape_litterwhite));
            iv_enlarge.setRotation(180);
        }else if(i==R.id.cl_attention){
            start(FollowsAndFollowedFragment.newInstance(uid,1,"TA关注的人"));
        }else if(i==R.id.cl_fans){
            start(FollowsAndFollowedFragment.newInstance(uid,2,"TA的粉丝"));
        }else if (i==R.id.iv_back){
            pop();
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
    //设置用户信息
    private UserInfo userInfo;
    private void setUserInfo(UserInfo userInfo) {
        this.userInfo=userInfo;
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
        Glide.with(getActivity()).load(userInfo.getUserHeadImage()).placeholder(R.drawable.module_mine_bg_avatar_154_def).into(roundImg_user);
    }


}
