package com.huihu.module_mine.community.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.answered.entity.AnsweredInfo;
import com.huihu.module_mine.community.entity.CommunityInfo;
import com.huihu.module_mine.community.view.adapterInterface.CommunityItemClickListener;
import com.huihu.uilib.util.CountUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ouyangjianfeng
 * @time 2019/2/28  15:09
 * @desc 提问和回答的item, 首页，圈子，个人中心都可复用
 */
public class CommunityListAdapter extends RecyclerView.Adapter<CommunityListAdapter.CommunityViewHolder> implements View.OnClickListener {
    public List<CommunityInfo.PageDatasBean> getmList() {
        return mList;
    }

    private List<CommunityInfo.PageDatasBean> mList= new ArrayList<>();

    private Context mContext;

    private CommunityItemClickListener mCommunityItemClickedListener;
    @NonNull
    @Override
    public CommunityViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_mine_community_item, group, false);
        return new CommunityViewHolder(view);
    }
    public void setOnCommunityItemClickedListener(CommunityItemClickListener mCommunityItemClickedListener){
        this.mCommunityItemClickedListener=mCommunityItemClickedListener;
    }
    public void  setContext(Context mContext){
        this.mContext=mContext;
    }

     public void setDatas(List<CommunityInfo.PageDatasBean> mList){
        this.mList=mList;
        notifyDataSetChanged();
     }
    @Override
    public void onBindViewHolder(@NonNull CommunityViewHolder holder, int i) {
        CommunityInfo.PageDatasBean datasBean = mList.get(i);
        holder.title.setText(datasBean.getTitle());
        TextViewUtils.setTextFakeBold(holder.title);
        holder.content.setText(datasBean.getBriefContent());
        holder.communityName.setText(datasBean.getCircleName());
        holder.tv_popular_bottom_one.setText(CountUtil.toHuihuCount(datasBean.getAgreeCount())+"赞同");
        holder.tv_popular_bottom_two.setText(CountUtil.toHuihuCount(datasBean.getCommentCount())+"评论");
        holder.itemView.setTag(i);
        holder.itemView.setOnClickListener(this);
        int state = datasBean.getState();
        if (state==1){
            holder.iv_popular_bottom_three.setBackground(mContext.getDrawable(R.drawable.module_mine_icon_share));
            holder.iv_popular_bottom_three.setTag(i);
            holder.iv_popular_bottom_three.setOnClickListener(this);
            holder.iv_popular_bottom_three.setText("");
        }else if(state==2){
            holder.iv_popular_bottom_three.setBackground(mContext.getDrawable(R.drawable.module_mine_error_def));
            holder.iv_popular_bottom_three.setText("违规下架");
        }else if(state==3){
            holder.iv_popular_bottom_three.setBackground(mContext.getDrawable(R.drawable.module_mine_icon_review_32_def));
            holder.iv_popular_bottom_three.setText("重新审核");
        }
        if (datasBean.getPopular()==1){
            holder.isHot.setVisibility(View.VISIBLE);
        }else {
            holder.isHot.setVisibility(View.GONE);
        }

        List<CommunityInfo.PageDatasBean.ImagesBean> images = datasBean.getImages();
        GridViewImageAdapter gridAdapter = new GridViewImageAdapter(mContext,images);
       holder.gridView.setNumColumns(images.size());
        holder.gridView.setAdapter(gridAdapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
    mCommunityItemClickedListener.onCommunityItemClick(v,(int)v.getTag(),getItemViewType((int)v.getTag()));
    }

    public class CommunityViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_title)
         TextView title;
        @BindView(R2.id.tv_content)
         TextView content;
        @BindView(R2.id.tv_communityName)
         TextView communityName;
        @BindView(R2.id.iv_popular_bottom_three)
        TextView iv_popular_bottom_three;
        @BindView(R2.id.tv_popular_bottom_two)
        TextView tv_popular_bottom_two;
        @BindView(R2.id.tv_popular_bottom_one)
        TextView tv_popular_bottom_one;
        @BindView(R2.id.isHot)
        ImageView isHot;
        @BindView(R2.id.gridview)
        GridView gridView;
        public CommunityViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
