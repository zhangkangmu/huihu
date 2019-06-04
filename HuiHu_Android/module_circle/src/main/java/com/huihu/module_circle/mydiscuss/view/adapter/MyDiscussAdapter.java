package com.huihu.module_circle.mydiscuss.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.mydiscuss.entity.MyDiscussInfo;
import com.huihu.module_circle.mydiscuss.view.adapterInterface.OnClickListen;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDiscussAdapter extends RecyclerView.Adapter<MyDiscussAdapter.MyDiscussAdapterViewHold> implements View.OnClickListener {
    private OnClickListen OnClickListen;
    private Context mContext;
    private List<MyDiscussInfo.PageDatasBean> datas = new ArrayList<>();

    public MyDiscussAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setClickListen(OnClickListen OnClickListen) {
        this.OnClickListen = OnClickListen;
    }

    @NonNull
    @Override
    public MyDiscussAdapterViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_circle_item_my_discuss, viewGroup, false);
        view.setOnClickListener(this);
        return new MyDiscussAdapterViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDiscussAdapterViewHold holder, int i) {
        holder.itemView.setTag(i);
        MyDiscussInfo.PageDatasBean bean = datas.get(i);
        holder.title.setText(bean.getTitle());
        holder.content.setText(bean.getBriefContent());
        //分享
        holder.iv_share.setTag(i);
        holder.iv_share.setOnClickListener(this);
        //赞同总数
        holder.tv_popular_bottom_one.setText(bean.getAgreeCount() + " 赞同");
        //关注总数
        holder.tv_popular_bottom_two.setText(bean.getCommentCount() + " 赞同");
        //设置多张图片展示的方式
        List<MyDiscussInfo.PageDatasBean.ImagesBean> images = bean.getImages();
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
        return datas.size();
        //        return 20;
    }

    @Override
    public void onClick(View v) {
        if (OnClickListen != null) {
            OnClickListen.onClickItem(v, (Integer) v.getTag());
        }
    }

    public void setCircleDiscussList(List<MyDiscussInfo.PageDatasBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public List<MyDiscussInfo.PageDatasBean> getmList() {
        return datas;
    }

    class MyDiscussAdapterViewHold extends RecyclerView.ViewHolder {

        @BindView(R2.id.tv_title)
        TextView title;
        @BindView(R2.id.tv_content)
        TextView content;
        @BindView(R2.id.iv_share)
        ImageView iv_share;
        @BindView(R2.id.tv_popular_bottom_one)
        TextView tv_popular_bottom_one;
        @BindView(R2.id.tv_popular_bottom_two)
        TextView tv_popular_bottom_two;
        @BindView(R2.id.gridview)
        GridView gridView;

        public MyDiscussAdapterViewHold(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
