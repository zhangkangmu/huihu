package com.huihu.module_mine.attentioncircle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_mine.R;
import com.huihu.module_mine.attentioncircle.attentioncircleinterface.IAttentionCirclePresenter;
import com.huihu.module_mine.attentioncircle.entity.CircleAttentionInfo;
import com.huihu.module_mine.attentioncircle.view.adapterInterface.OnAttentionItemClickListen;

import java.util.ArrayList;
import java.util.List;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.CircleAdapterViewHolder> implements View.OnClickListener {
    private List<CircleAttentionInfo.PageDatasBean> pageDatasList= new ArrayList<>();
    private Context mContext;
    private IAttentionCirclePresenter iAttentionCirclePresenter;
    private OnAttentionItemClickListen mOnAttentionItemClickListen;
    boolean isAttention=true;
    public CircleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CircleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_mine_item_attention, viewGroup, false);
        view.setOnClickListener(this);
        return new CircleAdapterViewHolder(view);
    }

    public void setOnAttentionItemClickListen(OnAttentionItemClickListen mOnAttentionItemClickListen){
        this.mOnAttentionItemClickListen=mOnAttentionItemClickListen;
    }

    @Override
    public void onBindViewHolder(@NonNull final CircleAdapterViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(i);
        ImgTools.showImageView(mContext,pageDatasList.get(i).getImageUrl(),viewHolder.iv_info_icon);
        viewHolder.tv_title.setText(pageDatasList.get(i).getCircleName());
        viewHolder.tv_principal.setText("负责人:"+pageDatasList.get(i).getNickName());
        viewHolder.tv_amount.setText(pageDatasList.get(i).getMemberNum()+"人");
        int circleType = pageDatasList.get(i).getCircleType();
    }

    @Override
    public int getItemCount() {
            return pageDatasList.size();
    }

    public void setAttentionCircleList(List<CircleAttentionInfo.PageDatasBean> pageDatasList) {
        this.pageDatasList = pageDatasList;
        notifyDataSetChanged();
    }

    public void setCircleItemChange(List<CircleAttentionInfo.PageDatasBean> pageDatas, int position, boolean isAttention) {
        this.pageDatasList = pageDatas;
        this.isAttention=isAttention;
        notifyItemChanged(position);
    }

    public List<CircleAttentionInfo.PageDatasBean> getmList() {
        return pageDatasList;
    }

    @Override
    public void onClick(View v) {
        if (mOnAttentionItemClickListen!=null){
        mOnAttentionItemClickListen.attentiOnItemClick(v, (int) v.getTag(), false);
        }
    }

    public class CircleAdapterViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_info_icon;
        TextView tv_title;
        TextView tv_principal;
        TextView tv_amount;

        public CircleAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_info_icon=itemView.findViewById(R.id.iv_info_icon);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_principal=itemView.findViewById(R.id.tv_principal);
            tv_amount=itemView.findViewById(R.id.tv_amount);
        }
    }
}
