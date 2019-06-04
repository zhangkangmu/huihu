package com.huihu.module_circle.discuss.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.discuss.entity.RecemendDiscussInfo;
import com.huihu.module_circle.discuss.view.adapterInterface.OnRecommendDiscussItemClickListener;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.util.CountUtil;
import com.huihu.uilib.util.TimeFormatUtils;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by caicancan on 2019/4/17
 * description:圈子的推荐讨论
 *
 */
public class DiscussLsitAdapter extends RecyclerView.Adapter<DiscussLsitAdapter.ViewHolder>implements View.OnClickListener {


    private Context mContext;
    private List<RecemendDiscussInfo.PageDatasBean> mList=new ArrayList<>();
    private OnRecommendDiscussItemClickListener mListener;
    public List<RecemendDiscussInfo.PageDatasBean> getmList() {
        return mList;
    }

    public void setmList(List<RecemendDiscussInfo.PageDatasBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void setOnRecommendDiscussItemListener(OnRecommendDiscussItemClickListener mListener){
        this.mListener=mListener;
    }

    public void setContext(Context mContext){
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_circle_recommendlist_item, group, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {

       return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        RecemendDiscussInfo.PageDatasBean bean = mList.get(i);
        ImgTools.showImageView(mContext,bean.getUserInfo().getUserHeadImage(),holder.rv_user);
        holder.tv_community.setText(bean.getUserInfo().getNickName()+"·"+ TimeFormatUtils.toHuihuTime(bean.getCreateTime())+"发布");
        holder.tv_communityName.setText(bean.getCircleName());
        holder.tv_title.setText(mList.get(i).getTitle());
        holder.tv_popular_bottom_one.setText(CountUtil.toHuihuCount(bean.getAgreeCount())+"赞同");
        holder.tv_popular_bottom_two.setText(CountUtil.toHuihuCount(bean.getCommentCount())+"关注");
        List<RecemendDiscussInfo.PageDatasBean.ImagesBean> images = bean.getImages();
        GridViewImageAdapter gridAdapter = new GridViewImageAdapter(mContext,images);
        if (images.size()<=3){
            holder.gridView.setNumColumns(images.size());
        }else{
            holder.gridView.setNumColumns(3);
        }
        holder.gridView.setAdapter(gridAdapter);
        holder.iv_delete.setTag(i);
        holder.iv_delete.setOnClickListener(this);
        holder.share.setTag(i);
        holder.share.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        mListener.OnRecommendDiscussItemClick(v,(int)v.getTag());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       @BindView(R2.id.rv_user)
        RoundImageView rv_user;
       @BindView(R2.id.tv_title)
        TextView tv_title;
       @BindView(R2.id.tv_community)
       TextView tv_community;
       @BindView(R2.id.tv_communityName)
       TextView tv_communityName;
       @BindView(R2.id.gridview)
        GridView gridView;
       @BindView(R2.id.tv_popular_bottom_one)
       TextView tv_popular_bottom_one;
       @BindView(R2.id.tv_popular_bottom_two)
       TextView tv_popular_bottom_two;
       @BindView(R2.id.share)
        ImageView share;
       @BindView(R2.id.iv_delete)
       ImageView  iv_delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
