package com.huihu.module_circle.circle.view.adpterinterface;

import android.view.View;

import com.huihu.module_circle.circle.entity.CircleBaseBean;

public interface OnCircleItemClickListener {
    void circleInfoClicked(View v, int tag, CircleBaseBean circleBaseBean,int type);
}
