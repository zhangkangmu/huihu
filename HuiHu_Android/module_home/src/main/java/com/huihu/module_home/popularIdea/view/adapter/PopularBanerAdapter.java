package com.huihu.module_home.popularIdea.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.huihu.module_home.R;
import com.huihu.module_home.popularIdea.enity.SwitchGrph;
import com.huihu.uilib.customize.CornerImageView;

import java.util.ArrayList;
import java.util.List;

public class PopularBanerAdapter extends PagerAdapter {
    private List<SwitchGrph> resIds = new ArrayList<>();
    private Context mContext;

    public void setResIds(Context mContext) {
        this.mContext = mContext;
        resIds.add(new SwitchGrph());
    }

    public void setData(List<SwitchGrph> resIds) {
        this.resIds = resIds;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return resIds.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(
            ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.module_home_item_popular_viewpager, null);
        CornerImageView itemImage = (CornerImageView) view.findViewById(R.id.item_image);
        Glide.with(mContext).load(resIds.get(position).getPicture())
                .placeholder(R.drawable.module_home_shape_search_bg)
                .into(itemImage);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

