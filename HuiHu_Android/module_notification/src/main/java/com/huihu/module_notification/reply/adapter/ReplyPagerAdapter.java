package com.huihu.module_notification.reply.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.module_notification.adapter.RecyclerViewPagerAdapter;
import com.huihu.module_notification.fans.adapter.FansAdapter;
import com.huihu.module_notification.reply.replyinterface.IReplyPresenter;
import com.huihu.uilib.statelayout.StateLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wangjing on 2019/3/8 0008
 * description:
 */
public class ReplyPagerAdapter extends RecyclerViewPagerAdapter {

    private Context mContext;
    //    利用RecycledViewPool优化
    private RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();

    private List<ReplyAdapter> adapters;
    private StateLayout mineStateLayout, attentionStateLayout;
    private final IReplyPresenter presenter;

    public ReplyPagerAdapter(Context mContext, IReplyPresenter iReplyPresenter) {
        this.mContext = mContext;
        recycledViewPool.setMaxRecycledViews(0, 15);
        presenter = iReplyPresenter;
    }

    public void setAdapters(List<ReplyAdapter> adapters) {
        this.adapters = adapters;
    }

    public StateLayout getMineStateLayout() {
        return mineStateLayout;
    }

    public StateLayout getAttentionStateLayout() {
        return attentionStateLayout;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    protected void setRecyclerView(RecyclerView rv, final int position) {
        rv.setRecycledViewPool(recycledViewPool);
        if (mineStateLayout == null && position == 0){
            mineStateLayout = new StateLayout.Builder(rv).setOnRetryClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.v2pGetFirstDataMine();
                }
            }).create();
        }
        if (attentionStateLayout == null && position == 1){
            attentionStateLayout = new StateLayout.Builder(rv).setOnRetryClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.v2pGetFirstDataAttention();
                }
            }).create();
        }


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

}
