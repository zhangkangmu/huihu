package com.huihu.uilib.adapter;

import android.Manifest;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bailun.wangjing.permissionlibrary.annotation.RequestPermission;
import com.bumptech.glide.Glide;
import com.huihu.uilib.R2;
import com.huihu.uilib.R;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.album.view.AlbumActivity;
import com.huihu.uilib.album.view.LookPicturesFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * create by wangjing on 2019/3/25 0025
 * description:
 */
public class SelectThumbnailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AlbumActivity.OnSelectEndListener {

    private static final int SELECT_TYPE = 1000;
    private static final int PHOTOGRAPH_TYPE = 1001;

    private List<AlbumImageBean> thumbnailUrls = new ArrayList<>();
    private int limit = 9;
    private SupportFragment fragment;
    private OnDataChangeListener dataChangeListener;

    public SelectThumbnailAdapter(SupportFragment fragment) {
        this.fragment = fragment;
    }

    private View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof AlbumImageBean){
                AlbumImageBean bean = (AlbumImageBean) v.getTag();
                delete(bean);
            }
        }
    };
    private View.OnClickListener startOtherListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof AlbumImageBean){
                AlbumImageBean bean = (AlbumImageBean) v.getTag();
                int index = thumbnailUrls.indexOf(bean);
                fragment.start(LookPicturesFragment.newInstance(index, thumbnailUrls
                        , new LookPicturesFragment.OnDeleteListener() {
                            @Override
                            public void onDelete(AlbumImageBean bean) {
                                delete(bean);
                            }
                }, true));
            }
        }
    };

    public List<AlbumImageBean> getThumbnailUrls() {
        return thumbnailUrls;
    }

    public void setThumbnailUrls(List<AlbumImageBean> thumbnailUrls) {
        this.thumbnailUrls = thumbnailUrls;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public OnDataChangeListener getDataChangeListener() {
        return dataChangeListener;
    }

    public void setDataChangeListener(OnDataChangeListener dataChangeListener) {
        this.dataChangeListener = dataChangeListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == SELECT_TYPE){
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.uilib_item_select_thumbnail, viewGroup, false);
            return new SelectViewHolder(v);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.comresourcelib_item_album_photograph, viewGroup, false);
            return new PhotographViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SelectViewHolder){
            SelectViewHolder holder = (SelectViewHolder) viewHolder;
            AlbumImageBean bean = thumbnailUrls.get(i);
            Context context = holder.itemView.getContext();
            Glide.with(context).load(bean.getPath()).into(holder.ivSelectThumbnail);
            holder.ivDelete.setTag(bean);
            holder.ivDelete.setOnClickListener(deleteListener);
            holder.itemView.setTag(bean);
            holder.itemView.setOnClickListener(startOtherListener);
        } else if (viewHolder instanceof PhotographViewHolder) {
            viewHolder. itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startAlbum(v.getContext());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int count;
        if (thumbnailUrls.size() == 0){
            count = 1;
        } else if (thumbnailUrls.size() < limit) {
            count = thumbnailUrls.size() + 1;
        } else {
            count = limit;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int count = getItemCount();
        if (position == count - 1 && limit > thumbnailUrls.size()) {
            return PHOTOGRAPH_TYPE;
        } else {
            return SELECT_TYPE;
        }
    }

    @RequestPermission(request = 0, permissions = {Manifest.permission.READ_EXTERNAL_STORAGE})
    private void startAlbum(Context context){
        AlbumActivity.openSelf(context, limit - thumbnailUrls.size()
                , this);
    }

    @Override
    public void onSelectEnd(List<AlbumImageBean> beanList) {
        int oldCount = getItemCount();
        thumbnailUrls.addAll(beanList);
        int newCount = getItemCount();
        notifyItemRangeInserted(oldCount, newCount);
        if (dataChangeListener != null) dataChangeListener.onDataChange();
    }

    private void delete(AlbumImageBean bean){
        int index = thumbnailUrls.indexOf(bean);
        if (index > -1){
            int oldCount = getItemCount();
            thumbnailUrls.remove(index);
            int newCount = getItemCount();
            if (newCount < oldCount){
                notifyItemRemoved(index);
            } else {
                notifyDataSetChanged();
            }
            if (dataChangeListener != null) dataChangeListener.onDataChange();
        }
    }

    static class SelectViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.iv_select_thumbnail)
        ImageView ivSelectThumbnail;
        @BindView(R2.id.iv_delete)
        ImageView ivDelete;

        SelectViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSelectThumbnail = itemView.findViewById(R.id.iv_select_thumbnail);
            ivDelete = itemView.findViewById(R.id.iv_delete);
        }
    }

    static class PhotographViewHolder extends RecyclerView.ViewHolder{
        PhotographViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnDataChangeListener{
        void onDataChange();
    }

}
