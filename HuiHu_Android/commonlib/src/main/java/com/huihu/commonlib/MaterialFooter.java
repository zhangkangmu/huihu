package com.huihu.commonlib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * create by ouyangjianfeng on 2019/4/5
 * description:
 */
public class MaterialFooter extends LinearLayout implements RefreshFooter {


    private View mView;

    public MaterialFooter(Context context) {
        super(context);
        initView(context);

    }

    private void initView(Context context) {
        mView = LayoutInflater.from(context).inflate(R.layout.uilib_refresh_footer, null);
        CircleProgressBar circleProgressBar = mView.findViewById(R.id.progressBar);
        circleProgressBar.setColorSchemeColors(getResources().getColor(R.color.common_blue));
    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        return false;
    }

    @NonNull
    @Override
    public View getView() {
        return mView;
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

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        return 0;
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
            case LoadFinish:
            case LoadReleased:
                break;
            case Loading:
                break;
        }
    }
}
