package com.huihu.module_notification.fans.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.module_notification.adapter.RecyclerViewPagerAdapter;
import com.huihu.module_notification.fans.FansConstant;
import com.huihu.module_notification.fans.fansinterface.IFansPresenter;
import com.huihu.uilib.statelayout.StateLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wangjing on 2019/3/8 0008
 * description:
 */
public class FansPagerAdapter extends RecyclerViewPagerAdapter {

    private Context mContext;
    private List<FansAdapter> adapters;
    private StateLayout followMeStateLayout, myFollowStateLayout;
    private final IFansPresenter presenter;

    public FansPagerAdapter(Context mContext, List<FansAdapter> adapters, IFansPresenter presenter) {
        this.mContext = mContext;
        this.adapters = adapters;
        this.presenter = presenter;
    }


    public StateLayout getFollowMeStateLayout() {
        return followMeStateLayout;
    }

    public StateLayout getMyFollowStateLayout() {
        return myFollowStateLayout;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    protected RecyclerView.Adapter getInternalAdapter(int position) {
        return adapters.get(position);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager(int position) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }

    @Override
    protected void setRecyclerView(RecyclerView rv, final int position) {
        if (position == 0){
            if (followMeStateLayout == null) followMeStateLayout = new StateLayout.Builder(rv).setOnRetryClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.v2pGetFirstData(FansConstant.FOLLOW_ME);
                }
            }).create();
        } else {
            if (myFollowStateLayout == null) myFollowStateLayout = new StateLayout.Builder(rv).setOnRetryClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.v2pGetFirstData(FansConstant.MY_FOLLOW);
                }
            }).create();
        }
        ((SimpleItemAnimator)rv.getItemAnimator()).setSupportsChangeAnimations(false);
    }
}
