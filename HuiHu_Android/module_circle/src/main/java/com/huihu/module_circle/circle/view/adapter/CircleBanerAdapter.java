package com.huihu.module_circle.circle.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_circle.R;
import com.huihu.module_circle.circle.entity.CircleBaseBean;
import com.huihu.module_circle.circle.entity.CircleInfo;
import com.huihu.module_circle.circle.view.adpterinterface.OnCircleBannerClickListener;
import com.huihu.module_circle.circle.view.adpterinterface.OnCircleItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class CircleBanerAdapter<T> extends PagerAdapter implements View.OnClickListener, OnCircleItemClickListener {

    private ArrayList<T> resIds = new ArrayList<>();
    private Context mContext;
    private OnCircleBannerClickListener mListener;
    private ImageView refreshData;
    private TextView tv_refreshData;

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<CircleBaseBean> resIds) {
        this.resIds = (ArrayList<T>) resIds;
        notifyDataSetChanged();
    }
    public ArrayList<T> getData() {
        return resIds;
    }

    public void setOnCircleInfoClickListener(OnCircleBannerClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getCount() {
        return resIds.size() == 0 ? 1 : resIds.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(
            ViewGroup container, int position) {
            View view = View.inflate(mContext, R.layout.module_circle_item_banner_viewpager, null);
            List<? extends CircleBaseBean> circleBeans = new ArrayList<>();
            if (resIds.size()==3)
            if (position == 0) {
                circleBeans = (List<CircleInfo.RecentCircleBean>) resIds.get(position);
            } else if (position == 1) {
                circleBeans = (List<CircleInfo.PopularCircleBean>) resIds.get(position);
            } else if (position == 2) {
                circleBeans = (List<CircleInfo.ActiveCircleBean>) resIds.get(position);
            }
            RecyclerView mRecycleView = (RecyclerView) view.findViewById(R.id.mRecycleView);
            initRecyclerView(mRecycleView, circleBeans,position);
            container.addView(view);
            return view;
    }


    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    public CircleItemAdapter adapter;

    private void initRecyclerView(RecyclerView mRecycleView, List<? extends CircleBaseBean> circleBeans,int position) {
        //1.最新，热门，活跃
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        adapter = new CircleItemAdapter();
        adapter.setContext(mContext,position);
        adapter.setmList(circleBeans);
        adapter.setOnItemClickListener(this);
        mRecycleView.setAdapter(adapter);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onClick(View v) {
        mListener.circleInfoClicked(v, (int) v.getTag(),null,-1);
    }

    @Override
    public void circleInfoClicked(View view, int position,CircleBaseBean bean,int type) {
        mListener.circleInfoClicked(view,position,bean,type);
    }
}

