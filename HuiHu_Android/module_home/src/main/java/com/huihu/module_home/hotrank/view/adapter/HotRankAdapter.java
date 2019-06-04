package com.huihu.module_home.hotrank.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.hotrank.entity.HotRankBean;
import com.huihu.module_home.hotrank.view.adapterInface.OnHotRankItemClickListener;
import com.huihu.uilib.customize.CornerImageView;
import com.huihu.uilib.util.CountUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by ouyangjianfeng on 2019/3/14
 * description:首页热榜adapter
 *
 */
public class HotRankAdapter extends RecyclerView.Adapter<HotRankAdapter.HotRankViewHolder>implements View.OnClickListener {

    /**
     * 数据类型有三种
     * ideaType 1 2 3
     * */
    private int TYPE_IDEA1=1;
    private int TYPE_IDEA2=2;
    private int TYPE_IDEA3=3;
    private Context mContext;
    private List<HotRankBean.PageDatasBean> mList=new ArrayList<>();

    public List<HotRankBean.PageDatasBean> getmList() {
        return mList;
    }

    public void setmList(List<HotRankBean.PageDatasBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
    private OnHotRankItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnHotRankItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setContext(Context mContext){
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public HotRankViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_item_hot_rank, group, false);

        return new HotRankViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        HotRankBean.PageDatasBean moreTypeBean = mList.get(position);
        if (moreTypeBean.getIdeaType() == 1) {
            return TYPE_IDEA1;
        } else if (moreTypeBean.getIdeaType() == 2) {
            return TYPE_IDEA2;
        } else {
            return TYPE_IDEA3;
        }
       // return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull HotRankViewHolder holder, int i) {

        holder.itemView.setTag(i);
        holder.itemView.setOnClickListener(this);
        HotRankBean.PageDatasBean hotBankDataBean = mList.get(i);
        int number=i+1;
        holder.tv_rank.setText(""+number);
        holder.tv_title.setText(hotBankDataBean.getTitle());
        TextViewUtils.setTextFakeBold(holder.tv_title);
        TextViewUtils.setTextFakeBold(holder.tv_rank);
        ImgTools.showImageView(mContext,hotBankDataBean.getImageUrl(),holder.iv_hotRank);
        holder.tv_hot.setText(CountUtil.toHuihuCount(hotBankDataBean.getBrowse())+"热度");
        if (hotBankDataBean.getIdeaType()==1) {
            holder.iv_start.setBackground(mContext.getDrawable(R.drawable.module_home_label_answer_40_def));
            holder.tv_comment.setText(CountUtil.toHuihuCount(hotBankDataBean.getAnswerCount())+"回答");
        }else if (hotBankDataBean.getIdeaType()==2){
            holder.iv_start.setBackground(mContext.getDrawable(R.drawable.module_home_label_reply_40_def));
            holder.tv_comment.setText(CountUtil.toHuihuCount(hotBankDataBean.getCommentCount())+"评论");
        }else {
            holder.iv_start.setBackground(mContext.getDrawable(R.drawable.module_home_label_community_40_def));
            holder.tv_comment.setText(CountUtil.toHuihuCount(hotBankDataBean.getCommentCount())+"评论");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClick(v,(int) v.getTag(),getItemViewType((int) v.getTag()));
    }

    public class HotRankViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_rank)
        TextView tv_rank;
        @BindView(R2.id.iv_pic)
        ImageView iv_start;
        @BindView(R2.id.tv_title)
        TextView tv_title;
        @BindView(R2.id.iv_hotRank)
        CornerImageView iv_hotRank;
        @BindView(R2.id.tv_hot)
        TextView tv_hot;
        @BindView(R2.id.tv_comment)
        TextView tv_comment;

        public HotRankViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
