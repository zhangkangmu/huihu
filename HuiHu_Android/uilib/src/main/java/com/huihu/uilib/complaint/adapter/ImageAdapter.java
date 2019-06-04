package com.huihu.uilib.complaint.adapter;

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
 * Created by jiangwensong on 2019/4/11.
 * description：
 */
public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<AlbumImageBean> mImageBeanList;
    private View.OnClickListener mOnClickListener;


    public ImageAdapter(Context context, List<AlbumImageBean> imageBeanList){
        mImageBeanList = imageBeanList;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        if (type == 1){
            View view = LayoutInflater.from(mContext).inflate(R.layout.comresourcelib_item_album_photograph, viewGroup, false);
            return new LastImageViewHolder(view);
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.uilib_item_image, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ImageViewHolder){
            ((ImageViewHolder) viewHolder).ivImage.setImageBitmap(BitmapFactory.decodeFile(mImageBeanList.get(i).getPath()));
            ((ImageViewHolder) viewHolder).ivCancle.setTag(i);
            ((ImageViewHolder) viewHolder).ivCancle.setOnClickListener(mOnClickListener);
        }else if (viewHolder instanceof LastImageViewHolder){
            ((LastImageViewHolder) viewHolder).ivIcon.setOnClickListener(mOnClickListener);
        }

    }


    @Override
    public int getItemViewType(int position) {
        if(position == getItemCount() - 1){
            return 1;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return mImageBeanList.size() + 1;
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

    class LastImageViewHolder extends RecyclerView.ViewHolder{
        ImageView ivIcon;

        public LastImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
