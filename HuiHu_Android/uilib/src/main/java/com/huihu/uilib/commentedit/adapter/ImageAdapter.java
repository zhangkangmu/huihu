package com.huihu.uilib.commentedit.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huihu.uilib.R;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;

/**
 * Created by jiangwensong on 2019/4/2.
 * description：
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {


    private Context mContext;
    private List<AlbumImageBean> mImageBeanList;
    private View.OnClickListener mOnClickListener;

    public ImageAdapter(Context context, List<AlbumImageBean> imageBeanList){
        mImageBeanList = imageBeanList;
        mContext = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.uilib_item_image_60dp, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder viewHolder, int i) {
        viewHolder.ivImage.setImageBitmap(BitmapFactory.decodeFile(mImageBeanList.get(i).getPath()));
        viewHolder.ivCancle.setTag(i);
        viewHolder.ivCancle.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mImageBeanList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        mOnClickListener = listener;
        notifyDataSetChanged();
    }

    public void setDatas(List<AlbumImageBean> list){
        mImageBeanList = list;
        notifyDataSetChanged();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView ivImage;
        ImageView ivCancle;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
            ivCancle = itemView.findViewById(R.id.iv_image_cancle);

        }
    }
}
