package com.huihu.module_circle.newcirclerecommend.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.newcirclerecommend.entity.NewCircleRecommendInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewCircleRecommendAdapter extends RecyclerView.Adapter<NewCircleRecommendAdapter.NewCircleRecommendAdapterHolder>{
    private Context mContext;
    private List<NewCircleRecommendInfo.PageDatasBean> mDatas= new ArrayList<>();;

    public NewCircleRecommendAdapter(Context context) {
        this.mContext = mContext;
    }



    @NonNull
    @Override
    public NewCircleRecommendAdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_circle_item_recomment, viewGroup, false);
//        view.setOnClickListener(this);
        return new NewCircleRecommendAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewCircleRecommendAdapterHolder holder, int i) {
        NewCircleRecommendInfo.PageDatasBean bean=mDatas.get(i);
        ImgTools.showImageViewWithConner(mContext,bean.getImageUrl(),holder.iv_info_icon,2);
        holder.tv_title.setText(bean.getCircleName());
        holder.tv_principal.setText(bean.getNickName());
        holder.tv_amount.setText(bean.getMemberNum()+"人");
        if (bean.getCircleType() == 0) {
            holder.tv_attention.setText("关注Ta");
            holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.global_blue));
            holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_litterwhite));
        } else {
            holder.tv_attention.setText("已关注");
            holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_blue));
            holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
//        return 20;
    }
    public void  setContext(Context mContext){
        this.mContext=mContext;
    }

    public void setNewCircleRecommendList(List<NewCircleRecommendInfo.PageDatasBean> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public List<NewCircleRecommendInfo.PageDatasBean> getmList() {
        return mDatas;
    }

    public class NewCircleRecommendAdapterHolder extends RecyclerView.ViewHolder{
        @BindView(R2.id.iv_info_icon)
        ImageView iv_info_icon;
        @BindView(R2.id.tv_title)
        TextView tv_title;
        @BindView(R2.id.tv_principal)
        TextView tv_principal;
        @BindView(R2.id.tv_amount)
        TextView tv_amount;
        @BindView(R2.id.tv_attention)
        TextView tv_attention;

        public NewCircleRecommendAdapterHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
