package com.huihu.module_circle.circle.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.circle.entity.CircleBaseBean;
import com.huihu.module_circle.circle.view.adpterinterface.OnCircleItemClickListener;
import com.huihu.uilib.customize.CornerImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author caicancan
 * @time 2019/4/17
 */
public class CircleItemAdapter extends RecyclerView.Adapter<CircleItemAdapter.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<? extends CircleBaseBean> mList = new ArrayList<>();
    private OnCircleItemClickListener onCircleInfoItemClickListener;
    public List<? extends CircleBaseBean> getmList() {
        return mList;
    }
    private int type;
    public void setmList(List<? extends CircleBaseBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnCircleItemClickListener onItemClickListener){
        this.onCircleInfoItemClickListener=onItemClickListener;
    }

    public void setContext(Context mContext,int type) {
        this.mContext = mContext;
        this.type=type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_circle_banner_item, group, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        CircleBaseBean bean = mList.get(i);
        ImgTools.showImageView(mContext,bean.getImageUrl(),holder.image);
        holder.tv_community_name.setText(bean.getCircleName());
        holder.tv_number.setText(bean.getMemberNum()+"人");
        holder.itemView.setTag(i);
        holder.tv_join.setTag(i);
        if (bean.getJoin()==0){
            holder.tv_join.setText(mContext.getResources().getString(R.string.join));
            holder.tv_join.setTextColor(mContext.getResources().getColor(R.color.global_blue));
        }else {
            holder.tv_join.setText("已关注");
            holder.tv_join.setTextColor(mContext.getResources().getColor(R.color.gray_two));
        }
        holder.tv_join.setOnClickListener(this);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        onCircleInfoItemClickListener.circleInfoClicked(v,(int)v.getTag(),mList.get((int)v.getTag()),type);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.image)
        CornerImageView image;
        @BindView(R2.id.tv_community_name)
        TextView tv_community_name;
        @BindView(R2.id.tv_number)
        TextView tv_number;
        @BindView(R2.id.tv_join)
        TextView tv_join;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
