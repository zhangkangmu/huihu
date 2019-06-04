package com.huihu.module_notification.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.huihu.module_notification.fans.adapter.FansAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * create by wangjing on 2019/3/8 0008
 * description: RecyclerView的PagerAdapter, 每一页都是RecyclerView
 */
public abstract class RecyclerViewPagerAdapter extends PagerAdapter {

    private LinkedList<FrameLayout> frameList = new LinkedList<>();
    private int cacheCount = 3;

    public void setCacheCount(int cacheCount) {
        this.cacheCount = cacheCount;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        FrameLayout frameLayout = null;
        RecyclerView rv;
        if (frameList.size() > 0) frameLayout = frameList.removeFirst();
        if (frameLayout == null){
            frameLayout = new FrameLayout(container.getContext());
            rv = new RecyclerView(container.getContext());
            frameLayout.addView(rv, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            container.addView(frameLayout);
            rv.setLayoutManager(getLayoutManager(position));
            rv.setAdapter(getInternalAdapter(position));
        } else {
            rv = (RecyclerView) frameLayout.getChildAt(0);
        }
        setRecyclerView(rv, position);
        return frameLayout;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        FrameLayout frameLayout = frameList.get(position);
        if (frameLayout != null){
            container.removeView(frameLayout);
            if (frameList.size() < cacheCount) frameList.add(frameLayout);
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    /**
     * 这个方法主要是留给RecyclerView优化的
     * @param rv 初始化的RecyclerView
     * @param position 位置
     */
    protected void setRecyclerView(RecyclerView rv, int position){

    }

    abstract protected RecyclerView.Adapter getInternalAdapter(int position);

    abstract protected RecyclerView.LayoutManager getLayoutManager(int position);
}
