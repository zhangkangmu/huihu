package com.huihu.module_home.home.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.module_home.R;
import com.huihu.module_home.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by ouyangjianfeng on 2019/3/15
 * description:
 */
public class HomeMultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_VIEWPAGER = 0;//顶部viewpager
    private static final int TYPE_MULTI = 1;//图文多变的item

    private static final int LIMIT_OFFSCREENT_COUNT = 3;

    private Context mContext;

    public HomeMultiAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == TYPE_VIEWPAGER) {
            View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_item_viewpager, group, false);
            viewHolder = new ViewPagerViewHolder(view);
        } else if (viewType == TYPE_MULTI) {
            View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_item_multi, group, false);
            viewHolder = new MultiViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewPagerViewHolder) {
            ViewPagerViewHolder viewPagerViewHolder = (ViewPagerViewHolder) holder;
            viewPagerViewHolder.setGallery();
        } else if (holder instanceof MultiViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.view_pager)
        ViewPager mViewPager;

        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void setGallery() {
            mViewPager.setOffscreenPageLimit(LIMIT_OFFSCREENT_COUNT);
            mViewPager.setPageMargin(mContext.getResources().getDimensionPixelOffset(R.dimen.page_margin));
            mViewPager.setAdapter(new MyPagerAdapter(mContext));
        }
    }

    class MultiViewHolder extends RecyclerView.ViewHolder {

        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
