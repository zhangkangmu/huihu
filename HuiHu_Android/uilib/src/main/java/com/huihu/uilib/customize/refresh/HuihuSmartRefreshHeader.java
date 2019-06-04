package com.huihu.uilib.customize.refresh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.huihu.uilib.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.util.DensityUtil;

public class HuihuSmartRefreshHeader extends LinearLayout implements RefreshHeader {
    private CircularProgressDrawable circularProgressDrawable;;//刷新动画
    private CircleImageView mCircleView;//刷新动画视图

    public HuihuSmartRefreshHeader(Context context) {
        super(context);
        initView(context);
    }

    public HuihuSmartRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }
    public HuihuSmartRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }
    private void initView(Context context) {
        setGravity(Gravity.CENTER_HORIZONTAL);
        mCircleView = new CircleImageView(this.getContext(), -328966);
        circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setColorSchemeColors(getResources().getColor(R.color.global_blue));
        mCircleView.setImageDrawable(circularProgressDrawable);
        circularProgressDrawable.setStyle(1);
        mCircleView.setImageDrawable(circularProgressDrawable);
        mCircleView.setVisibility(VISIBLE);
        addView(mCircleView,DensityUtil.dp2px(40), DensityUtil.dp2px(40));
        setMinimumHeight(DensityUtil.dp2px(40));
    }


    @NonNull
    @Override
    public View getView() {
        return this;//真实的视图就是自己，不能返回null
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        circularProgressDrawable.start();//开始动画
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        circularProgressDrawable.stop();//停止动画
//        if (success){
//            mHeaderText.setText("刷新完成");
//        } else {
//            mHeaderText.setText("刷新失败");
//        }
        return 0;//延迟500毫秒之后再弹回;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
//                mHeaderText.setText("下拉开始刷新");
//                mArrowView.setVisibility(VISIBLE);//显示下拉箭头
                mCircleView.setVisibility(VISIBLE);//隐藏动画
//                mArrowView.animate().rotation(0);//还原箭头方向
                break;
            case Refreshing:
//                mHeaderText.setText("正在刷新");
                mCircleView.setVisibility(VISIBLE);//显示加载动画
//                mArrowView.setVisibility(GONE);//隐藏箭头
                break;
            case ReleaseToRefresh:
//                mHeaderText.setText("释放立即刷新");
//                mArrowView.animate().rotation(180);//显示箭头改为朝上
                break;
        }
    }
}