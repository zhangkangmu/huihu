package com.huihu.module_maincontainer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.checklogin.annotation.CheckLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author ouyangjianfeng
 * @time 2019/2/26  15:24
 * @desc 主页的fragment
 */
public class MainFragment extends BaseFragment {

    private Unbinder unbinder;
    @BindView(R2.id.iv_home)
    ImageView mIvHome;
    @BindView(R2.id.iv_circle)
    ImageView mIvCircle;
    @BindView(R2.id.iv_message)
    ImageView mIvMessage;
    @BindView(R2.id.iv_mine)
    ImageView mIvMine;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public int preSelected = 0;
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private SupportFragment[] mFragments = new SupportFragment[]{
            (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.HOME_FRAGMENT),
            (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.CIRCLE_FRAGMENT),
            (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.NOTIFICATION_FRAGMENT),
            (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.MINE_FRAGMENT)};
    private int[] defIcon = new int[]{
            R.drawable.module_main_icon_home_def,
            R.drawable.module_main_icon_community_def,
            R.drawable.module_main_icon_message_def,
            R.drawable.module_main_icon_mine_def};
    private int[] selectedIcon = new int[]{
            R.drawable.module_main_icon_home_sel,
            R.drawable.module_mine_icon_community_sel,
            R.drawable.module_main_icon_message_sel,
            R.drawable.module_mine_icon_mine_sel};
    private ImageView[] mImageViews = new ImageView[4];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        initIconArray();
        loadFragment();
        showHideFragment(FIRST);
        setIcon(FIRST);
    }

    private void initIconArray() {
        mImageViews[0] = mIvHome;
        mImageViews[1] = mIvCircle;
        mImageViews[2] = mIvMessage;
        mImageViews[3] = mIvMine;
    }

    private void loadFragment() {
        loadMultipleRootFragment(R.id.fl_container, FIRST,
                mFragments[FIRST],
                mFragments[SECOND],
                mFragments[THIRD],
                mFragments[FOURTH]);
    }

    @OnClick({R2.id.cl_navigation_home, R2.id.cl_navigation_circle, R2.id.cl_navigation_notification, R2.id.cl_navigation_mine})
    public void onViewClicked(View view) {
        int viewId = view.getId();
        if (viewId == R.id.cl_navigation_home) {
            if (preSelected != FIRST) {
                showHideFragment(FIRST);
            }
        } else if (viewId == R.id.cl_navigation_circle) {
            if (preSelected != SECOND) {
                showHideFragment(SECOND);
            }
        } else if (viewId == R.id.cl_navigation_notification) {
            if (preSelected != THIRD) {
                showHideFragment(THIRD);
            }
        } else if (viewId == R.id.cl_navigation_mine) {
            checkLogin();
        }
    }

    private void showHideFragment(int selected) {
        setIcon(selected);
        showHideFragment(mFragments[selected], mFragments[preSelected]);
        preSelected = selected;
    }

    private void setIcon(int selected) {
        mImageViews[preSelected].setImageResource(defIcon[preSelected]);
        mImageViews[selected].setImageResource(selectedIcon[selected]);
    }

    /**
     * 检查是否登录
     */
    @CheckLogin
    private void checkLogin() {
        showHideFragment(FOURTH);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
