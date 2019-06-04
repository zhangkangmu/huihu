package com.huihu.module_circle.mycircle.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.mycircle.entity.MyCircleInfo;
import com.huihu.module_circle.mycircle.view.adapterInterface.OnMyCircleItemClickListener;
import com.huihu.uilib.customize.CornerImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author caicancan
 * @time 2019/4/17
 */
public class MyCircleAdapter extends RecyclerView.Adapter<MyCircleAdapter.ViewHolder> implements View.OnClickListener {


    private Context mContext;
    private List<MyCircleInfo.PageDatasBean> mList = new ArrayList<>();
    private OnMyCircleItemClickListener mlistener;

    public List<MyCircleInfo.PageDatasBean> getmList() {
        return mList;
    }

    public void setmList(List<MyCircleInfo.PageDatasBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void setOnMyCircleItemClickListener(OnMyCircleItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_circle_mycircle_item, group, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        MyCircleInfo.PageDatasBean bean = mList.get(i);
        //0: 未加入 1：成员 2：管理员 3：负责人
        int memberType = bean.getMemberType();
        if (memberType == 0) {
            if (i == 0) {
                holder.header.setVisibility(View.VISIBLE);
            } else {
                holder.header.setVisibility(View.GONE);
            }
            holder.join.setText(mContext.getResources().getString(R.string.join));
            holder.join.setTextColor(mContext.getResources().getColor(R.color.global_blue));
            holder.join.setVisibility(View.VISIBLE);
        } else {
            holder.join.setText(mContext.getResources().getString(R.string.attentioned));
            holder.join.setTextColor(mContext.getResources().getColor(R.color.gray_two));
            holder.join.setVisibility(View.GONE);
        }
        if (memberType == 3||memberType==2) {
            holder.cons_manage.setVisibility(View.VISIBLE);
            holder.tv_discuss_number.setText(""+bean.getIdeaNum());
        } else {
            holder.cons_manage.setVisibility(View.GONE);
        }
        ImgTools.showImageView(mContext, bean.getImageUrl(), holder.image);
        holder.tv_community_name.setText(bean.getCircleName());
        TextViewUtils.setTextFakeBold(holder.tv_community_name);
        holder.tv_number.setText("负责人:" + bean.getName() + "  " + bean.getMemberNum() + "人");
        holder.join.setTag(i);
        holder.join.setOnClickListener(this);
        holder.cons_manage.setTag(i);
        holder.cons_manage.setOnClickListener(this);
        holder.itemView.setTag(i);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        mlistener.onMyCircleItemClick(v, (int) v.getTag());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.image)
        CornerImageView image;
        @BindView(R2.id.tv_community_name)
        TextView tv_community_name;
        @BindView(R2.id.tv_number)
        TextView tv_number;
        @BindView(R2.id.join)
        TextView join;
        @BindView(R2.id.header)
        View header;
        @BindView(R2.id.cons_manage)
        ConstraintLayout cons_manage;
        @BindView(R2.id.tv_discuss_number)
        TextView tv_discuss_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
