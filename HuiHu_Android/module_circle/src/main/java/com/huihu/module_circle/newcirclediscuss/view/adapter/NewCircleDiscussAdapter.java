package com.huihu.module_circle.newcirclediscuss.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;
import com.huihu.module_circle.newcirclediscuss.view.adapterInterface.OnDeletedItemClickListen;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.util.MarkUtil;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewCircleDiscussAdapter extends RecyclerView.Adapter<NewCircleDiscussAdapter.NewCircleDiscussAdapterViewHold> implements View.OnClickListener {

    private Context mContext;
    private List<NewCircleDiscussInfo.PageDatasBean> datas = new ArrayList<>();

    private OnDeletedItemClickListen onDeletedItemClickListen;
    private onMyItemClickListener mListener;

    public void setOnItemClickListener(onMyItemClickListener listener) {
        mListener = listener;
    }

    public NewCircleDiscussAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setOnDeletedItemClickListen(OnDeletedItemClickListen mOnDeletedItemClickListen) {
        this.onDeletedItemClickListen = mOnDeletedItemClickListen;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                long ideaId = (long) v.getTag();
                mListener.onItemClick(ideaId);
            }
        }
    };

    @NonNull
    @Override
    public NewCircleDiscussAdapterViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_circle_item_new_discuss, viewGroup, false);
        view.setOnClickListener(this);
        return new NewCircleDiscussAdapterViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewCircleDiscussAdapterViewHold holder, int i) {
        holder.itemView.setTag(i);
        NewCircleDiscussInfo.PageDatasBean bean = datas.get(i);
        //头像
        Glide.with(mContext).load(bean.getUserInfo().getUserHeadImg_80()).into(holder.avata);
        holder.name.setText(bean.getUserInfo().getAuthName());
        holder.rl_person.setTag(i);
        holder.rl_person.setOnClickListener(this);
        //认证
        MarkUtil.markPerson(mContext,bean.getUserInfo().getIncMin(),holder.iv_auth);
        //关注
        holder.tv_attention.setTag(i);
        holder.tv_attention.setOnClickListener(this);
        //分享
        holder.iv_share.setTag(i);
        holder.iv_share.setOnClickListener(this);
        if (bean.getUserInfo().getFollow() == 0) {
            holder.tv_attention.setText("关注Ta");
            holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.global_blue));
            holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_litterwhite));
        } else {
            holder.tv_attention.setText("已关注");
            holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_blue));
            holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.white));
        }
        //发布时间
        holder.time.setText(TimeFormatUtils.toHuihuTime(bean.getCreateTime()));
        //圈子名称，简介
        holder.title.setText(bean.getTitle());
        holder.content.setText(bean.getBriefContent());
        //赞同总数
        holder.tv_popular_bottom_one.setText(bean.getAgreeCount() + " 赞同");
        //关注总数
        holder.tv_popular_bottom_two.setText(bean.getCommentCount() + " 赞同");
        //设置多张图片展示的方式
        List<NewCircleDiscussInfo.PageDatasBean.ImagesBean> images = bean.getImages();
        GridViewImageAdapter gridAdapter = new GridViewImageAdapter(mContext, images);
        if (images.size() <= 3) {
            holder.gridView.setNumColumns(images.size());
        } else {
            holder.gridView.setNumColumns(3);
        }
        holder.gridView.setAdapter(gridAdapter);

        holder.root.setTag(bean.getIdeaId());
        holder.root.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return datas.size();
        //        return 20;
    }

    @Override
    public void onClick(View v) {
        if (onDeletedItemClickListen != null) {
            onDeletedItemClickListen.deleteItemClick(v, (Integer) v.getTag());
        }
    }

    public void setCircleDiscussList(List<NewCircleDiscussInfo.PageDatasBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public List<NewCircleDiscussInfo.PageDatasBean> getmList() {
        return datas;
    }

    class NewCircleDiscussAdapterViewHold extends RecyclerView.ViewHolder {
        @BindView(R2.id.rl_person)
        RelativeLayout rl_person;
        @BindView(R2.id.root)
        ConstraintLayout root;
        @BindView(R2.id.iv_Avatar)
        RoundImageView avata;
        @BindView(R2.id.iv_auth)
        ImageView iv_auth;
        @BindView(R2.id.tv_title)
        TextView title;
        @BindView(R2.id.tv_time)
        TextView time;
        @BindView(R2.id.tv_name)
        TextView name;
        @BindView(R2.id.tv_content)
        TextView content;
        @BindView(R2.id.iv_share)
        ImageView iv_share;
        @BindView(R2.id.tv_attention)
        TextView tv_attention;
        @BindView(R2.id.tv_popular_bottom_one)
        TextView tv_popular_bottom_one;
        @BindView(R2.id.tv_popular_bottom_two)
        TextView tv_popular_bottom_two;
        @BindView(R2.id.gridview)
        GridView gridView;

        public NewCircleDiscussAdapterViewHold(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface onMyItemClickListener {
        void onItemClick(long ideaId);
    }

}
