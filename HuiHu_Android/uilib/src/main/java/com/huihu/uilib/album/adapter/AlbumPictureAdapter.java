package com.huihu.uilib.album.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.huihu.uilib.R;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;

/**
 * create by wangjing on 2019/3/14 0014
 * description:
 */
public class AlbumPictureAdapter extends RecyclerView.Adapter<AlbumPictureAdapter.AlbumPictureViewHolder> {

    private List<AlbumImageBean> imageBeanList;
    private Context mContext;

    public AlbumPictureAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setImageBeanList(List<AlbumImageBean> imageBeanList) {
        this.imageBeanList = imageBeanList;
    }


    @NonNull
    @Override
    public AlbumPictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comresourcelib_item_picture, viewGroup, false);
        return new AlbumPictureViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumPictureViewHolder holder, int i) {
        String path = imageBeanList.get(i).getPath();
        String url = imageBeanList.get(i).getUrl();
        if (!TextUtils.isEmpty(path)) {
            Glide.with(mContext).load(path).into(holder.photoView);
        } else if (!TextUtils.isEmpty(url)){
            Glide.with(mContext).load(url).into(holder.photoView);
        }

    }


    @Override
    public int getItemCount() {
        return imageBeanList == null ? 0 : imageBeanList.size();
    }

    static class AlbumPictureViewHolder extends AlbumAdapter.AlbumViewHolder{

        PhotoView photoView;

        AlbumPictureViewHolder(@NonNull View itemView) {
            super(itemView);
            photoView = itemView.findViewById(R.id.iv_photo);
        }
    }
}
