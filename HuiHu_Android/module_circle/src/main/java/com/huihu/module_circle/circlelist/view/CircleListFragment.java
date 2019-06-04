package com.huihu.module_circle.circlelist.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.circlelist.circlelistinterface.ICircleListPresenter;
import com.huihu.module_circle.circlelist.circlelistinterface.ICircleListView;
import com.huihu.module_circle.circlelist.entity.CircleListInfo;
import com.huihu.module_circle.circlelist.presenter.ImpCircleListPresenter;
import com.huihu.module_circle.circlemember.view.fragment.CircleMemberFragment;
import com.huihu.module_circle.circlesetting.view.fragment.CircleSettingFragment;
import com.huihu.module_circle.invitejoincircle.view.fragment.InviteJoinCircleFragment;
import com.huihu.module_circle.mydiscuss.view.fragment.MyDiscussFragment;
import com.huihu.module_circle.newcirclediscuss.view.fragment.NewCircleDiscussFragment;
import com.huihu.module_circle.newcircleintroduction.view.NewCircleIntroductionFragment;
import com.huihu.module_circle.newcirclerecommend.view.fragment.NewCircleRecommendFragment;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.adapter.CommonViewPagerAdapter;
import com.huihu.uilib.adapter.HuihuNavigationAdapter;
import com.huihu.uilib.customize.CornerImageView;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.model.IndicatorParamModel;
import com.huihu.uilib.model.TextStyle;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

@SimpleRouterClassRegister(key = RouterReDefine.CIRCLELIST_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class CircleListFragment extends BaseFragment implements ICircleListView, View.OnClickListener {
    private final ICircleListPresenter iCircleListPresenter = new ImpCircleListPresenter(this);
    private static final String CIRCLEID = "circleId";
    private static final String UID = "uid";
    @BindView(R2.id.indicator)
    MagicIndicator mIndicator;
    @BindView(R2.id.view_pager)
    ViewPager mViewPager;
    @BindView(R2.id.iv_category)
    ImageView category;
    @BindView(R2.id.tv_title)
    TextView tv_title;
    @BindView(R2.id.tv_category_follow)
    TextView tv_category_follow;
    @BindView(R2.id.tv_do)
    TextView tv_do;
    @BindView(R2.id.ll_num_Avatar)
    LinearLayout ll_num_Avatar;
    @BindView(R2.id.iv_other)
    ImageView iv_other;
    @BindView(R2.id.cv_invite)
    CornerImageView cv_invite;
    @BindView(R2.id.tv_manager_circle)
    TextView tv_manager_circle;

    Unbinder unbinder;
    private SupportFragment[] mSupportFragments = new SupportFragment[4];
    private Long mCircleId;
    private Long mUid;

    private CircleListInfo mInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_circle_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCircleId = getArguments().getLong(CIRCLEID, 0);
        mUid = getArguments().getLong(UID, 0);
        iCircleListPresenter.v2pRequestCircleMessage(mCircleId, mUid);
    }

    public static CircleListFragment newInstance(long circleId, long uid) {
        Bundle args = new Bundle();
        args.putLong(CIRCLEID, circleId);
        args.putLong(UID, uid);
        CircleListFragment fragment = new CircleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView() {
        //导航tab
        initIndicator();
        //初始化viewpager
        initViewPager();
        //初始化监听
        initListener();
    }

    private void initListener() {
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

    private void initViewPager() {
        CommonViewPagerAdapter adapter = new CommonViewPagerAdapter(getChildFragmentManager(), mSupportFragments);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(adapter);
        mSupportFragments[0] = NewCircleIntroductionFragment.newInstance(mCircleId, mUid);
        mSupportFragments[1] = NewCircleDiscussFragment.newInstance(mCircleId, mUid);
        mSupportFragments[2] = NewCircleRecommendFragment.newInstance(mCircleId, mUid);
        mSupportFragments[3] = MyDiscussFragment.newInstance(mCircleId, mUid);

        CommonViewPagerAdapter commonViewPagerAdapter = new CommonViewPagerAdapter(getChildFragmentManager(), mSupportFragments);
        mViewPager.setAdapter(commonViewPagerAdapter);
        mViewPager.setCurrentItem(1);
    }

    private void initIndicator() {
        IndicatorParamModel paramModel = new IndicatorParamModel();
        paramModel.setTextStyle(TextStyle.level_second);
        paramModel.setTitleStrings(_mActivity.getResources().getStringArray(R.array.module_circle_list_indicator));
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        commonNavigator.setSkimOver(true);//开启跨页切换是否略过效果
        HuihuNavigationAdapter navigationAdapter = new HuihuNavigationAdapter(mViewPager, paramModel);
        commonNavigator.setAdapter(navigationAdapter);
        mIndicator.setNavigator(commonNavigator);
        mIndicator.onPageSelected(1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R2.id.cl_back)
    public void onBack() {
        pop();
    }

    //请求成功
    @Override
    public void p2vReturnCircleList(CircleListInfo info) {
        this.mInfo = info;
        ImgTools.showImageViewWithConner(_mActivity, info.getImageUrl(), category, 4);
        tv_do.setOnClickListener(this);
        cv_invite.setOnClickListener(this);
        //        tv_manager_circle.setOnClickListener(this);
        tv_title.setText(info.getCircleName());
        tv_category_follow.setText(info.getMemberNum() + " 成员");
        changeMemberType(mInfo);
        LinearLayout.LayoutParams layoutParams;
        layoutParams = new LinearLayout.LayoutParams(26, 26);
        RoundImageView imageView;
        int numSize = info.getMembers().size();
        if (numSize >= 4) {
            for (int i = 0; i < 4; i++) {
                if (1 == 0) {
                    layoutParams.setMargins(0, 0, 0, 0);
                } else {
                    layoutParams.setMargins(0, 0, -6, 0);
                }
                imageView = new RoundImageView(getContext());
                imageView.setPadding(0, 0, 3, 0);
                Glide.with(getContext()).load(info.getMembers().get(i).getUserHeadImage()).into(imageView);
                ll_num_Avatar.addView(imageView, 0, layoutParams);
                iv_other.setVisibility(View.VISIBLE);
            }
        } else {
            for (int i = 0; i < numSize; i++) {
                if (1 == 0) {
                    layoutParams.setMargins(0, 0, 0, 0);
                } else {
                    layoutParams.setMargins(0, 0, -6, 0);
                }
                imageView = new RoundImageView(getContext());
                imageView.setPadding(0, 0, 3, 0);
                Glide.with(getContext()).load(info.getMembers().get(i).getUserHeadImage()).into(imageView);
                ll_num_Avatar.addView(imageView, 0, layoutParams);
                iv_other.setVisibility(View.INVISIBLE);
            }
        }
    }

    //向简介展示数据
    public CircleListInfo showCircleList() {
        return mInfo;
    }

    @Override
    public void v2pReturnJoinSucces(CircleListInfo mInfo) {
        this.mInfo = mInfo;
        changeMemberType(mInfo);
    }

    public void changeMemberType(CircleListInfo mInfo){
        //当前用户类型（0: 未加入 1：成员 2：管理员 3：负责人）
        int type=mInfo.getMemberType();
        if (type == 0) {
            tv_do.setText("加入");
            cv_invite.setVisibility(View.INVISIBLE);
            //管理圈子消失
            tv_manager_circle.setVisibility(View.INVISIBLE);
        } else if (type == 1) {
            //成员
            tv_do.setText("邀请");
            //这里需要改,为了数据临时有
            cv_invite.setVisibility(View.VISIBLE);
//            tv_manager_circle.setVisibility(View.INVISIBLE);
            tv_manager_circle.setVisibility(View.VISIBLE);
        } else if (type == 2) {
            //管理员
            tv_do.setText("邀请");
            cv_invite.setVisibility(View.VISIBLE);
            tv_manager_circle.setVisibility(View.VISIBLE);
        } else if (type == 3) {
            //负责人
            tv_do.setText("邀请");
            cv_invite.setVisibility(View.VISIBLE);
            tv_manager_circle.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_do) {
            if (mInfo.getMemberType() == 0) {
                //加入
                iCircleListPresenter.v2pJoinCircle(mInfo, false);
            } else {
                //邀请
                ToastUtil.show("邀请");
                this.start(InviteJoinCircleFragment.newInstance(true));
            }
        }else if (id == R.id.cv_invite) {
            ToastUtil.show("退出");
            new AlertDialog.Builder(getContext())
                    .setTitle("请确认")
                    .setMessage("确定退出圈子吗？")
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            iCircleListPresenter.v2pJoinCircle(mInfo,true);
                        }
                    })
                    .setNegativeButton("否", null)
                    .show();
        }
    }

    @OnClick({R2.id.tv_manager_circle,R2.id.ll_num_Avatar})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_manager_circle) {
            //管理圈子，右上角的
            ToastUtil.show("" + mInfo.getMemberType());
            if (mInfo.getMemberType() == 1) {
                start(CircleSettingFragment.newInstance(mInfo.getCircleId(),mInfo.getMemberType()));
            }
//            } else if (mInfo.getMemberType() == 2) {
//                start(MemberManagementFragment.newInstance(mInfo.getCircleId(), mInfo.getMemberType()));
//            }
        }else if (id == R.id.ll_num_Avatar){
            //圈子成员，图标
            start(CircleMemberFragment.newInstance());
        }
    }

    @OnClick(R2.id.iv_create_discuss)
    public void onCreateDiscuss() {
        Bundle bundle = new Bundle();
        bundle.putLong("ciecleId", mCircleId);
        bundle.putInt("type", 2);
        SupportFragment fragment = (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.FRAGMENT_QUESTION, bundle);
        start(fragment);
    }
}
