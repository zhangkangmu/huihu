package com.huihu.module_mine.favoritesanswered.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.module_mine.R;
import com.huihu.module_mine.favoritesanswered.entity.FavoritesAnsweredInfo;
import com.huihu.module_mine.favoritesanswered.view.adapterInterface.OnDeletedItemClickListen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FavoritesAnsweredAdapter extends RecyclerView.Adapter<FavoritesAnsweredAdapter.FavoritesAnsweredAdapterViewHold> implements View.OnClickListener{

    private Context mContext;
    List<FavoritesAnsweredInfo.PageDatasBean> mPageDatas=new ArrayList<>();
    private OnDeletedItemClickListen mOnDeletedItemClickListen;
    public void setOnDeletedItemClickListen(OnDeletedItemClickListen mOnDeletedItemClickListen) {
        this.mOnDeletedItemClickListen = mOnDeletedItemClickListen;
    }

    @NonNull
    @Override
    public FavoritesAnsweredAdapterViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_mine_favorites_answer_item, viewGroup, false);
        view.setOnClickListener(this);
        return new FavoritesAnsweredAdapterViewHold(view);
    }

    public FavoritesAnsweredAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<FavoritesAnsweredInfo.PageDatasBean> getmList() {
        return mPageDatas;
    }
    @Override
    public void onBindViewHolder(@NonNull FavoritesAnsweredAdapterViewHold holder, int i) {
        FavoritesAnsweredInfo.PageDatasBean bean = mPageDatas.get(i);
        holder.itemView.setTag(i);
        holder.title.setText(bean.getTitle());
        holder.content.setText(bean.getBriefContent());
        //设置删除按钮
        holder.icon_delete.setTag(i);
        holder.icon_delete.setOnClickListener(this);
        //设置时间
        long createTime = bean.getEditTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date(createTime);
        holder.time.setText(simpleDateFormat.format(date));
        //设置多张图片展示的方式
        List<FavoritesAnsweredInfo.PageDatasBean.ImagesBean> images = bean.getImages();
        QuestionGridImageAdapter gridAdapter = new QuestionGridImageAdapter(mContext, images);
        if (images.size() <= 3) {
            holder.gridView.setNumColumns(images.size());
        } else {
            holder.gridView.setNumColumns(3);
        }
        holder.gridView.setAdapter(gridAdapter);
    }

    @Override
    public int getItemCount() {
        return mPageDatas.size();
    }

    public void setDatas(List<FavoritesAnsweredInfo.PageDatasBean> pageDatas) {
        this.mPageDatas = pageDatas;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        mOnDeletedItemClickListen.deleteItemClick(v,(int) v.getTag(),getItemViewType((int) v.getTag()));
    }
    public List<FavoritesAnsweredInfo.PageDatasBean> geItemDatasBean(){
        return mPageDatas;
    };

    public void removeItem(int position) {
        mPageDatas.remove(position);
        notifyDataSetChanged();
    }

    public class FavoritesAnsweredAdapterViewHold extends RecyclerView.ViewHolder{
        TextView title;
        TextView content;
        TextView time;
        GridView gridView;
        ImageView icon_delete;
        public FavoritesAnsweredAdapterViewHold(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);
            time = itemView.findViewById(R.id.tv_time);
            gridView = itemView.findViewById(R.id.gridview);
            icon_delete = itemView.findViewById(R.id.icon_delete);
        }
    }
}
