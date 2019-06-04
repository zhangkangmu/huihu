package com.huihu.module_mine.favoritesdisuss.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.module_mine.R;
import com.huihu.module_mine.favoritesdisuss.entity.FavoritesDiscussInfo;
import com.huihu.module_mine.favoritesdisuss.view.adapterInterface.OnDeletedItemClickListen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FavoritesDisussAdapter extends RecyclerView.Adapter<FavoritesDisussAdapter.FavoritesDisussAdapterViewHold>implements View.OnClickListener{

    private Context mContext;

    List<FavoritesDiscussInfo.PageDatasBean> mDatas=new ArrayList<>();

    private OnDeletedItemClickListen mOnDeletedItemClickListen;
    public void setOnDeletedItemClickListen(OnDeletedItemClickListen mOnDeletedItemClickListen) {
        this.mOnDeletedItemClickListen = mOnDeletedItemClickListen;
    }

    public FavoritesDisussAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FavoritesDisussAdapterViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_mine_favorites_discuss_item, viewGroup, false);
        return new FavoritesDisussAdapter.FavoritesDisussAdapterViewHold(view);
    }

    public List<FavoritesDiscussInfo.PageDatasBean> getmList() {
        return mDatas;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesDisussAdapterViewHold holder, int i) {
        FavoritesDiscussInfo.PageDatasBean bean = mDatas.get(i);
        holder.title.setText(bean.getTitle());
        holder.content.setText(bean.getBriefContent());
        holder.communityName.setText(bean.getCircleName());
        //设置删除按钮
        holder.icon_delete.setTag(i);
        holder.icon_delete.setOnClickListener(this);
        //设置时间
        long createTime = bean.getEditTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date(createTime);
        holder.time.setText(simpleDateFormat.format(date));
        //设置多张图片展示的方式
        List<FavoritesDiscussInfo.PageDatasBean.ImagesBean> images = bean.getImages();
        GridViewImageAdapter gridAdapter = new GridViewImageAdapter(mContext, images);
        if (images.size() <= 3) {
            holder.gridView.setNumColumns(images.size());
        } else {
            holder.gridView.setNumColumns(3);
        }
        holder.gridView.setAdapter(gridAdapter);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<FavoritesDiscussInfo.PageDatasBean> datas) {
        this.mDatas=datas;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        mOnDeletedItemClickListen.deleteItemClick(v,(int) v.getTag(),getItemViewType((int) v.getTag()));
    }

    public class FavoritesDisussAdapterViewHold extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;
        TextView time;
        TextView communityName;
        GridView gridView;
        ImageView icon_delete;
        public FavoritesDisussAdapterViewHold(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
            time = itemView.findViewById(R.id.tv_time);
            communityName=itemView.findViewById(R.id.tv_communityName);
            gridView = itemView.findViewById(R.id.gridview);
            icon_delete = itemView.findViewById(R.id.icon_delete);
        }
    }
}
