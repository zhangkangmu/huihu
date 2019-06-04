package com.huihu.uilib.customize.source;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huihu.uilib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * create by wangjing on 2019/3/10 0010
 * description:
 */
public class MorePictureAdapter extends RecyclerView.Adapter<MorePictureAdapter.MorePictureViewHolder> {

    private List<String> urlList = new ArrayList<>();

    public List<String> getUrlList() {
        return urlList;
    }

    @NonNull
    @Override
    public MorePictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.uilib_item_more_picture, viewGroup, false);
        return new MorePictureViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MorePictureViewHolder holder, int i) {
        String url = urlList.get(i);
        Glide.with(holder.itemView.getContext()).load(url).into(holder.ivPicture);
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    static class MorePictureViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPicture;

        MorePictureViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPicture = itemView.findViewById(R.id.iv_picture);
        }
    }
}
