package com.huihu.module_circle.circle.view.adpterinterface;

import android.view.View;

import com.huihu.module_circle.circle.entity.CircleBaseBean;

public interface OnCircleBannerClickListener {
    void circleInfoClicked(View view, int position, CircleBaseBean bean,int type);
}
