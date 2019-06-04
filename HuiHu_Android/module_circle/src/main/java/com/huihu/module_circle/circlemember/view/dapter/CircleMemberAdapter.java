package com.huihu.module_circle.circlemember.view.dapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.circlemember.entity.MemberManagementInfo;
import com.huihu.module_circle.circlemember.view.adapterInterface.OnItemClickListen;
import com.huihu.uilib.customize.CornerImageView;
import com.huihu.uilib.customize.RoundImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleMemberAdapter extends RecyclerView.Adapter<CircleMemberAdapter.CircleMemberAdapterViewHolder> implements View.OnClickListener{
    private Context mContext;
    private OnItemClickListen onItemClickListen;
    private List<MemberManagementInfo.PageDatasBean> mDatas= new ArrayList<>();;
    public CircleMemberAdapter(Context context) {
        this.mContext = context;
    }
    public void  setContext(Context mContext){
        this.mContext=mContext;
    }
    @NonNull
    @Override
    public CircleMemberAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_circle_item_circle_member, viewGroup, false);
        view.setOnClickListener(this);
        return new CircleMemberAdapterViewHolder(view);
    }

    public void setOnItemClickListen(OnItemClickListen onItemClickListen){
        this.onItemClickListen=onItemClickListen;
    }

    @Override
    public void onBindViewHolder(@NonNull CircleMemberAdapterViewHolder holder, int i) {
        holder.itemView.setTag(i);
        MemberManagementInfo.PageDatasBean bean=mDatas.get(i);
        //UserInfo有可能为null
        if (bean.getUserInfo()!=null){
//            holder.iv_info_icon.setTag(i);
//            holder.iv_info_icon.setOnClickListener(this);
            holder.ll_other.setTag(i);
            holder.ll_other.setOnClickListener(this);
            Glide.with(mContext).load(bean.getUserInfo().getUserHeadImage()).into(holder.iv_info_icon);
            holder.tv_title.setText(bean.getUserInfo().getAuthName());
            //用户是否已被关注（0：否 1：是)
            int followId = bean.getUserInfo().getFollow();
            holder.tv_attention.setTag(i);
            holder.tv_attention.setOnClickListener(this);
            if (followId == 0) {
                holder.tv_attention.setText("关注Ta");
                holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_blue));
            } else {
                holder.tv_attention.setText("已关注");
                holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.gray_three));
                holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_litterwhite));
            }
        }
        //设置时间
        long createTime = bean.getJoinTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date(createTime);
        holder.tv_time.setText(simpleDateFormat.format(date));

        //(-1:全部(默认) 1：普通 2：管理员 3:负责人 )
        int type = bean.getMemberType();
        if (type == 1) {
            holder.cv_administrator.setVisibility(View.INVISIBLE);
            holder.cv_principal.setVisibility(View.INVISIBLE);
        }else if (type == 2){
            holder.cv_administrator.setVisibility(View.VISIBLE);
            holder.cv_principal.setVisibility(View.INVISIBLE);
        }else if (type == 3){
            holder.cv_administrator.setVisibility(View.INVISIBLE);
            holder.cv_principal.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
//        return 20;
    }
    public void setMemberManagerList(List<MemberManagementInfo.PageDatasBean> datas) {
        Log.d("zyh-member-adapter",""+datas.size());
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
        public void onClick(View v) {
        if (onItemClickListen!=null){
            onItemClickListen.itemClick(v, (Integer) v.getTag());
        }
    }

    public List<MemberManagementInfo.PageDatasBean> getmList() {
        return mDatas;
    }
    public class CircleMemberAdapterViewHolder extends RecyclerView.ViewHolder{
        @BindView(R2.id.iv_info_icon)
        RoundImageView iv_info_icon;
        @BindView(R2.id.tv_title)
        TextView tv_title;
        @BindView(R2.id.tv_time)
        TextView tv_time;
        @BindView(R2.id.tv_attention)
        TextView tv_attention;
        @BindView(R2.id.cv_administrator)
        CornerImageView cv_administrator;
        @BindView(R2.id.cv_principal)
        CornerImageView cv_principal;
        @BindView(R2.id.ll_other)
        LinearLayout ll_other;

        public CircleMemberAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
