package com.huihu.module_mine.classificationattention.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_mine.R;
import com.huihu.module_mine.classificationattention.entity.ClassificationAttentionInfo;
import com.huihu.module_mine.classificationattention.view.adapterInterface.OnAttentionItemClickListen;

import java.util.ArrayList;
import java.util.List;

public class ClassificationAdapter extends RecyclerView.Adapter<ClassificationAdapter.ClassificationAdapterViewHolder> implements View.OnClickListener{
    private List<ClassificationAttentionInfo.PageDatasBean> mPageDatasList= new ArrayList<>();
    private Context mContext;
    private boolean mIsAttention=true;

    private OnAttentionItemClickListen mOnAttentionItemClickListen;
    public ClassificationAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setOnAttentionItemClickListen(OnAttentionItemClickListen mOnAttentionItemClickListen){
        this.mOnAttentionItemClickListen=mOnAttentionItemClickListen;
    }

    @NonNull
    @Override
    public ClassificationAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_mine_item_join, viewGroup, false);
        view.setOnClickListener(this);
        return new ClassificationAdapterViewHolder(view);
    }

    public void  setContext(Context mContext){
        this.mContext=mContext;
    }

    public void setClassificationList(List<ClassificationAttentionInfo.PageDatasBean> pageDatasList) {
        this.mPageDatasList = pageDatasList;
        notifyDataSetChanged();
    }

    public void setClassificationItemChange(List<ClassificationAttentionInfo.PageDatasBean> pageDatasList,boolean isAttention,int position) {
        this.mPageDatasList = pageDatasList;
        this.mIsAttention=isAttention;
        notifyItemChanged(position);
    }

    @Override
    public void onBindViewHolder(@NonNull  ClassificationAdapterViewHolder classificationAdapterViewHolder, int i) {
        classificationAdapterViewHolder.itemView.setTag(i);
        ImgTools.showImageView(mContext,mPageDatasList.get(i).getPicture(),classificationAdapterViewHolder.iv_info_icon);
        classificationAdapterViewHolder.tv_title.setText(mPageDatasList.get(i).getCategory());
        classificationAdapterViewHolder.tv_amount.setText(mPageDatasList.get(i).getFollowCount() + "人");
    }

    @Override
    public int getItemCount() {
        return mPageDatasList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnAttentionItemClickListen != null) {
            mOnAttentionItemClickListen.itemClick(v, (int) v.getTag(),getItemViewType((int) v.getTag()));
        }
    }

    public List<ClassificationAttentionInfo.PageDatasBean> getmList() {
        return mPageDatasList;
    }


    public class ClassificationAdapterViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_info_icon;
        TextView tv_title;
        TextView tv_amount;

        public ClassificationAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_info_icon = itemView.findViewById(R.id.iv_info_icon);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_amount = itemView.findViewById(R.id.tv_amount);
        }
    }
}
