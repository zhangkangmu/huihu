package com.huihu.module_mine.comment.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;

import com.huihu.module_mine.comment.entity.CommentInfo;
import com.huihu.module_mine.comment.view.adapterInterface.OnCommentItemClickListener;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.customize.source.SourceView;
import com.huihu.uilib.util.CountUtil;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ouyangjianfeng
 * @time 2019/2/28  15:09
 * @desc 提问和回答的item, 首页，圈子，个人中心都可复用
 */
public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> implements View.OnClickListener {

    private Context mContext;
    private OnCommentItemClickListener mOnCommentItemClickListener;

    public void setmContext(Context mContext){
        this.mContext=mContext;
    }
    public void setOnCommentItemClickListener(OnCommentItemClickListener mOnCommentItemClickListener){
        this.mOnCommentItemClickListener=mOnCommentItemClickListener;
    }
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_mine_comment_item, group, false);
        return new CommentViewHolder(view);
    }

    public List<CommentInfo.PageDatasBean> getmList() {
        return mList;
    }

    private List<CommentInfo.PageDatasBean> mList= new ArrayList<>();
     public void setDatas(List<CommentInfo.PageDatasBean> mList){
        this.mList=mList;
        notifyDataSetChanged();
     }
    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int i) {
        CommentInfo.PageDatasBean bean = mList.get(i);
        List<CommentInfo.PageDatasBean.CommentImagesBean> images = bean.getCommentImages();
        holder.user_name.setText(bean.getUserInfo().getNickName());
        holder.itemView.setTag(i);
        holder.itemView.setOnClickListener(this);
        TextViewUtils.setTextFakeBold(holder.user_name);
        holder.tv_content.setText(bean.getComment());
        ImgTools.showImageView(mContext,bean.getUserInfo().getUserHeadImg_48(),holder.user_logo);
        holder.iv_delete.setTag(i);
        holder.iv_delete.setOnClickListener(this);
        if (bean.getAgreeCount()!=0) {
            holder.iv_like.setText("" + CountUtil.toHuihuCount(bean.getAgreeCount()));
        }else {
            holder.iv_like.setText("赞");
        }
        GridViewImageAdapter gridAdapter = new GridViewImageAdapter(mContext,images);
        if (images.size()<=3) {
            holder.gridView.setNumColumns(images.size());
        }else {
            holder.gridView.setNumColumns(3);
        }
        holder.sourceView.setImageUrl(R.id.iv_source_article, bean.getUserInfo().getUserHeadImg_48());
        holder.sourceView.setText(R.id.tv_source_article, bean.getComment());
        holder.gridView.setAdapter(gridAdapter);
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.show("position"+position+"-----parent"+position);
            }
        });
        holder.tv_submit_time.setText(TimeFormatUtils.toHuihuTime(bean.getCommentTime())+"回答");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        mOnCommentItemClickListener.OnCommentItemClick(v,(int)v.getTag(),getItemViewType((int)v.getTag()));
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
         @BindView(R2.id.tv_user_name)
         TextView user_name;
         @BindView(R2.id.rv_user_logo)
         RoundImageView user_logo;
         @BindView(R2.id.tv_content)
         TextView tv_content;
         @BindView(R2.id.iv_delete)
         ImageView iv_delete;
         @BindView(R2.id.gridview)
         GridView gridView;
         @BindView(R2.id.iv_like)
         TextView iv_like;
         @BindView(R2.id.source_comment)
         SourceView sourceView;
         @BindView(R2.id.tv_submit_time)
         TextView tv_submit_time;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
