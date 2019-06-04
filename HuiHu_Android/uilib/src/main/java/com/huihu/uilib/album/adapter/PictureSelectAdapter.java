package com.huihu.uilib.album.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.huihu.uilib.R;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;

/**
 * create by wangjing on 2019/3/14 0014
 * description:
 */
public class PictureSelectAdapter extends RecyclerView.Adapter<PictureSelectAdapter.AlbumSelectPictureViewHolder> {

    private List<AlbumImageBean> imageBeanList;
    private Context mContext;
    private int mCurrentPosition;
    private OnClickSelectListener listener;

    public PictureSelectAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setImageBeanList(List<AlbumImageBean> imageBeanList) {
        this.imageBeanList = imageBeanList;
    }

    public void setCurrentPosition(int mCurrentPosition) {
        this.mCurrentPosition = mCurrentPosition;
    }

    public void setListener(OnClickSelectListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AlbumSelectPictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comresourcelib_item_select_picture, viewGroup, false);
        return new AlbumSelectPictureViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumSelectPictureViewHolder holder, final int i) {
        Glide.with(mContext).load(imageBeanList.get(i).getPath()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onClick(imageBeanList.get(i));
            }
        });
        if (i == mCurrentPosition){
            holder.imageView.setBackgroundResource(R.drawable.background_select_picture);
        } else {
            holder.imageView.setBackground(null);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull AlbumSelectPictureViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.size() > 0){
            if (payloads.get(0) instanceof Integer ){
                int index = (int) payloads.get(0);
                if (index > -1){
                    holder.imageView.setBackgroundResource(R.drawable.background_select_picture);
                } else {
                    holder.imageView.setBackground(null);
                }
            }
        } else {
            this.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return imageBeanList == null ? 0 : imageBeanList.size();
    }

    static class AlbumSelectPictureViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        AlbumSelectPictureViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_select_picture);
        }
    }

    public interface OnClickSelectListener{
        void onClick(AlbumImageBean bean);
    }
}
