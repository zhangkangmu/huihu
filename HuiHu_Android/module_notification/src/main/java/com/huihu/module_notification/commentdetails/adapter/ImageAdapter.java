package com.huihu.module_notification.commentdetails.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_notification.R;
import com.huihu.module_notification.commentdetails.entity.ImageInfo;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<ImageInfo> mDatas;

    public ImageAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.uilib_item_image,viewGroup,false);
            return new ImageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ImageViewHolder){
            ((ImageViewHolder)viewHolder).setView(mDatas.get(i).getImageUrl());
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<ImageInfo> imageInfoList){
        mDatas = imageInfoList;
        notifyDataSetChanged();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
        }

        public void setView(String url){
            ImgTools.showImageView(mContext,url,ivImage);
        }
    }

}
