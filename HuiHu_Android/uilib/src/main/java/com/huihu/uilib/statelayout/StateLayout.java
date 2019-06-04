package com.huihu.uilib.statelayout;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.R;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

/**
 * create on 2018/10/16 0016
 *
 * @author wangjing
 */
public class StateLayout extends FrameLayout {

    private Context mContext;
    //默认加载中布局
    /**
     * 加载中布局
     */
    private View loadingLayout;
    /**
     * 是否使用了默认加载中布局
     */
    private boolean isDefaultLoading;

    //默认的空数据布局

    @StringRes
    private static final int DEFAULT_TIPS_NO_DATA = R.string.uilib_no_data_tip;
    @DrawableRes
    private static final int DEFAULT_DRAWABLE_NO_DATA = R.drawable.bg_no_data_def;
    /**
     * 无数据布局
     */
    private View emptyDataLayout;
    /**
     * 无数据布局配置对象
     */
    private AbnormalStateLayoutBuilder emptyDataLayoutBuilder;
    /**
     * 无数据布局提示文案
     */
    private String noDataTips;
    /**
     * 无数据布局提示图片
     */
    private int noDataDrawableRes;
    /**
     * 无数据布局提示图片高度-dps
     */
    private int noDataDrawableHeight;
    /**
     * 无数据布局提示图片宽度-dps
     */
    private int noDataDrawableWidth;
    /**
     * 无数据布局提示图片和文字间距-dps
     */
    private int noDataImageAndTextSpace;
    /**
     * 无数据布局背景颜色
     */
    private int noDataBackgroundColor;

    //默认的无网络布局

    @StringRes
    private static final int DEFAULT_TIPS_NO_NETWORK = R.string.uilib_net_fail_tip;
    @DrawableRes
    private static final int DEFAULT_DRAWABLE_NO_NETWORK = R.drawable.bg_net_fail_def;
    /**
     * 无网络布局
     */
    private View noNetworkLayout;
    /**
     * 无网络布局提示文案
     */
    private String noNetworkTips;
    /**
     * 无网络布局提示图片
     */
    private int noNetworkDrawableRes;
    /**
     * 无网络布局提示图片和文字间距-dps
     */
    private int noNetworkImageAndTextSpace;
    /**
     * 无网络布局背景颜色
     */
    private int noNetworkBackgroudColor;
    /**
     * 状态布局列表
     */
    private SparseArray<View> mStateViews = new SparseArray<>();
    private int state;
    private CircleProgressBar mProgressBar;


    private StateLayout(@NonNull Context context) {
        super(context);
    }

    public void showContent() {
        changeState(StateLayoutType.Content);
    }

    public void showLoadingData() {
        changeState(StateLayoutType.LoadingData);
    }

    public void showEmptyData() {
        changeState(StateLayoutType.EmptyData);
    }

    public void showNoNetwork() {
        changeState(StateLayoutType.NoNetwork);
    }

    public void showNetworkError() {
        changeState(StateLayoutType.NetworkError);
    }

    protected void init(Builder mBuilder) {
        mContext = mBuilder.mContext;
        addStateView(StateLayoutType.Content, mBuilder.content);
        addLoadingLayout(mBuilder);
        addEmptyDataLayout(mBuilder);
        addNoNetworkLayout(mBuilder);
        addStateView(StateLayoutType.NetworkError, mBuilder.networkError);
        changeState(StateLayoutType.Content);
    }

    /**
     * 添加加载中布局
     */
    private void addLoadingLayout(Builder mBuilder) {
        loadingLayout = mBuilder.loadingLayout;
        if (loadingLayout == null) {
            loadingLayout = LayoutInflater.from(mBuilder.mContext).inflate(R.layout.uilib_load_state, null);
            mProgressBar = loadingLayout.findViewById(R.id.progressBar);
            mProgressBar.setColorSchemeColors(getResources().getColor(R.color.global_blue));
        }
        addStateView(StateLayoutType.LoadingData, loadingLayout);
    }

    /**
     * 添加空布局
     */
    private void addEmptyDataLayout(Builder mBuilder) {
        emptyDataLayout = mBuilder.emptyDataLayout;
        if (emptyDataLayout == null) {
            emptyDataLayout = LayoutInflater.from(mBuilder.mContext).inflate(R.layout.module_uilib_layout_state_abnormal, null);
            emptyDataLayoutBuilder = mBuilder.emptyDataLayoutBuilder;
            if (emptyDataLayoutBuilder != null) {
                noDataTips = emptyDataLayoutBuilder.getTipsStr();
                noDataDrawableRes = emptyDataLayoutBuilder.getTipsDrawableRes();
                noDataImageAndTextSpace = emptyDataLayoutBuilder.getImageAndTextSpace();
                noDataBackgroundColor = emptyDataLayoutBuilder.getBackgroundColor();
            }
            TextView tvAbnormal = emptyDataLayout.findViewById(R.id.tv_abnormal);
            ImageView ivAbnormal = emptyDataLayout.findViewById(R.id.iv_abnormal);
            if (TextUtils.isEmpty(noDataTips)) {
                tvAbnormal.setText(DEFAULT_TIPS_NO_DATA);
            } else {
                tvAbnormal.setText(noDataTips);
            }
            if (noDataDrawableRes == 0) {
                ivAbnormal.setImageDrawable(ContextCompat.getDrawable(mBuilder.mContext, DEFAULT_DRAWABLE_NO_DATA));
            } else {
                ivAbnormal.setImageDrawable(ContextCompat.getDrawable(mBuilder.mContext, noDataDrawableRes));
            }
            if (noDataBackgroundColor != Color.TRANSPARENT) {
                emptyDataLayout.setBackgroundColor(noDataBackgroundColor);
            }
        }
        addStateView(StateLayoutType.EmptyData, emptyDataLayout);
    }

    /**
     * 添加无网络布局
     */
    private void addNoNetworkLayout(Builder mBuilder) {
        noNetworkLayout = mBuilder.noNetworkLayout;
        if (noNetworkLayout == null) {
            noNetworkLayout = LayoutInflater.from(mBuilder.mContext).inflate(R.layout.module_uilib_layout_state_abnormal, null);
            AbnormalStateLayoutBuilder noNetworkLayoutBuilder = mBuilder.noNetworkLayoutBuilder;
            if (noNetworkLayoutBuilder != null) {
                noNetworkTips = noNetworkLayoutBuilder.getTipsStr();
                noNetworkDrawableRes = noNetworkLayoutBuilder.getTipsDrawableRes();
                noNetworkImageAndTextSpace = noNetworkLayoutBuilder.getImageAndTextSpace();
                noNetworkBackgroudColor = noNetworkLayoutBuilder.getBackgroundColor();
            }
            TextView tvAbnormal = noNetworkLayout.findViewById(R.id.tv_abnormal);
            ImageView ivAbnormal = noNetworkLayout.findViewById(R.id.iv_abnormal);
            TextView tvRetry = noNetworkLayout.findViewById(R.id.tv_retry);
            tvRetry.setVisibility(VISIBLE);
            if (TextUtils.isEmpty(noNetworkTips)) {
                tvAbnormal.setText(DEFAULT_TIPS_NO_NETWORK);
            } else {
                tvAbnormal.setText(noNetworkTips);
            }
            if (noNetworkDrawableRes == 0) {
                ivAbnormal.setImageDrawable(ContextCompat.getDrawable(mBuilder.mContext, DEFAULT_DRAWABLE_NO_NETWORK));
            } else {
                ivAbnormal.setImageDrawable(ContextCompat.getDrawable(mBuilder.mContext, noNetworkDrawableRes));
            }
            if (mBuilder.onRetryClick != null)
                tvRetry.setOnClickListener(mBuilder.onRetryClick);
            if (noNetworkBackgroudColor != Color.TRANSPARENT) {
                noNetworkLayout.setBackgroundColor(noNetworkBackgroudColor);
            }
        }
        addStateView(StateLayoutType.NoNetwork, noNetworkLayout);
    }

    private void addStateView(@StateLayoutType int type, View v) {
        if (v == null) {
            return;
        }
        mStateViews.put(type, v);
        this.addView(v);
    }

    public int getState() {
        return state;
    }

    private void changeState(@StateLayoutType int type) {
        if (mStateViews.get(type) == null) {
            return;
        }
        int n = mStateViews.size();
        for (int i = 0; i < n; ++i) {
            View v = mStateViews.valueAt(i);
            int key = mStateViews.keyAt(i);
            if (key == type) {
                v.setVisibility(VISIBLE);
                state = type;
            } else {
                v.setVisibility(GONE);
            }
        }
    }

    public TextView getDefaultEmptyDataTextView() {
        return emptyDataLayout.findViewById(R.id.tv_abnormal);
    }

    /**
     * 获取无网络布局，主要是为了自定义的无网络布局
     */
    public View getNoNetworkLayout() {
        return noNetworkLayout;
    }

    /**
     * 获取空数据布局，主要是为了自定义的空数据布局
     */
    public View getEmptyDataLayout() {
        return emptyDataLayout;
    }

    /**
     * 获取加载中布局，主要为了自定义的加载中布局
     */
    public View getLoadingLayout() {
        return loadingLayout;
    }

    public static class Builder {

        private Context mContext;
        private StateLayout mStateLayout;
        private ViewGroup mRootView;
        View content;
        ViewStub networkError;
        private View loadingLayout;
        private View emptyDataLayout;
        private ViewStub noNetworkLayout;
        private AbnormalStateLayoutBuilder emptyDataLayoutBuilder;
        private AbnormalStateLayoutBuilder noNetworkLayoutBuilder;
        /**
         * 重新点击请求
         */
        private View.OnClickListener onRetryClick;

        public Builder(View targetView) {
            mContext = targetView.getContext();
            init();
            mRootView = (ViewGroup) targetView.getParent();
            content = targetView;
            int count = mRootView.getChildCount();
            int index = 0;
            for (int i = 0; i < count; ++i) {
                if (mRootView.getChildAt(i) == content) {
                    index = i;
                    break;
                }
            }
            replaceTargetView(index);
        }

        /**
         * 设置默认空数据布局相关配置
         */
        public Builder setDefaultEmptyDataLayoutConfig(AbnormalStateLayoutBuilder builder) {
            emptyDataLayoutBuilder = builder;
            return this;
        }

        /**
         * 设置默认无网络布局相关配置
         */
        public Builder setDefaultNoNetworkLayoutConfig(AbnormalStateLayoutBuilder builder) {
            noNetworkLayoutBuilder = builder;
            return this;
        }

        /**
         * 默认布局的点击事件
         * 1.无网络
         */
        public Builder setOnRetryClick(View.OnClickListener onRetryClick) {
            this.onRetryClick = onRetryClick;
            return this;
        }

        /**
         * 自定义空数据布局
         */
        public Builder setEmptyDataLayout(@LayoutRes int layoutId) {
            emptyDataLayout = LayoutInflater.from(mContext).inflate(layoutId, null);
            return this;
        }

        /**
         * 自定义无网络布局
         */
        public Builder setNoNetwork(@LayoutRes int layoutId) {
            noNetworkLayout = new ViewStub(mContext);
            noNetworkLayout.setLayoutResource(layoutId);
            return this;
        }

        /**
         * 自定义加载中布局
         */
        public Builder setLoadingLayout(@LayoutRes int layoutId) {
            loadingLayout = LayoutInflater.from(mContext).inflate(layoutId, null);
            return this;
        }


        public Builder setNetworkError(@LayoutRes int layoutId) {
            networkError = new ViewStub(mContext);
            networkError.setLayoutResource(layoutId);
            return this;
        }

        public StateLayout create() {
            mStateLayout.init(this);
            return mStateLayout;
        }


        private void init() {
            mStateLayout = new StateLayout(mContext);
        }


        private void replaceTargetView(int index) {
            ViewGroup.LayoutParams params = content.getLayoutParams();
            mRootView.removeView(content);
            //          因为不重新设置LayoutParams的话，会造成显示的位置和原来的不一样
            content.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mRootView.addView(mStateLayout, index, params);
        }
    }
}
