package com.huihu.module_mine.followsandfollowed.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.followsandfollowed.entity.FollowsBean;
import com.huihu.module_mine.followsandfollowed.view.adapterInterface.OnFollowsAndFansItemClick;
import com.huihu.uilib.customize.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowsAndFansAdapter extends RecyclerView.Adapter<FollowsAndFansAdapter.ViewHolder> implements View.OnClickListener {

    private List<FollowsBean.PageDatasBean> mList=new ArrayList<>();
    private Context mContext;
    private OnFollowsAndFansItemClick mClickListener;
    public void setOnFollowsAndFansItemClickListener(OnFollowsAndFansItemClick mClickListener){
        this.mClickListener=mClickListener;
    }
    public void setmList(List<FollowsBean.PageDatasBean> mList){
        this.mList=mList;
        notifyDataSetChanged();
    }
    public void setContext(Context mContext) {
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public FollowsAndFansAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_mine_follow2fans_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowsAndFansAdapter.ViewHolder viewHolder, int i) {
        ImgTools.showImageView(mContext,mList.get(i).getUserHeadImg(),viewHolder.rv_user_logn);
        viewHolder.tv_attention.setTag(i);
        viewHolder.tv_user_name.setText(mList.get(i).getNickName());
        viewHolder.tv_attention.setOnClickListener(this);
        viewHolder.tv_user_name.setTag(i);
        viewHolder.rv_user_logn.setTag(i);
        viewHolder.tv_user_name.setOnClickListener(this);
        viewHolder.rv_user_logn.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        mClickListener.onFollowsAndFaansItemClick(v,(int)v.getTag());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.rv_user)
        RoundImageView rv_user_logn;
        @BindView(R2.id.tv_name_user)
        TextView tv_user_name;
        @BindView(R2.id.tv_attention)
        TextView tv_attention;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
