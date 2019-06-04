package com.huihu.module_circle.membermanagement.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.membermanagement.entity.MemberManagementInfo;
import com.huihu.module_circle.membermanagement.view.adapterInterface.OnItemClickListen;
import com.huihu.module_circle.newcirclerecommend.entity.NewCircleRecommendInfo;
import com.huihu.uilib.customize.CornerImageView;
import com.huihu.uilib.customize.RoundImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberManagerAdapter extends RecyclerView.Adapter<MemberManagerAdapter.MemberManagerAdapterViewHolder> implements View.OnClickListener{
    private Context mContext;
    private OnItemClickListen onItemClickListen;
    private List<MemberManagementInfo.PageDatasBean> mDatas= new ArrayList<>();;
    public MemberManagerAdapter(Context context) {
        this.mContext = context;
    }
    public void  setContext(Context mContext){
        this.mContext=mContext;
    }
    @NonNull
    @Override
    public MemberManagerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_circle_item_member_manage, viewGroup, false);
        view.setOnClickListener(this);
        return new MemberManagerAdapterViewHolder(view);
    }

    public void setOnItemClickListen(OnItemClickListen onItemClickListen){
        this.onItemClickListen=onItemClickListen;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberManagerAdapterViewHolder holder, int i) {
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
            //followId用户是否已被关注（0：否 1：是)
            int followId = bean.getUserInfo().getFollow();
            holder.tv_attention.setTag(i);
            holder.tv_attention.setOnClickListener(this);
            //(-1:全部(默认) 1：普通 2：管理员 3:负责人 )
            int type = bean.getMemberType();
            if (type!=1){
                holder.cv_banned.setVisibility(View.INVISIBLE);
                holder.im_manage.setVisibility(View.INVISIBLE);
            if (followId == 0 ) {
                if (bean.getUserInfo().getUid()== SPUtils.getInstance().getCurrentUid()){
                    holder.tv_attention.setText("退出");
                    holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.common_blue));
                    holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_litterwhite));
                }
                holder.tv_attention.setText("关注Ta");
                holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.white));
                holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_blue));
            } else {
                //如果是自己，退出的接口没有接,要改
                if (bean.getUserInfo().getUid()== SPUtils.getInstance().getCurrentUid()){
                    holder.tv_attention.setText("退出");
                    holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.common_blue));
                    holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_litterwhite));
                }
                holder.tv_attention.setText("已关注");
                holder.tv_attention.setTextColor(mContext.getResources().getColor(R.color.gray_three));
                holder.tv_attention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_litterwhite));
            }
            }else {
                //普通成员
                holder.cv_administrator.setVisibility(View.INVISIBLE);
                holder.cv_principal.setVisibility(View.INVISIBLE);
                holder.tv_attention.setVisibility(View.INVISIBLE);
                //三个点点点号显示
                holder.im_manage.setTag(i);
                holder.im_manage.setOnClickListener(this);
                holder.im_manage.setVisibility(View.VISIBLE);
                if (bean.getForbid()==1){
                        holder.cv_banned.setVisibility(View.VISIBLE);
                }else {
                    holder.cv_banned.setVisibility(View.INVISIBLE);
                }
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
            if (bean.getForbid() == 1) {
                holder.cv_banned.setVisibility(View.VISIBLE);
                holder.cv_administrator.setVisibility(View.INVISIBLE);
                holder.cv_principal.setVisibility(View.INVISIBLE);
            }else {
                holder.cv_banned.setVisibility(View.INVISIBLE);
                holder.cv_administrator.setVisibility(View.INVISIBLE);
                holder.cv_principal.setVisibility(View.INVISIBLE);
            }
        } else if (type == 2) {
            holder.cv_administrator.setVisibility(View.VISIBLE);
            holder.cv_principal.setVisibility(View.INVISIBLE);
            holder.cv_banned.setVisibility(View.INVISIBLE);
        }else if (type == 3){
            holder.cv_administrator.setVisibility(View.INVISIBLE);
            holder.cv_principal.setVisibility(View.VISIBLE);
            holder.cv_banned.setVisibility(View.INVISIBLE);
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
    public class MemberManagerAdapterViewHolder extends RecyclerView.ViewHolder{
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
        @BindView(R2.id.cv_banned)
        CornerImageView cv_banned;
        @BindView(R2.id.ll_other)
        LinearLayout ll_other;
        @BindView(R2.id.im_manage)
        TextView im_manage;

        public MemberManagerAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
