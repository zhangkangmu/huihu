package com.huihu.uilib.album.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.uilib.R;
import com.huihu.uilib.album.albuminterface.IAlbumPresenter;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.LinkedList;
import java.util.List;

/**
 * create by wangjing on 2019/3/12 0012
 * description:
 */
public class AlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int PHOTOGRAPH_TYPE = 1;
    private static final int PICTURE_TYPE = 0;

    private List<AlbumImageBean> imageBeanList;
    private List<AlbumImageBean> selectImageBeanList = new LinkedList<>();
    private Context mContext;
    private IAlbumPresenter iAlbumPresenter;
    private OnSelectListener onSelectListener;
    private int limit = 9;

    private View.OnClickListener selectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof AlbumImageBean){
                AlbumImageBean bean = (AlbumImageBean) v.getTag();
                notifyChangeSelectList(bean);
            }
        }
    };

    public AlbumAdapter(@NonNull Context mContext, @NonNull IAlbumPresenter iAlbumPresenter) {
        this.mContext = mContext;
        this.iAlbumPresenter = iAlbumPresenter;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public List<AlbumImageBean> getImageBeanList() {
        return imageBeanList;
    }

    public void setImageBeanList(List<AlbumImageBean> imageBeanList) {
        this.imageBeanList = imageBeanList;
    }

    public List<AlbumImageBean> getSelectImageBeanList() {
        return selectImageBeanList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == PICTURE_TYPE){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comresourcelib_item_album_picture, viewGroup, false);
            return new AlbumViewHolder(view);
        } else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comresourcelib_item_album_photograph, viewGroup, false);
            return new PhotographViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof AlbumViewHolder){
            AlbumImageBean imageBean = imageBeanList.get(i - 1);
            AlbumViewHolder holder = (AlbumViewHolder) viewHolder;
            changeSelectOrder(holder, imageBean);
            holder.tvSelectOrder.setTag(imageBean);
            holder.tvSelectOrder.setOnClickListener(selectListener);
            holder.ivAlbumPicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iAlbumPresenter.v2pOpenPictureFragment(0, i - 1);
                }
            });
            Glide.with(mContext).load(imageBean.getPath()).into(holder.ivAlbumPicture);
        } else if (viewHolder instanceof PhotographViewHolder){
            PhotographViewHolder holder = (PhotographViewHolder) viewHolder;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iAlbumPresenter.v2pOpenSystemCamera();
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.size() == 0){
            this.onBindViewHolder(holder, position);
        } else if (payloads.get(0) instanceof AlbumImageBean){
            AlbumImageBean bean = (AlbumImageBean) payloads.get(0);
            if (holder instanceof AlbumViewHolder){
                AlbumViewHolder viewHolder = (AlbumViewHolder) holder;
               changeSelectOrder(viewHolder, bean);
            }
        }
    }

    @Override
    public int getItemCount() {
        return imageBeanList == null ? 1 : imageBeanList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? PHOTOGRAPH_TYPE : PICTURE_TYPE;
    }

    public void notifyChangeSelectList(AlbumImageBean bean){
        int selectPosition = selectImageBeanList.indexOf(bean);
        if (selectPosition == -1) {
            if (selectImageBeanList.size() < limit){
                selectImageBeanList.add(bean);
            } else {
                ToastUtil.show(String.format(mContext.getString(R.string.comresourcelib_album_select_limit), limit));
            }
        } else {
            selectImageBeanList.remove(bean);
//                    点击在中间的Item，修改后面的顺序
            int selectCount = selectImageBeanList.size();
            if (selectPosition != selectCount){
                for (int i = selectPosition; i < selectCount; i++){
                    AlbumImageBean imageBean = selectImageBeanList.get(i);
                    notifySelectOrderChange(imageBean);
                }
            }
        }
        notifySelectOrderChange(bean);
        if (selectListener != null) onSelectListener.onSelect(selectImageBeanList.size());
    }

    private void changeSelectOrder(AlbumViewHolder holder, AlbumImageBean bean){
        int selectOrder = selectImageBeanList.indexOf(bean) + 1;
        String order = selectOrder == 0 ? "" : String.valueOf(selectOrder);
        holder.tvSelectOrder.setText(order);
    }

    public void notifySelectOrderChange(AlbumImageBean bean){
        int position = imageBeanList.indexOf(bean);
        notifyItemChanged(position + 1, bean);
    }

    static class AlbumViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAlbumPicture;
        TextView tvSelectOrder;

        AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAlbumPicture = itemView.findViewById(R.id.iv_album_picture);
            tvSelectOrder = itemView.findViewById(R.id.tv_select_order);
        }
    }

    static class PhotographViewHolder extends RecyclerView.ViewHolder{
        PhotographViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface OnSelectListener{
        void onSelect(int count);
    }
}
